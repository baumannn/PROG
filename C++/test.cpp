
#include <iostream>
#include <algorithm>

using namespace std;

int foo(int a, int b){
    if(a==1){
        return 1;
    }
    return b + foo(a-1, b+1);

}

int main()
{
    int n;
    while(cin >> n){
     cout << foo(n+1,0) << endl;
    }
    return 0;
}
