
#include <iostream>
using namespace std;
#define MAX 1001

int A[MAX], B[MAX], C[MAX+MAX], M[MAX][MAX];
int n1, n2;

bool rec(int a, int b, int c){

    bool b1 = false, b2 = false;

    if(M[a][b] == 1){
        return false;
    }

    if(c == n1 + n2 ) {
        return true;
    }
    if(A[a] != C[c] && B[b] != C[c]) {
        return false;
    }

    if(A[a] == C[c] && a < n1) {
        b1 = rec(a+1, b, c+1);
    }
    if(B[b] == C[c] && b < n2) {
        b2 = rec(a, b+1, c+1);
    }

    M[a][b] = 1;

    return b1 || b2;

}


int main(){
    cin >> n1 >> n2;
    while(n1 != 0 || n2 != 0) {

        for(int i = 0; i <= n1; ++i) {
            for(int j = 0; j <= n2; ++j) {
                M[i][j] = 0;
            }
        }

        for(int i = 0; i < n1; ++i) {
            cin >> A[i];
        }

        for(int i = 0; i < n2; ++i) {
            cin >> B[i];
        }

        for(int i = 0; i < n1 + n2; ++i) {
            cin >> C[i];
        }

        if(rec(0,0,0)) {

            cout << "possible" << endl;

        }

        else cout  << "not possible" << endl;
/*
        for(int i = 0; i <= n1; ++i) {
            for(int j = 0; j <= n2; ++j) {
                cout << M[i][j] << " | ";
            }
            cout << endl;
        }
*/
        cin >> n1 >> n2;
    }
    return 0;
}
