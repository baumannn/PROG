#include <iostream>
#include <string>
using namespace std;

int main(){
    string a, b;
    while(cin >> a >> b) {
        bool out = false;
        for(int ia = 0, ib = 0; ib < b.length() && ia < a.length(); ++ib) {
            if(b[ib] == a[ia]) {
                ia++;
            }
            if(ia == a.length()) {
                out = true;
            }
        }
        if(out) {
            cout << "Yes" << endl;
        }
        else
            cout << "No" << endl;
    }
    return 0;
}
