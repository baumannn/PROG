#include <iostream>
#include <string>

using namespace std;

string arr [50];
int check [51], check2 [51];

int main()
{
    int cases, strings, totalSize;
    string in, out, big;

    cin>>cases;

    for(int i = 0; i<cases; i++)
    {
        cin>>strings;
        totalSize = 0;
        out = "";

        for(int j=0;j<strings;j++)
        {
            cin>>in;
            arr[i]=in;
            totalSize += arr[i].length();
        }

        while(out.length() != totalSize)
        {
            big = "A";
            int cont = 0, ind, cont2 =0;
            while(cont!=1)
            {
                if(cont2==0)
                {
                    for(int j=0; j<strings; j++)
                    {
                        if(big[0] < arr[j][0] && arr[j] != "")
                        {
                            big = arr[j][0];
                            cont = 1;
                            check [cont] = j;
                            ind = j;
                        }
                        else if(big[0] == arr[j][0])
                        {
                            check [cont] = j;
                            cont++;
                        }
                    }
                }
                else
                {
                    for(int j=0; j<cont; j++)
                    {
                        if(big[0] < arr[check[j]][cont2] && arr[j] != "")
                        {
                            big = arr[check[j]][cont2];
                            cont = 1;
                            ind = j;
                        }
                        else if(big[0] == arr[check[j]][cont2])
                        {
                            check2 [cont] = j;
                            cont++;
                        }
                    }
                }
                cont2++;
            }
            out += big;
            arr[ind].erase(0,1);
        }
        cout<<out<<endl;
    }

    return 0;
}
