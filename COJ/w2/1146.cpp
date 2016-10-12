#include <iostream>
using namespace std;

int uno[5];
int dos[5];
int tres[5];
bool yes = false;

bool summ() {
    for(int g = 0; g < 4; ++g) {
        for(int h = 0; h < 4; ++h) {
            for(int i = 0; i < 4; ++i) {
                for(int j = 0; j < 4; ++j) {
                    for(int k = 0; k < 4; ++k) {

                        for(int l = 0; l < 5; ++l) {
                            tres[l] = uno[l];
                        }

                        tres[0] = (uno[0] + g + h + j)%4;
                        tres[1] = (uno[1] + g + h + i)%4;
                        tres[2] = (uno[2] + g + i + k)%4;
                        tres[3] = (uno[3] + i + j + k)%4;
                        tres[4] = (uno[4] + h + j + k)%4;

                        yes = true;
                        for(int l = 0; l < 5; ++l) {
                            if(yes && tres[l]==dos[l]){
                                yes = true;
                            }
                            else yes = false;
                        }

                        if(yes) {
                            cout << tres[0] << " " << tres[1] << " " << tres[2] << " " << tres[3] << " " << tres[4] << "\n";
                           cout << g << " " << h << " " << i << " " << j << " " << k << '\n';
                            return yes;
                        }
                    }
                }
            }
        }
    }
    return false;
}

int get(string s) {
    if(s == "Diamond")
        return 2;
    if(s == "Heart")
        return 0;
    if(s == "Spade")
        return 1;
    if(s == "Clover")
        return 3;
}

int main(){

    string a, b, c, d, e;
    while (cin >> a >> b >> c >> d >> e) {
            uno[0] = get(a);
            uno[1] = get(b);
            uno[2] = get(c);
            uno[3] = get(d);
            uno[4] = get(e);

            cin >> a >> b >> c >> d >> e;

            dos[0] = get(a);
            dos[1] = get(b);
            dos[2] = get(c);
            dos[3] = get(d);
            dos[4] = get(e);

            if(summ()){
                cout << "The necklace can be useful." << endl;
            }
            else
                cout << "The necklace can not be useful." << endl;
    }



    return 0;
}
