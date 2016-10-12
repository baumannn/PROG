#include <iostream>
#include <vector>
#include <algorithm>
#include <stdio.h>
#include <queue>

using namespace std;

int arr1 [100000], arr2 [100000];

int main()
{
    int soldiers, in, cont = 0, mini = 1000000000;

    cin>>soldiers;

    priority_queue <int, vector<int>, greater<int> > q1;
    priority_queue <int, vector<int>, greater<int> > q2;

    for(int i=0; i<soldiers; i++)
    {
        cin>>in;
        q1.push(in);
    }

    for(int i=0; i<soldiers; i++)
    {
        cin>>in;
        q2.push(in);
    }

    while(!q2.empty())
    {
        if(q2.top()>q1.top())
        {
            cont++;
            q1.pop();
            q2.pop();
        }
        else
        {
            q2.pop();
        }
    }

    printf("%d\n",cont);

    return 0;
}
