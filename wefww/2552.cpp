#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;
    double arr[n];
    for(int i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    double sum, max;
    sum = max = arr[0];

    for(int i = 1; i < n; ++i) {
        sum += arr[i];
        if(arr[i] > max) {
            max = arr[i];
        }
    }

    if(max >= (sum * 45.0)/100.0) {
        cout << "1\n";
        return 0;
    }
    else {
        if(max >= (sum * 40.0)/100.0) {
            for(int i = 0; i < n; ++i) {
                if(arr[i] != 0 && (max/sum - arr[i]/sum) >= 10) {
                    cout << "2\n";
                    return 0;
                }
            }
            cout << "1\n";
            return 0;
        }
        else {
            cout << "2\n";
            return 0;
        }

    }
}
