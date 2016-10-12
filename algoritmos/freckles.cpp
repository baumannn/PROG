#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

#define MAX 99999

double dist(double x1, double y1, double x2, double y2) {
    if(x1==x2 && y1==y2)
        return 0;
    return sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
}

int main() {
    int n, visited[100] = {1}, ne = 1, a, b, u, v;

    double x, y, vals[2][100], mat[100][100], ink = 0, mincost = 0;
    cin >> n;

    for(int i = 1; i < n; ++i){
        visited[i] = 0;
    }

    for(int i = 0; i < n; ++i) {
        cin >> x >> y;
        vals[0][i] = x;
        vals[1][i] = y;
    }

    for(int i = 0; i < n; ++i) {
        for(int j = i; j < n; ++j) {
            if(i == j) {
                mat[i][j] = 0;
            }
            else
                mat[i][j] = mat[j][i] = dist(vals[0][i], vals[1][i], vals[0][j], vals[1][j]);
            if(mat[i][j] == 0)
                mat[i][j] = MAX;
        }
    }

    while(ne < n) {
        double min = MAX;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if(mat[i][j] < min) {
                    if(visited[i] != 0) {
                        min = mat[i][j];
                        a = u = i;
                        b = v = j;
                    }
                }
            }
        }
        if(visited[u] == 0 || visited[v] == 0) {
            mincost += min;
            cout << a << ", " << b << "|  cost: " << min << endl;
            visited[b] = 1;
            ne++;
        }
        mat[a][b] = mat[b][a] = MAX;
    }

    cout.precision(2);
    cout << fixed << mincost << endl;

    return 0;
}
