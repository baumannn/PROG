#include <iostream>
using namespace std;

//a->b
void overlap(long long arra[201], long long arrb[201], int times) {


    long long temp[201];

    for(int i = 0; i < times; ++i) {

        for(int p = 0; p < 201; ++p) {
            if(p-i < 0){
                temp[p]=0;
            }
            else
                temp[p]=arra[p-i];
        }
        for(int j = 0; j < 201; ++j) {
            arrb[j] += temp[j];
        }


    }
    return;
}

int main(){

    long long mah[19][201];
    for(int i = 0; i < 19; ++i){
        for(int j = 0; j < 201
        ; ++j) {
            mah[i][j] = 0;
        }
    }
    mah[0][0] = 1;
    for(int i = 1; i < 19; ++i){
        overlap(mah[i-1],mah[i],i);
    }
    /* print
    for(int i = 0; i < 19; ++i) {

        for(int j = 0; j < 200; ++j){
            cout << mah[i][j] <<" ";
        }
        cout << endl;
    }
    */
    int n, k;
    cin >> n >> k;
    while( n != 0 || k != 0) {
        cout << mah[n][k] << endl;
        cin >> n >> k;
    }
    return 0;
}
