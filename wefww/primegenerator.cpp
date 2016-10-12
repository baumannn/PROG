#include <iostream>
#include <math.h>
#include <fstream>

using namespace std;


bool isprime(int n){
	if(n==2)
		return true;
	if(n%2==0 || n==0)
		return false;
	if(n == 1)
		return false;
	for(int i = 3; i <= sqrt(n); i+=2){
		if(n%i==0)
			return false;
	}
	return true;
}

int primees[]

int main(){


    int k = 0;
    for(int i=3; i<1000000; i+=2)
    {
        if(isprime(i)) ++k;
    }
    cout << k;
}
