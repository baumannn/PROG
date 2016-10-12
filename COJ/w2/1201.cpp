#include <queue>
#include <iostream>

using namespace std;

int main() {
    int cases, n, num, maxweight;
    bool got;
    cin >> cases;
    while (cases--){
        cin >> n;
        priority_queue <int, vector<int>, greater<int> > q;
        for(int i = 0; i < n; ++i) {
            cin >> num;
            q.push(num);
        }



        got = false;

        maxweight = 0;
        while(!q.empty()) {

            if (maxweight < q.top()*n) {
                //cout << maxweight << " < " << q.top()*n << " = " << q.top() << " * " << n << endl;
                maxweight = q.top()*n;
            }
            --n;
            q.pop();
        }
        cout << maxweight << endl;
    }

    return 0;
}
