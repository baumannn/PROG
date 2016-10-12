#include <iostream>
using namespace std;

///tamaño maximo de arreglos
const int MAX = 11;

int abs(int n) {
    if(n < 0)
        return -1*n;
    return n;
}

///imprime arr
void print (double arr[MAX][MAX], int n) {
    cout << endl;
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < n + 1; ++j) {
            if(j == n)
                cout << "| ";
        //    if(abs(arr[i][j]) < 0.001 && arr[i][j] != 0)
          //      cout << "o ";
           // else
                cout << arr[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
    return;
}

///checa si a esta en arr de tamaño n
bool isin (int arr[MAX], int a, int n) {
    for(int i = 0; i < n; ++i) {
        if(arr[i] == a) {
            return true;
        }
    }
    return false;
}

int count = -1; ///para insertar elementos en cols y rows
int cols[MAX]; ///columnas que ya no son validas, en count es la ultima que se accedio
int rows[MAX]; ///filas que ya no son validas, en count es la ultima que se accedio
int vals[MAX]; ///indices en donde se encuentran los variables, arr[vals[i]][n] = x_i

///encuentra pivoote en area valida, hace invalidas la fila y columna
double piv(double arr[MAX][MAX], int n) {
    double max = arr[0][0];
    int maxcol = 0, maxrow = 0;
    for(int i = 0; i < n; ++i) {
        int c = 0;
        int t = 0;
        for(int j = 0; j < n; ++j) {
            if(arr[i][j] == 0){
                ++c;
            }
            else t = j;
        }
        if(c == 3){
            vals[t] = i;
            cols[count] = t;
            rows[count] = i;
            return arr[i][t];
        }
        if(isin(rows,i,n)) {
            continue;
        }
        for(int j = 0; j < n; ++j) {
            if(isin(cols,j,n)) {
                continue;
            }
            if(arr[i][j] > max || -1*arr[i][j] > max) {
                max = arr[i][j];
                vals[j] = i;
                maxrow = i;
                maxcol = j;
            }
        }
    }
    ++count;
    cols[count] = maxcol;
    rows[count] = maxrow;
    return max;
}

int main() {
    int n;
    cout << "N: ";
    cin >> n;
    double arr[MAX][MAX];
    for(int i = 0; i < n; ++i) {
        rows[i] = -1;
        cols[i] = -1;
        for(int j = 0; j <= n; ++j) {
            if(j==n)
                cout << "eq " << i + 1 << " y: ";
            else
                cout << "eq " << i + 1 << " x_" << j << ": ";
            cin >> arr[i][j];
        }
    }
    //\
    print(arr,n);
    for(int i = 0; i < n; ++i) {
        double val = piv(arr,n);
        for(int j = 0; j < n; ++j) {
            if(j != rows[count]) {
                double div = arr[j][cols[count]];
                for(int k = 0; k <= n; ++k) {
                    /*cout << "SUB: " << arr[j][k] << " - " << div * arr[rows[count]][k] / val << " = "
                        << div << " * " << arr[rows[count]][k] << " / " << val << endl;*/
                    arr[j][k] -= div * arr[rows[count]][k] / val;
                }
            }
        }
        for(int k = 0; k <= n; ++k) {
            arr[rows[count]][k] /= val;
        }
        //
        print(arr,n);
    }



    for(int i = 0; i < n; ++i) {
        cout << "x_" << i << ": " << arr[vals[i]][n] << endl;
    }


    return 0;
}
