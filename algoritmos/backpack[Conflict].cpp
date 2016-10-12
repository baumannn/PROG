/*
1
4 16
40 2
30 5
50 10
10 5
*/

#include <iostream>
#include <queue>
using namespace std;
const int MAXOBJ = 50;

//valor, costo, valor/costo de cada objeto
int val [MAXOBJ];
int peso[MAXOBJ];
double vpp[MAXOBJ];

//checa si se esta incluyendo ese objeto
bool inc[MAXOBJ];

int maxx = 0;

//peso maximo que maneja la mochila
int maxweight;


//cantidad de objetos candidatos
int objs;


//contadores de nodos para ambos algoritmos
int btct = 0;
int bbct = 0;


struct node{
    int va, pa, niv;
    double vp;
    bool in[MAXOBJ];
};

class compare {
    public:
    bool operator() (node a, node b) {
        if(a.vp < b.vp){
            return true;
        }
        else if (a.vp == b.vp) {
            if(a.niv < b.niv){
                return true;
            }
        }
        return false;
    }
};

priority_queue <node, vector<node>, compare> pq;


double getvp2(node nn){
    double vp = 0;
    int pac = 0;
    int n = 0;
    //checa todos los objetos
    while(n < objs && pac < maxweight) {
        if(nn.in[n]){
            //si cabe el siguiente objeto entero
            if(pac + peso[n] <= maxweight) {
                ///cout << "add + " << val[n] << endl;
                pac += peso[n];
                vp += val[n];
            }
            // si no cabe entero
            else {
                ///cout << "add - " << vpp[n] << " * ( " << maxweight << " - " << pac << " )" << endl;
                vp += vpp[n]*(maxweight-pac);
                pac = maxweight;
            }
        }
        ++n;
    }
    return vp;
}


void branch(node n) {
    ++bbct;
    if( n.vp > maxx && n.pa <= maxweight){
        if(n.va > maxx) {
            maxx = n.va;
        }
        //checa si es el ultimo nivel
        if(n.niv!=objs) {

            //considera el siquiente objeto
            node nu;
            n.in[n.niv] = true;

            for(int i = 0; i < objs; ++i){
                nu.in[i] = n.in[i];
            }
            nu.niv = n.niv+1;
            nu.pa = n.pa + peso[n.niv];
            nu.va = n.va + val[n.niv];
            nu.vp = n.vp;

            pq.push(nu);

            //no considera el siguiente objeto
            nu.in[n.niv] = false;
            nu.niv = n.niv+1;
            nu.pa = n.pa;
            nu.va = n.va;
            nu.vp = getvp2(nu);

            pq.push(nu);
        }
    }
    return;
}


void bb(node n) {
    pq.push(n);
    while(!pq.empty()){
        node nunode;
        nunode = pq.top();
        pq.pop();
        branch(nunode);
    }
    return;
}


//busca el valor posible
double getvp(){
    double vp = 0;
    int pac = 0;
    int n = 0;
    //checa todos los objetos
    while(n < objs && pac < maxweight) {
        if(inc[n]){
            //si cabe el siguiente objeto entero
            if(pac + peso[n] <= maxweight) {
                ///cout << "add + " << val[n] << endl;
                pac += peso[n];
                vp += val[n];
            }
            // si no cabe entero
            else {
                ///cout << "add - " << vpp[n] << " * ( " << maxweight << " - " << pac << " )" << endl;
                vp += vpp[n]*(maxweight-pac);
                pac = maxweight;
            }
        }
        ++n;
    }
    return vp;
}




void backtrack(int va, int pa, double vp, int niv) {
            /**
            cout << endl << endl;
            for(int i = 0; i < objs; ++i){

                cout << inc[i] << " ";
            }
            cout << endl;
            */
    //se cuenta este nodo
    btct++;
            /**
            if(niv == 0)
                cout << "node: " << va << " " << pa << " " << vp << " " << niv << " | " << "*" << endl;

            else
                cout << "node: " << va << " " << pa << " " << vp << " " << niv << " | " << inc[niv-1]<< endl;
            */
    //checa que se pueda conseguir un mejor valor por aqui
    if( vp > maxx && pa <= maxweight){
        if(va > maxx) {
            maxx = va;
        }
        //checa si es el ultimo nivel
        if(niv!=objs) {
            //considera el siquiente objeto
            inc[niv] = true;
            backtrack(va+val[niv], pa+peso[niv], vp, niv+1);
            //no considera el siguiente objeto
            inc[niv] = false;
            backtrack(va, pa, getvp(), niv+1);
            inc[niv] = true;
        }
    }
}


void sort() {
    int mx;
    int n = objs;
    for (int i = 0; i < n; ++i) {
        mx = i;
        for ( int j = i+1; j < n; ++j) {
            if (vpp[j] > vpp[mx]) {
                mx = j;
            }
        }
        if ( mx != i ) {
            swap(vpp[i], vpp[mx]);
            swap(val[i], val[mx]);
            swap(peso[i], peso[mx]);
        }
    }
}


//falta sort los objetos
int main(){
    int casos, v0, p0;
    cin >> casos;
    for(int k = 0; k < casos; ++k) {

        maxx = 0;
        btct = 0;
        bbct = 0;

        cin >> objs >> maxweight;

        for(int i = 0; i < objs; ++i) {
            inc[i] = true;
        }

        for(int i = 0; i < objs; ++i) {
            cin >> v0 >> p0;
            val[i] = v0;
            peso[i] = p0;
            vpp[i] = ((double)v0)/p0;
        }

        sort();

        backtrack(0, 0, getvp(), 0);

        cout << "Caso " << k+1 << ": "<< maxx << " " << btct << " ";

        //reset
        maxx = 0;
        for(int i = 0; i < objs; ++i) {
            inc[i] = true;
        }

        node nd;
        for(int i = 0; i < objs; ++i) {
            nd.in[i] = inc[i];
        }
        nd.niv = 0;
        nd.pa = 0;
        nd.va = 0;
        nd.vp = getvp();
        bb(nd);

        cout << bbct << endl;

    }


    return 0;
}
