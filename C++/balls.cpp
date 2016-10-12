#include <iostream>
using namespace std;

#define A 100


void print(int arr[A][A], int n){
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j <= i; ++j) {
            if(arr[i][j] == 0) {
                cout << "  0 ";
            }
            else if(arr[i][j] < -9) {
                cout << arr[i][j] << " ";
            }
            else if(arr[i][j] < 0) {
                cout << " " << arr[i][j] << " ";
            }

            else if(arr[i][j] > 9) {
                cout << " " << arr[i][j] << " ";
            }

            else
                cout << "  " << arr[i][j] << " ";


        }
        cout << endl;
    }
    cout << endl;
    return;
}



void clean(int arr[A][A], int n){
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < n; ++j) {
            arr[i][j] = 0;
        }
    }
    return;
}

void get(int arra[A][A], int arrb[A][A], int n) {
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j <= i; ++j) {
            if(i==0 && j==0) {
                arrb[i][j] = arra[i][j];
            }
            else if(j==0) {
                arrb[i][j] = arra[i][j] + arrb[i-1][j];
            }
            else if(j==i) {
                arrb[i][j] = arra[i][j] + arrb[i-1][j-1];
            }
            else {
                arrb[i][j] = arra[i][j] + arrb[i-1][j-1] + arrb[i-1][j] - arrb[i-2][j-1];
            }
        }
    }
}

//return max, pos is i,j


int mx(int arr[A][A], int n, int &ii, int &jj) {
    int m = 0;
    ii = jj = -1;
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j <= i; ++j) {
            if(arr[i][j] >= m) {
                m = arr[i][j];
                ii = i;
                jj = j;
            }
        }
    }
    return m;

}

void grab(int arr[A][A], int ii, int jj) {
    int n = 1;
    for(int i = ii; i >= 0; --i) {
        for(int j = jj; j > jj-n && jj >= 0; --j) {
            arr[i][j] = 0;
        }
        ++n;
    }

}

int main(){

    int arr[A][A], val[A][A], n, max, geti, getj, points;
    cin >> n;
    while(n != 0){
        points = 0;
        clean(arr, n);
        clean(val, n);
        for(int i = 0; i < n; ++i){
            for(int j = 0; j <= i; ++j) {
                cin >> arr[i][j];
            }
        }
        get(arr, val, n);
        print(arr,n);
        print(val,n);
        max = mx(val, n, geti, getj);
        if(max > 0) {
            grab(arr, geti, getj);
            points += max;
        }
        get(arr, val, n);
        print(arr,n);
        print(val,n);

        cout << points << endl;
        cin >> n;
    }


    return 0;
}
