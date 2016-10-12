#include <vector>
#include <iostream>
using namespace std;

int MAX = 300;

int mainy(){
    int vertices = 7;
    vector<vector<int> > a(vertices, vector<int>(vertices,MAX));

    for(int i = 0; i < vertices; ++i){
        a[i][i] = 0;
    }

    a[0][1] = 50;
    a[0][2] = 60;
    a[1][3] = 120;
    a[1][4] = 90;
    a[2][5] = 50;
    a[3][5] = 80;
    a[3][6] = 70;
    a[4][6] = 40;
    a[5][6] = 140;

    a[1][0] = 50;
    a[2][0] = 60;
    a[3][1] = 120;
    a[4][1] = 90;
    a[5][2] = 50;
    a[5][3] = 80;
    a[6][3] = 70;
    a[6][4] = 40;
    a[6][5] = 140;

    cout << endl;
    for(int i = 0; i < vertices; ++i) {
        for(int j = 0; j < vertices; ++j) {
            if(a[i][j] == MAX)
                cout << "X ";
            else
                cout << a[i][j] << " ";
        }
        cout << endl;
    }


    for(int k = 0; k < vertices; ++k) {
        for(int i = 0; i < vertices; ++i) {
            for(int j = 0; j < vertices; ++j) {
                if(a[i][j] > max(a[i][k] , a[k][j])) {
                    a[i][j] = max(a[i][k] , a[k][j]);

                }
            }
        }
    }



    cout << endl;
    for(int i = 0; i < vertices; ++i) {
        for(int j = 0; j < vertices; ++j) {
            if(a[i][j] == MAX)
                cout << "X ";
            else
                cout << a[i][j] << " ";
        }
        cout << endl;
    }

    return 0;
}

int main() {
    mainy();
    return 0;
}
