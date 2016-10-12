#include <iostream>
using namespace std;

int main(){
    int n;
    cin >> n;
    if(n > 0)
        cout << n*(n+1)/2<<endl;
    else if(n==0)
        cout << 1 << endl;
    else {
        n*=-1;
        n = n*(n+1)/2;
        n--;
        n*=-1;
        cout << n << endl;
    }
    return 0;
}
