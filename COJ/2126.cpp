#include <iostream>
#include <vector>
using namespace std;

int MAX = 99999;

int main() {
    int n, a, b, q, min, minc;
    while(cin >> n) {
        vector<vector<int> > arr(n, vector<int>(n,MAX));
        for(int i = 0; i < n; ++i) {
            arr[i][i] = 0;
        }
        for(int i = 0; i < n; ++i) {
            cin >> a;
            arr[i][a-1] = 1;
        }

        //floyd
        for(int k = 0; k < n; ++k) {
            for(int i = 0; i < n; ++i) {
                for(int j = 0; j < n; ++j) {
                    if(arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];

                    }
                }
            }
        }
/*
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if(arr[i][j] == MAX)
                    cout << "X ";
                else
                    cout << arr[i][j] << " ";
            }
            cout << endl;
        }
*/
        cin >> q;
        for(int i = 0; i < q; ++i) {
            cin >> a >> b;
            min = MAX;
            for(int j = 0; j < n; ++j) {
                if(arr[a-1][j] < MAX && arr[b-1][j] < MAX && min > arr[a-1][j] + arr[b-1][j]) {
                    min = arr[a-1][j] + arr[b-1][j];
                }
            }
            if (min == MAX)
                cout << -1 << endl;
            else
                cout << min << endl;
        }


    }
    return 0;
}
