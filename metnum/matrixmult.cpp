#include <iostream>
using namespace std;


#define MAX 50

void prob1() {
    int ax, ay, bx, by, sum;
    bool flag;
    int A[MAX][MAX], B[MAX][MAX], C[MAX][MAX];
    do {
        flag = false;
        cout << "A rows: ";
        cin >> ax;
        cout << "A cols: ";
        cin >> ay;
        cout << "B rows: ";
        cin >> bx;
        cout << "B cols: ";
        cin >> by;

        if(ax != by || ax > MAX || ay > MAX || bx > MAX|| by > MAX) {
            cout << "ERROR" << endl;
            flag = true;
        }
    } while (flag);

    ///input
    cout << "A:" << endl;
    for(int i = 0; i < ax; ++i) {
        for(int j = 0; j < ay; ++j) {
            cin >> A[i][j];
        }
    }
    cout << "B:" << endl;
    for(int i = 0; i < bx; ++i) {
        for(int j = 0; j < by; ++j) {
            cin >> B[i][j];
        }
    }

    ///comptutation
    for(int i = 0; i < ax; ++i) {
        for(int j = 0; j < by ; ++j) {
            sum = 0;
            for(int k = 0; k < ay; ++k) {
                sum += A[i][k]*B[k][j];
            }
            C[i][j] = sum;
        }
    }

    ///output
    for(int i = 0; i < ax; ++i) {
        for(int j = 0; j < ax; ++j) {
            cout << C[i][j] << " ";
        }
        cout << endl;
    }

    return;
}

void prob2() {
    int A[MAX][MAX];
    int x, y, sum, bigsum = 0;
    cout << "Rows: ";
    cin >> x;
    cout << "Cols: ";
    cin >> y;

    cout << "Matrix: " << endl;
    for(int i = 0; i < x; ++i) {
        for(int j = 0; j < y; ++j) {
            cin >> A[i][j];
        }
    }

    ///sumrows
    for(int i = 0; i < x; ++i) {
        sum = 0;
        for(int j = 0; j < y; ++j) {
            sum += A[i][j];
            bigsum += A[i][j];
        }
        A[i][y] = sum;
    }

    ///sumcols
    for(int i = 0; i < y; ++i) {
        sum = 0;
        for(int j = 0; j < x; ++j) {
            sum += A[j][i];
            bigsum += A[j][i];
        }
        A[x][i] = sum;
    }

    A[x][y] = bigsum;

    ///out
    for(int i = 0; i <= x; ++i) {
        for(int j = 0; j <= y; ++j) {
            cout << A[i][j] << " ";
        }
        cout << endl;
    }

    return;
}

bool prob3() {
    int n, A[MAX][MAX], sum, comp;
    cout << "N: ";
    cin >> n;

    cout << "Matrix:" << endl;

    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < n; ++j) {
            cin >> A[i][j];
        }
    }

    for(int i = 0; i < n; ++i) {
        sum = 0;
        for(int j = 0; j < n; ++j) {
            if(i==j){
                comp = A[i][j];
            }
            else
                sum += A[i][j];
        }
        if(comp <= sum) {
            cout << "Not diagoanlly dominant." << endl;
            return false;
        }
    }

    cout << "Diagoanlly dominant." << endl;
    return true;
}

int main() {

    ///prob1();
    ///prob2();
    prob3();

    return 0;
}
