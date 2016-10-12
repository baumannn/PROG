#include <iostream>
#include <string>
using namespace std;


int main() {
    string str;
    cin >> str;
    while(str!="0") {
        int a = 0, b = 0;
        for(int i = 0; i < str.length(); i+=2) {
            a += str[i]-48;
        }
        for(int i = 1; i < str.length(); i+=2)
            b += str[i]-48;
        a-=b;
        if(a==0 || a%11==0){
            cout << str << " is a multiple of 11." << endl;
        }
        else
            cout << str << " is not a multiple of 11." << endl;

        cin >> str;
    }


    return 0;
}
