#include <iostream>
using namespace std;

int arr[256][256];
int num = 1;


void triomino(int n, int a, int b){
	if(n==2){
		//completar caso base
		for(int i = 0; i < n; ++i){
			for(int j = 0; j < n; ++j){
				if(arr[a+i][b+j] == 0){
					arr[a+i][b+j] = num;
				}
			}
		}
		++num;
	}

	else{
		int x = a, y = b;

		//encontrar cuadro ocupado
		for(int i = a; i < a + n; ++i){
			for(int j = b; j < b + n; ++j){
				if(arr[i][j] != 0){
					x = i;
					y = j;
				}
			}
		}
		
		//si esta en cuadrante 1
		if(x < a + n/2 && y < b + n/2){
			//recursiva en cuadrante 1
			triomino(n/2, a, b);
			
			//crea un triomino que cubre un cuadro de cada otro cuadrante
			arr[a+n/2][b+n/2-1] = num;
			arr[a+n/2][b+n/2] = num;
			arr[a+n/2-1][b+n/2] = num;

			++num;

			//recursiva en los otros 3 cuadrantes
			triomino(n/2, a, b + n/2);
			triomino(n/2, a + n/2, b + n/2);
			triomino(n/2, a + n/2, b);

		}
		//si esta en cuadrante 2
		else if(x < a + n/2 && y >= b + n/2){
			triomino(n/2, a, b + n/2);
			
			arr[a+n/2][b+n/2-1] = num;
			arr[a+n/2][b+n/2] = num;
			arr[a+n/2-1][b+n/2-1] = num;

			++num;

			triomino(n/2, a, b);
			triomino(n/2, a + n/2, b + n/2);
			triomino(n/2, a + n/2, b);
		}
		//si esta en cuadrante 3
		else if(x >= a + n/2 && y < b + n/2){
			triomino(n/2, a + n/2, b);
			
			arr[a+n/2-1][b+n/2] = num;
			arr[a+n/2][b+n/2] = num;
			arr[a+n/2-1][b+n/2-1] = num;

			++num;

			triomino(n/2, a, b);
			triomino(n/2, a, b + n/2);
			triomino(n/2, a + n/2, b + n/2);
		}
		//si esta en cuadrante 4
		else{
			triomino(n/2, a + n/2, b + n/2);
			
			arr[a+n/2-1][b+n/2] = num;
			arr[a+n/2][b+n/2-1] = num;
			arr[a+n/2-1][b+n/2-1] = num;

			++num;

			triomino(n/2, a, b);
			triomino(n/2, a, b + n/2);
			triomino(n/2, a + n/2, b);
		}
	}
}

void initArr(int a, int b){
	arr[a][b] = -1;
}

void print(int n){
	for(int i = 0; i < n; ++i){
		for(int j = 0; j < n; ++j){
			if(arr[i][j] == -1)
				cout << " " << arr[i][j] << " ";
			else if(arr[i][j] < 10)
				cout << "  " << arr[i][j] << " ";
			else if(arr[i][j] < 100)
				cout << " " << arr[i][j] << " ";
			else cout << arr[i][j] << " ";
		}
		cout << endl;
	}
}

int main(){
	int n, a, b;
	cout << "size, i, j: ";
	cin >> n >> a >> b;

	initArr(a, b);
	cout << endl;
	triomino(n,0,0);
	print(n);
	cout << endl;

	system("pause");
	return 0;
}