#include <iostream>
#include <vector>
using namespace std;

typedef pair<int,int> ii;
typedef vector<ii> vii;

const int MAXINT = 1000000;

int main() {
    int n;
    cin >> n;
    for(int nn = 0; nn < n; ++nn) {
        int rooms, connections, dist;
        cin >> rooms >> connections;

        int W[rooms][rooms], L[rooms], T[rooms], dis[rooms];
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

        for(int i = 0; i < rooms; ++i) {
            L[i] = W[0][i];
            T[i] = 0;
            dis[i] = 0;
        }

        for(int i = 1; i < rooms; ++i) {
            int min = MAXINT, vmin;
            for(int j = 1; j < rooms; ++j) {
                if(0 <= L[j] && L[j] <= min) {
                    min = L[j];
                    vmin = j;
                }
            }


            dis[vmin] = dis[T[vmin]] + W[T[vmin]][vmin];
            ii e = make_pair(dis[vmin],vmin);
            S.push_back(e);


            for(int j = 1; j < rooms; ++j) {
                if(L[vmin] + W[vmin][j] < L[j]) {
                    L[j] = L[vmin] + W[vmin][j];
                    T[j] = vmin;
                }
            }
            L[vmin] = -1;

        }

        int max = S[0].first;
        int maxindex = S[1].second;
        for(int i = 1; i < S.size(); ++i) {
            if(S[i].first > max) {
                max = S[i].first;
                maxindex = S[i].second;
            }
            else if(S[i].first == max) {
                if(maxindex > S[i].second) {
                    maxindex = S[i].second;
                }
            }

        }

        char c = maxindex + 'A';

        for(int i = 0; i < S.size(); ++i) {
            cout << S[i].first << " ";
        }

        cout << endl;
        for(int i = 0; i < S.size(); ++i) {
            cout << S[i].second << " ";
        }

        cout << endl;
        cout << "Case " << nn + 1 << ": " << c << endl;

    }

    return 0;
}
