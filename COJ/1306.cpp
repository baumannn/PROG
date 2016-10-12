#include <iostream>
#include <string>
#include <sstream>
using namespace std;

int main() {
    int n;
    string s, b;
    cin >> n;
    while(n--) {
        cin >> s;
        b = "";
        if(s.length() > 2) {
            b += s[s.length()-2];
            b += s[s.length()-1];
        }
        else
            b = s;
        stringstream ss;
        ss << b;
        int a;
        ss >> a;
        if(a%4==0){
            cout << "YES" << endl;
        }
        else cout << "NO" << endl;

    }

    return 0;
}
