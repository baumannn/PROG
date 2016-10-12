#include <iostream>
#include <vector>
#include <queue>
using namespace std;

#define MAX 100001;

int main() {

    int n, c, s, d;
    char c1, c2;
    cin >> n;
    while (n--) {
        cin >> c >> s;
        int vertices = c;
        vector<vector<int> > a(vertices, vector<int>(vertices,MAX));

        vector<int> distances(vertices,MAX);
        distances[0] = 0;

        vector<int> S;

        priority_queue<int> que;

        for(int i = 0; i < vertices; ++i){
            a[i][i] = 0;
        }

        while (s--) {
            cin >> c1 >> c2 >> d;
            a[c1-'A'][c2-'A'] = d;
            a[c2-'A'][c1-'A'] = d;
        }

        for(int k = 0; k < vertices; ++k) {
            for(int i = 0; i < vertices; ++i) {
                for(int j = 0; j < vertices; ++j) {
                    if(a[i][j] > a[i][k] + a[k][j]) {
                        a[i][j] = a[i][k] + a[k][j];

                    }
                }
            }
        }
    }
    return 0;
}
