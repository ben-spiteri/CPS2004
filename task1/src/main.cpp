#include <iostream>
#include <list>
#include <map>
#include <set>
#include <string>
#include <sstream>

#include "dag.hpp"


using namespace std;

//constructor for the edge class to set the nodes making up the edge
template <typename T>
edge<T>::edge(const T &s, const T &t) : source(move(s)), target(move(t)){}

//getter functions to handle source and target private variables
template <typename T>
const T &edge<T>::src() const{
    return source;
}

template <typename T>
const T &edge<T>::tgt() const{
    return target;
}


//============================================================================================================

//constructor for the node class object
template <typename T>
node<T>::node(const T &object) : object(move(object)){}//object here is the value stored withing this node object

//overloading of < operator for comparison of 2 node type objects 
//makes flow of program more efficient when generating DAG
template <typename T>
bool node<T>::operator<(const node<T> &obj) const{
    return (object < obj.object);
}

//adding a node
template <typename T>
void node<T>::add_node(const T &node){
    nodes.insert(node);
}

//adding an edge to a node
template <typename T>
void node<T>::get_edge(list<edge<T>> &edges) const{
    for(const auto &node : nodes){
        edges.push_back(edge<T>(object, node));
    }
}

//===============================================================================================================

//constructor for DAG takes list of edges as paramater
template <typename T>
DAG<T>::DAG(const list<edge<T>> &edges){
    //for each edge in the edges list
    for(const auto &edge : edges){
        //initializing the source and the target nodes for each edge
        T src = move(edge.src());
        T tgt = move(edge.tgt());

        //initializing a pointer to the source node
        auto n = node(src);

        //determining to see if soruce node is already present in the DAG
        auto i = nodes.find(n);
        if(i == nodes.end()){
            i = nodes.insert(n).first;
        }

        //adding the target node to the list of edges contained withing the source node
        ((node<T> &)*i).add_node(tgt);

        //determining to see if target node is already present in the DAG
        i = nodes.find(tgt);
        if(i == nodes.end()){
            nodes.insert(tgt);
        } 
    }
}

//printing the list of edges
template <typename T>
void DAG<T>::print(DAG<T> graph) const{
    
    const auto &edgeList = graph.get_edge();

    for(const auto &edge : edgeList){
        if(nodes.find(edge.tgt()) != nodes.end()){
            std::cout << edge.src() << " -> " << edge.tgt() << endl; 
        }
    }
}

//getting a copy of the list of edges from each node
template <typename T>
list<edge<T>> DAG<T>::get_edge() const{
    
    list<edge<T>> edges;

    for(const auto &n : nodes){
        n.get_edge(edges);
    }

    return edges;
}

//function to remove a node from the graph
template <typename T>
void DAG<T>::remove(const T &target){
    nodes.erase(target);
}

//==========================================================================================================

//main function
int main(){
    //generating the list of nodes
    list<edge<char>> edges;

    edges.push_back(edge<char>('a','b'));
    edges.push_back(edge<char>('a','c'));
    edges.push_back(edge<char>('b','c'));
    edges.push_back(edge<char>('b','f'));
    edges.push_back(edge<char>('c','d'));
    edges.push_back(edge<char>('d','f'));
    edges.push_back(edge<char>('f','g'));


    //generating the graph
    DAG<char> graph(edges);

    //printing the edges in the DAG
    std::cout << "DAG" << endl << endl;
    graph.print(graph);

    std::cout << endl;
    std::cout << endl;

    //removing node f and printing the DAG
    std::cout << "remove node f" << endl;
    graph.remove('f');
    graph.print(graph);
}