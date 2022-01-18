#include <iostream>
#include <list>
#include <map>
#include <set>
#include <string>
#include <sstream>

using namespace std;

template <typename T>
class edge{

    private:
        const T source;
        const T target;

    public:
        edge(const T &s, const T &t);
        const T &src() const;
        const T &tgt() const;
};


template <typename T>
class node{

    private:
        T object;

        set<T> nodes;

    public:
        node(const T &object);

        bool operator<(const node<T> &obj) const;

        void add_node(const T &node);

        void get_edge(list<edge<T>> &edges) const;
};

template <typename T>
class DAG{
    private:
        set<node<T>> nodes;
    
    public:
        DAG(const list<edge<T>> &edges);

        void print(DAG<T> graph) const;

        list<edge<T>> get_edge() const;
        
        void remove(const T &target);
};
