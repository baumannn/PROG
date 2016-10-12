#include <iostream>
#include <queue>
using namespace std;

struct test{
    int a, b;
};

class compare {
    public:
    bool operator() (test a, test b) {
        if(a.a > b.a){
            return true;
        }
        return false;
    }
};

int main(){
    int n, a ,b;
    priority_queue < test,vector<test>, compare> pq;
    cin >> n;

    for (int i =0; i < n; ++i) {
        cin >> a >> b;
        test aa;
        aa.a = a;
        aa.b = b;
        pq.push(aa);
    }
    for (int i =0; i < n; ++i) {
        cout << pq.top().a << " " <<pq.top().b << endl;
        pq.pop();
    }


}
