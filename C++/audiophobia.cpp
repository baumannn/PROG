#include <iostream>
#include <vector>
using namespace std;

int MAX = 999999999;

int main() {

    int n, c, s, q, c1, c2, d, num = 1;
    cin >> n;
    while (n--) {
        cin >> c >> s >> q;
        int vertices = c;
        vector<vector<int> > a(vertices, vector<int>(vertices,MAX));
        for(int i = 0; i < vertices; ++i){
            a[i][i] = 0;
        }

        while (s--) {
            cin >> c1 >> c2 >> d;
            a[c1-1][c2-1] = d;
            a[c2-1][c1-1] = d;
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

        cout << "Case " << num << ":" << endl;
        while (q--) {
            cin >> c1 >> c2;
            if (a[c1-1][c2-1] == MAX)
                cout << "no path" << endl;
            else
                cout << a[c1-1][c2-1] << endl;
        }
        num++;
    }
    return 0;
}
