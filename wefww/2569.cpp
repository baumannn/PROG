#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main(){

    int cases;
    cin>>cases;

    while(cases)
    {
        int n;
        cin>>n;

        vector<string> s_array;
        string temp_string, s;

        for (int i=0; i<n; i++)
        {

            cin>>temp_string;
            s_array.push_back(temp_string);
        }

        char largest;
        bool b_empty=false;

        while(!s_array.empty())
        {

            int j=0, temp=0;
            char largest='{';
            bool conflict=true;

            while(conflict){
                for(int i=0; i < s_array.size(); i++)
                {
                        if(s_array[i].size()==0)
                        {
                            s_array.erase(s_array.begin()+i);
                            conflict=false;
                        }

                        else if(j<s_array[i].size()){
                            if( s_array[i][j] > largest)
                            {
                                largest=s_array[i][j];
                                temp=i;//array to delete;
                                conflict=false;
                            }
                            else if( s_array[i][j] == largest){
                                conflict=true;
                            }
                    }
                }
                j++;
            }

            s+=largest;
            s_array[temp].erase(0,1);


        }
        cout<<s;

        //******
        cases--;
    }

}
