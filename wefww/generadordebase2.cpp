#include <iostream>
#include <fstream>
using namespace std;

int main()
{
    ofstream archivo;
    unsigned long long sum=1;
    archivo.open("base2.txt");

    sum=1;
    for(int i=0; i<=62; i++)
    {
        sum*=2;
        archivo<<sum<<",";
    }
    archivo.close();
}
