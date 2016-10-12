#include <iostream>
using namespace std;


bool P (int a) {
    int dig = 0;
    int x = a;
    while(x > 0) {
        a /= 10;
        ++dig;
    }

    int b = 1;
    while(a > 0) {
        if (b == 0) {
            return true;
        }
        b *= a % 10;
        a /= 10;
    }
    return !(b % 2);
}




int main(){
    int n, a, b, c;
    cin >> n;
    for(int i = 0; i < n; ++i) {
        cin >> a >> b;
        c = 0;
        for(int j = a; j <= b; ++j) {
            if(!(P(j)%2)) {
                ++c;
            }
        }
        cout << c << endl;
    }


    return 0;
}
