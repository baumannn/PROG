#include <iostream>
#include <vector>
using namespace std;

typedef pair<int,int> ii;
typedef vector<ii> vii;

const int MAXINT = 100000000;

int main() {
    int n;
    cin >> n;
    while (n--) {
        int rooms, connections, dist;
        cin >> rooms >> connections;
        int W[rooms][rooms], L[rooms], T[rooms];
        vii S;
        for(int i = 0; i < rooms; ++i) {
            for(int j = 0; j < rooms; ++j) {
             W[i][j] = MAXINT;
            }
        }

        char a, b;
        for(int i = 0; i < connections; ++i) {
            cin >> a >> b >> dist;
            W[a - 'A'][b - 'A'] = dist;
        }

        for(int i = 1; i < rooms; ++i) {
            L[i] = W[0][i];
            T[i] = 0;
        }

        for(int i = 1; i < rooms; ++i) {
            int min = MAXINT + 1, vmin;
            for(int j = 1; j < rooms; ++j) {
                if(0 <= L[j] && L[j] <= min) {
                   // cout << "L[" << j << "] = " << L[j] << ", p_min = " << min << endl;
                    min = L[j];
                    vmin = j;
                }
            }
            ii e = make_pair(T[vmin],vmin);
            S.push_back(e);


    //        cout << endl << "! " << vmin << " _._ " << L[vmin] << endl;\
            cout << e.first << ", " << e.second << endl;


            for(int i = 0; i < S.size(); ++i) {
          //      cout << "$ " << S[i].first << ", " << S[i].second << " ";
            }
            cout << endl;

            for(int j = 1; j < rooms; ++j) {
                if(L[vmin] + W[vmin][j] < L[j]) {
                    L[j] = L[vmin] + W[vmin][j];
                    T[j] = vmin;
                }
            }
            L[vmin] = -1;
        }

        for(int i = 0; i < S.size(); ++i) {
            cout << S[i].first << ", " << S[i].second << endl;
        }

    }

    return 0;
}
