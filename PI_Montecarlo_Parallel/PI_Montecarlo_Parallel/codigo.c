#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <Windows.h>

#define CANTIDAD_THREADS 8

clock_t start,end;

long M=0;
long N;
long NporThread;
CRITICAL_SECTION cs;

DWORD WINAPI rutina(LPVOID p) {

	long i;
	double x, y;
	long MLocal=0;

	for(i=0;i<NporThread;i++) {
		x=((double)rand())/RAND_MAX;
		y=((double)rand())/RAND_MAX;f
		if(sqrt(x*x+y*y) < 1.0){
			MLocal++;
		}
	}

	EnterCriticalSection(&cs);
	M+=MLocal;
	LeaveCriticalSection(&cs);
	return 0;
}

void main() {
	
	int i;
	
	HANDLE tHandle[CANTIDAD_THREADS];

	printf("Cuantos intentos?");
	scanf("%d",&N);

	start = clock();
	NporThread = N/CANTIDAD_THREADS;
	srand((unsigned)time(NULL));
//-------------------------
	InitializeCriticalSection(&cs);
	for(i = 0; i < CANTIDAD_THREADS;i++) {
		tHandle[i] = CreateThread(NULL,0,&rutina,NULL,0,NULL);
	}
	WaitForMultipleObjects(CANTIDAD_THREADS,&tHandle[0],TRUE,INFINITE);
	DeleteCriticalSection(&cs);
//-------------------------
	end = clock();
	printf("PI=%20.18lf ... (%ld)\n",(4.0*M)/N,end-start);
	
	scanf("%d",&N);
}