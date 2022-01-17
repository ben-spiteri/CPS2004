#include <iostream>
#include <vector>
#include <math.h>

#include "myuint.hpp"

//===========================================================================================================
//helper functions

//function to reverse a number
vector<bool> reverseBinary(vector<bool> back){
    vector<bool> rightWay;

    for (auto i = back.rbegin(); i != back.rend(); ++i){
        rightWay.push_back(*i);
    }

    return rightWay;
}

//function to calculate ones compliment
vector<bool> onesCompliment(vector<bool> number){
    vector<bool> ones;

    for ( auto i = number.begin(); i != number.end(); ++i){
        if(*i){
            ones.push_back(false);
        }
        else{
            ones.push_back(true);
        }
    }

    return ones;
}

//function to convert an int into a binary number stored as a bector bool
vector<bool> intToBinary(int input){
    vector<bool> myBinary;

    while (input != 0){
        myBinary.push_back((input % 2 == 0 ? false : true));
        input /= 2;    
    }

    myBinary = reverseBinary(myBinary);

    return myBinary;
}


//function that takes a binary number stored as vector bool and prints it out
void printNumber(vector<bool> number){

    for ( auto i = number.begin(); i != number.end(); ++i){
        cout << *i; //this is also printing 1s and 0s
        //cout << (*i == true ? 0 : 1) << " "; 
    }

    //add possibility to print number as a string
}

//function that determines if a number is 2^n
bool isPow2(int number){
    if (number <= 0){
        return false;
    }

    while (number != 1){
        
        if (number%2 != 0){
            return false;
        }
        
        number = number/2;
    }
    return true;
}

//============================================================================================================

template <int T>
//constructor with no parameters
myuint<T>::myuint(){        
    if(!isPow2(T)){
        cerr << "myuint only accept unsigned intiger values that are 2^n" << endl;
    }
}

//constructor for an int parameter
template <int T>
myuint<T>::myuint(int input){
    if(!isPow2(T)){
        cerr << "myuint only accept unsigned intiger values that are 2^n" << endl;
    }
    else{

        vector<bool> binary_num = intToBinary(input);

        //initializing container for bigInt     
        if(T >= binary_num.size()){
            int s = binary_num.size();
            for (auto i = 0 ; i < (T - s) ; i++){
                bigInt.push_back(false);
            }

            for (auto i = binary_num.begin(); i != binary_num.end(); ++i){
                bigInt.push_back(*i);
            }
        }
        else {
            std::cout << "size allocated to store value is too small" << endl;
        }
    } 
}

//=======================================================================================================
//myuint operation functions

//function to print myuint
template <int T>
void print_myuint(myuint<T> bigInt){
    
    for (auto i = bigInt.bigInt.begin(); i != bigInt.bigInt.end(); ++i){
        cout << *i; //this is also printing 1s and 0s
        //cout << (*i == true ? 0 : 1) << " "; 
    }
    std::cout << endl;
}

//myuint overloading of +
template <int T>
myuint<T> myuint<T>::operator+(myuint<T> num){
    vector<bool> a = myuint<T>::bigInt;
    vector<bool> b = num.bigInt;

    vector<bool> sum;

    bool carryFlag = false;

    //determining which of the 2 numbers is larger
    if(a.size() > b.size()){
        //iterating over the smaller number
        auto j = a.rbegin();
        for (auto i = b.rbegin(); i != b.rend(); ++i, ++j){
            
            //standard process of binary addition
            if(*i & *j){
                if(carryFlag){
                    sum.push_back(true);
                    carryFlag = true;
                }
                else{
                    sum.push_back(false);
                    carryFlag = true;
                }
            }
            else if(*i & !(*j)){
                if(carryFlag){
                    sum.push_back(false);
                    carryFlag = true;
                }
                else{
                    sum.push_back(true);
                    carryFlag = false;
                }
            }
            else if(!(*i) & *j){
                if(carryFlag){
                    sum.push_back(false);
                    carryFlag = true;
                }
                else{
                    sum.push_back(true);
                    carryFlag = false;
                }                
            }
            else if(!(*i) & !(*j)){
                if(carryFlag){
                    sum.push_back(true);
                    carryFlag = false;
                }
                else{
                    sum.push_back(false);
                    carryFlag = false;
                }
            }
        }

        while(j != a.rend()){
            sum.push_back(*j);
            ++j;
        }
    }

    //copy of above case but if b is larger than a
    else {
        auto j = b.rbegin();
        for (auto i = a.rbegin(); i != a.rend(); ++i, ++j){
            
            if(*i & *j){
                if(carryFlag){
                    sum.push_back(true);
                    carryFlag = true;
                }
                else{
                    sum.push_back(false);
                    carryFlag = true;
                }
            }
            else if(*i & !(*j)){
                if(carryFlag){
                    sum.push_back(false);
                    carryFlag = true;
                }
                else{
                    sum.push_back(true);
                    carryFlag = false;
                }
            }
            else if(!(*i) & *j){
                if(carryFlag){
                    sum.push_back(false);
                    carryFlag = true;
                }
                else{
                    sum.push_back(true);
                    carryFlag = false;
                }                
            }
            else if(!(*i) & !(*j)){
                if(carryFlag){
                    sum.push_back(true);
                    carryFlag = false;
                }
                else{
                    sum.push_back(false);
                    carryFlag = false;
                }
            }
        }

        while(j != b.rend()){
            sum.push_back(*j);
            ++j;
        }

    }

    if(carryFlag){
        sum.push_back(true);
    }

    sum = reverseBinary(sum);

    myuint<T> bigIntSum;
    bigIntSum.bigInt = sum;

    return bigIntSum;
}

//myuint overloading of -
template <int T> 
myuint<T> myuint<T>::operator-(myuint<T> num){
    vector<bool> a = myuint<T>::bigInt;
    vector<bool> b = num.bigInt;

    //setting the myuints to the same bit length
    if (a.size() != b.size()){
        if (a.size() > b.size()){

            b = reverseBinary(b);

            while (a.size() > b.size()){
                b.push_back(false);
            }

            b = reverseBinary(b);
        }
        else {
            a = reverseBinary(a);

            while (b.size() > a.size()){
                a.push_back(false);
            }

            a = reverseBinary(a);
        }
    }

    //one's compliment
    myuint<T> bOnes;
    bOnes.bigInt = onesCompliment(b);

    myuint<T> one(1);

    //two's compliment
    myuint<T> bTwos = bOnes + one;

    myuint<T> aN;
    aN.bigInt = a;

    //calculating the difference
    myuint<T> difference = aN + bTwos;

    vector<bool> temp = difference.bigInt;

    //pop to control bit length
    temp = reverseBinary(temp);
    temp.pop_back();
    temp = reverseBinary(temp);

    difference.bigInt = temp;

    return difference;


    /*
    note: due to using 2's compliment, in the case a large number is subtracted from a smaller number
          rather than an error being thrown, a negative number in 2's compliment is returned
          (i.e. if 4-11 = -7 ---- 0100 - 1011 = 1001) while this can be solved using simply by checking
          if the first location of a vector contains true, and if it does rather than returning a string
          you throw an error. This propery of calculating negative numbers come in very useful for working
          out the / and % operators so I left it in
    
    */
}

//myuint overlaoding of >>
template <int T>
myuint<T> myuint<T>::operator>>(int n){
    vector<bool> number = myuint<T>::bigInt;

    //if number of left shifts is larger then the length of myuint, an empty myuint is returned
    if(number.size() <= n){
        myuint<T> err(0);

        return err;
    }

    for(int i = 0; i < n; i++){
        number.pop_back();
    }

    number = reverseBinary(number);

    //to maintain the same bit count in the original myuint
    for(int i = 0; i < n; i++){
        number.push_back(false);
    }

    number = reverseBinary(number);

    myuint<T> shifted;
    shifted.bigInt = number;

    return shifted;
}

//myuint overloading of <<
template <int T>
myuint<T> myuint<T>::operator<<(int n){
    vector<bool> number = myuint<T>::bigInt;

    //as the point of this exercise is to store very large numbers, I see it only fitting that no overload errors are thrown
    for (int i = 0; i < n; i++){
        number.push_back(false);
    }

    myuint<T> shifted;
    shifted.bigInt = number;

    return shifted;
}


//function to determine if 2 myuints are equal
template<int T> 
bool myuint<T>::operator==(myuint<T> num){
    vector<bool> a = myuint<T>::bigInt;
    vector<bool> b = num.bigInt;

    //setting both myuints to the same length
    if (a.size() != b.size()){
        if (a.size() > b.size()){

            b = reverseBinary(b);

            while (a.size() > b.size()){
                b.push_back(false);
            }

            b = reverseBinary(b);
        }
        else {
            a = reverseBinary(a);

            while (b.size() > a.size()){
                a.push_back(false);
            }

            a = reverseBinary(a);
        }
    }

    bool isMatch = true;
    
    //looping through each position in the vector
    auto j = b.begin();
    for(auto i = a.begin(); i != a.end(); ++i, ++j){
        if(*i != *j){
            isMatch = false;
        }
    }

    return isMatch;
}

//myuint overloading of *
template <int T> 
myuint<T> myuint<T>::operator*(myuint<T> num){
    vector<bool> a = myuint::bigInt;
    vector<bool> b = num.bigInt;

    myuint<T> temp;
    temp.bigInt = a;

    myuint<T> product;
    product.bigInt = a;

    //using standard method of binary multiplication
    //for each true bit in the multiplyer left shift the multiplyee and add it    
    int count = 0;
    for(auto i = b.rbegin(); i != b.rend(); ++i, count++){
        if(*i){
            temp = temp << count;
            product = product + temp; 
        }
    }

    return product;
}

//myuint overloading of /
template <int T>
myuint<T> myuint<T>::operator/(myuint<T> num){
    vector<bool> a = myuint<T>::bigInt;
    vector<bool> b = num.bigInt;

    myuint<T> number;
    number.bigInt = a;

    myuint<T> devisor;
    devisor.bigInt = b;

    myuint<T> minus;

    bool neg = false;//variable to check if subtraction return negative number

    int count = 0;

    //loop to count how many times you can subtract the divisor from the divisee before the
    //the result is negative. Then returning the count
    while(!neg){
        minus = number - devisor;

        auto i = minus.bigInt.begin(); 
        if(*i){
            neg = true;
        }
        else {
            count++;
            number.bigInt = minus.bigInt;
        }
    }

    myuint<T> result = count;

    return result;
}

//myuint overloading of %
template <int T>
myuint<T> myuint<T>::operator%(myuint<T> num){
    vector<bool> a = myuint<T>::bigInt;
    vector<bool> b = num.bigInt;

    myuint<T> number;
    number.bigInt = a;

    myuint<T> devisor;
    devisor.bigInt = b;

    myuint<T> minus;

    bool neg = false;

    int count = 0;

    //using same teqnique as division but returning the remainder instead of minus count
    while(!neg){
        minus = number - devisor;

        auto i = minus.bigInt.begin(); 
        if(*i){
            neg = true;
        }
        else {
            count  += 1;
            number.bigInt = minus.bigInt;
        }
    }


    return number;
}


//function to convert from BigInt to int
template <int T>
template <typename type>
type myuint<T>::convert_toInt(){

    vector<bool> num = myuint<T>::bigInt;
    vector<bool> net;

    int total = 0;
    int count = 1;

    int bigIntsize = num.size();



    if(bigIntsize > 32){
        
        auto p = num.rbegin();
        for(int i = 0; i < 32; i++, ++p){
            net.push_back(*p);
        }

        net = reverseBinary(net);
    }

    for(auto i = num.rbegin(); i != num.rend(); ++i){
        if(*i){
            total = total + ((int)(pow(2, count)));
        }
        count += 1;
    }

    return total/2;
}

//=======================================================================================================
//test functions

int test(){
    
    std::cout << "===================================================" << std::endl;

    std::cout << endl;
    std::cout << endl;
    
    std::cout << "creating myuint a via myuint(int num)" << endl;
    myuint<8> a(105);
    print_myuint(a);
    std::cout << "executing a.convert_toInt " << a.convert_toInt<int>() << endl;

    std::cout << endl;
    std::cout << endl;

    std::cout << "creating myuint b via myuint() = int num" << endl;
    myuint<8> b = 11;
    print_myuint(b);
    std::cout << "executing b.convert_toInt " << b.convert_toInt<int>() << endl;

    std::cout << endl;
    std::cout << endl;

    std::cout << "executing a + b and stroing it in myuint c " << endl;
    myuint<8> c = a + b;
    print_myuint(c);
    std::cout << "executing c.convert_toInt " << c.convert_toInt<int>() << endl;

    std::cout << endl;
    std::cout << endl;

    std::cout << "executing a - b and stroing it in myuint d" << endl;
    myuint<8> d = a - b;
    print_myuint(d);
    std::cout << "executing d.convert_toInt " << d.convert_toInt<int>() << endl;
    
    std::cout << endl;
    std::cout << endl;

    std::cout << "executing b << 2 and storing it as myuint e" << endl;
    myuint<8> e = b << 2 ;
    print_myuint(e);
    std::cout << "executing e.convert_toInt " << e.convert_toInt<int>() << endl;

    std::cout << endl;
    std::cout << endl;

    std::cout << "executing b >> 2 and storing it as myuint f" << endl;
    myuint<8> f = b >> 2;
    print_myuint(f);
    std::cout << "executing f.convert_toInt " << f.convert_toInt<int>() << endl;

    std::cout << endl;
    std::cout << endl;

    std::cout << "executing a * b and storing it as myuint prod" << endl;
    myuint<8> prod = a * b;
    print_myuint(prod);
    std::cout << "executing prod.convert_toInt " << prod.convert_toInt<int>() << endl;

    std::cout << endl;
    std::cout << endl;

    std::cout << "executing a == b and storing it in bool check" << endl;
    bool check;
    
    if(a == b){
        check = true;
    }
    else {
        check = false;
    }

    std::cout << (check == true ?  "true" : "false")  << endl;

    std::cout << endl;
    std::cout << endl;

    std::cout << "executing a / b and storing it in myuint div" << endl;
    myuint<8> div = a/b;
    print_myuint(div);
    std::cout << "executing div.convert_toInt " << div.convert_toInt<int>() << endl;
    
    std::cout << endl;
    std::cout << endl;

    std::cout << "executing a % b and storing it in myuint mod" << endl;
    myuint<8> mod = a%b;
    print_myuint(mod);
    std::cout << "executing mod.convert_toInt " << mod.convert_toInt<int>() << endl;

    std::cout << "===================================================" << std::endl;

    return 0;
}

int foo(){
    myuint<2048> i(5);
    myuint<2048> j = (i << 1000) + 23;

    return j.convert_toInt<int>();
}

//=======================================================================================================
//main function

int main() {
    std::cout << "start main method" << std::endl;

    test();

    std::cout << foo() << endl;

    return 0;
}

