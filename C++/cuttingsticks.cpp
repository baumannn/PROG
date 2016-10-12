#include <iostream>

using namespace std;

int main(){
	int n;

	while(1){

        cin >> n;
        if(n == 0) {
            break;
        }

		int cuts;
		cin>>cuts;

		int line[cuts+1];
		int arr[cuts+1][cuts+1];


		for(int i = 0; i < cuts; ++i){
			cin >> line[i];
		}

		line[cuts]=n;

		for(int i = cuts; i > 0; --i){
			line[i] -= line[i-1];
		}

		for(int i = 0; i <= cuts; ++i){
			arr[i][i] = 0;
		}

		for (int h = 1; h <= cuts; ++h) {
			for (int i = 0; i <= cuts - h; ++i) {
				int j = i + h;
				int k = i;
				int min = arr[i][k] + arr[k+1][j];
				for( ; k < j; ++k){
					if(arr[i][k] + arr[k+1][j] < min){
						min = arr[i][k] + arr[k+1][j];
					}
				}
				for(int l = i; l <= j; ++l){
					min += line[l];
				}
				arr[i][j] = min;
			}
		}

		cout << "The minimum cutting is " << arr[0][cuts] << "." << endl;
	}

	return 0;
}
