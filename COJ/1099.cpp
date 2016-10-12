#include <iostream>
#include <sstream>
using namespace std;

int max(int a, int b){
    if(a > b)
        return a;
    return b;
}

int main(){
    string s;
    getline(cin , s);
    while(s!="0"){
        stringstream ss;
        ss << s;
        int a, b, c, aa, bb, cc;
        ss >> a >> b >> c;
        cc = max(max(a,b),c);
        if(cc == a) {
            bb = b;
            aa = c;
        }
        else if(cc == b) {
            bb = a;
            aa = c;
        }
        else {
            bb = b;
            aa = a;
        }
        bb*=bb;

        bb+=(aa*aa);
        if(bb==cc*cc){
            cout << "right" << endl;
        }
        else{
            cout << "wrong" << endl;
        }


        getline(cin , s);

    }



    return 0;
}
