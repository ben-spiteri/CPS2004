#include <iostream>
#include <math.h>
#include <vector>


using namespace std;

vector<bool> intToBinary(int input);

vector<bool> reverseBinary(vector<bool> back);

void printNumber(vector<bool> number);

bool isPow2(int number);

int foo();

template <int T> 
class myuint{
  
    public:
        vector<bool> bigInt;

        myuint();
        myuint(int input);

        void print_myuint(myuint<T> bigInt);

        myuint<T> operator+(myuint<T> num);
        myuint<T> operator-(myuint<T> num);

        myuint<T> operator<<(int num);
        myuint<T> operator>>(int num);


        myuint<T> operator*(myuint<T> num);

        bool operator==(myuint<T> num);

        myuint<T> operator/(myuint<T> num);
        myuint<T> operator%(myuint<T> num);

        template <typename type>
        type convert_toInt();
};
