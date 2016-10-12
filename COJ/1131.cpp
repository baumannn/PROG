#include <cmath>
#include <iostream>
using namespace std;


long long comb(long long n, long long k){
    if(k==1)
        return n;
    if(n==k)
        return 1;
    if(k>n)
        return 0;

}


int numdiv(long long n){
    if(n==1)
        return 1;
    int sum = 2;
    for(long long i = 2; i <= sqrt(n); ++i){
        if(n%i==0){
            sum+=2;
            cout << i << endl;
        }

    }
    return sum;
}


int main(){
    int a, b;
    while(cin >> a){
       // long long comb = fact(a)/(fact(b)*fact(a-b));
        cout << numdiv(720) << endl;
    }
    return 0;
}
