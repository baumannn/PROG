#include <iostream>

using namespace std;

/*

#@Algorithm to find Fibonnaci numbers@#
int fib(int n#@param #@ represent term to find @#@#)
{
    #@if id the first or second term@#
        if(n==0||n==1)
            return 1;
        return fib(n-1)+fib(n-2);
}#@end@#


*/


int main() {
    string str, out;
    while(getline(cin, str)) {
        out = "";
        int com = 0;
        while(str.size() > 0) {
            if(str[0] == '#' && str[1] == '@') {
                ++com;
                str.erase(0,2);
            }
            else if(com != 0 && str[0] == '@' && str[1] == '#') {
                --com;
                str.erase(0,2);
            }
            else if(com != 0) {
                str.erase(0,1);
            }
            else {
                out += str[0];
                str.erase(0,1);
            }


        }
        cout << out << endl;

    }


    return 0;
}
