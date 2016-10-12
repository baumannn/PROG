#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

double PI = 3.14159265;

double fun1(double n){
    return (cos(n*PI/180) + sin(n*PI/180))/(cos(n*PI/180)*sin(n*PI/180));
}

double func(double h, double n){
    return ((fun1(n)-1/h)/(fun1(n)-2/h)-h/2);
}


double sec(double in){
    double x0 = 0.000001, x1 = 45;
    double x2 = x1;
    int lim = 0;
    while(lim < 100){
            ++lim;
        x2 = x1 - ((func(in, x1)*(x1-x0))/(func(in,x1)-func(in,x0)));
        double check = func(in,x2);
        if(abs(check) < 0.000001)
            break;
        x0 = x1;
        x1 = x2;
    }

    return x2;

}

int main(){
    double in;
    while(cin >> in){
        cout << sec(in) << setprecision(5)<< endl;
    }
    return 0;
}
