#include <iostream>
using namespace std;
int main(){
    int n, arr[100][1000], arr2[100][1000];

    cin >> n;

    while(n != 0) {

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j <= i; ++j) {
                cin >> arr[i][j];
                if(i > 0) {
                    arr[i][j] += arr[i-1][j];
                }
            }
        }



        for(int j = 0; j < n; ++j) {
            for(int i = j; i < n; ++i) {
                if(j > 0) {
                    arr2[i][j] = arr2[i-1][j-1] + arr[i][j];
                }
                else
                    arr2[i][j] = arr[i][j];
            }
            for(int i = n-2; i >= j; --i) {
                if(arr2[i][j] < arr2[i+1][j])
                    arr2[i][j] = arr2[i+1][j];
            }

        }

        int max = 0;
        for(int i = 0; i < n; ++i) {
            if(max < arr2[i][i])
                max = arr2[i][i];
        }
        cout << max << endl;



        cin >> n;
    }


    return 0;
}
