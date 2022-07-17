#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <time.h>
#include <vector>
#include <windows.h>
#include <memory.h>
#include <string.h>
#include <iomanip>

#define DEBUG

using namespace std;

#define POT_SIZE 52 //散列点数量


typedef struct candidate
{
    int x;
    int y;
}pot, POTS;


int** Delta;


typedef struct Solution
{
    int permutation[POT_SIZE]; 
    int cost;                     
}SOLUTION;
 
int calc_delta(int i, int k, int* tmp, POTS* pots);

int distance_2pot(pot c1, pot c2);


int cost_total(int* pots_permutation, POTS* pots);

void random_permutation(int* pots_permutation);

void swap_element(int* p, int begin, int end);


void two_opt_swap(int* pots_permutation, int* new_pots_permutation, int index_i, int index_j);


void local_search(SOLUTION& best, POTS* pots, int max_no_improve);

bool AcceptanceCriterion(int* pots_permutation, int* old_pots_permutation, POTS* p_pots);



void double_bridge_move(int* pots_permutation, int* new_pots_permutation);

void perturbation(POTS* pots, SOLUTION& best_solution, SOLUTION& current_solution);


void iterated_local_search(SOLUTION& best, POTS* pots, int max_iterations, int max_no_improve);


void Update(int i, int k, int* tmp, POTS* pots);

int permutation[POT_SIZE];

POTS pots[POT_SIZE];


//berlin52散列点坐标，最优解7542好像
POTS berlin52[POT_SIZE] = { { 565,575 },{ 25,185 },{ 345,750 },{ 945,685 },{ 845,655 },
{ 880,660 },{ 25,230 },{ 525,1000 },{ 580,1175 },{ 650,1130 },{ 1605,620 },
{ 1220,580 },{ 1465,200 },{ 1530,5 },{ 845,680 },{ 725,370 },{ 145,665 },
{ 415,635 },{ 510,875 },{ 560,365 },{ 300,465 },{ 520,585 },{ 480,415 },
{ 835,625 },{ 975,580 },{ 1215,245 },{ 1320,315 },{ 1250,400 },{ 660,180 },
{ 410,250 },{ 420,555 },{ 575,665 },{ 1150,1160 },{ 700,580 },{ 685,595 },
{ 685,610 },{ 770,610 },{ 795,645 },{ 720,635 },{ 760,650 },{ 475,960 },
{ 95,260 },{ 875,920 },{ 700,500 },{ 555,815 },{ 830,485 },{ 1170,65 },
{ 830,610 },{ 605,625 },{ 595,360 },{ 1340,725 },{ 1740,245 } };

int main()
{
    srand(1);
    int max_iterations = 600;
    int max_no_improve = 50;
    Delta = new int* [POT_SIZE];
    for (int i = 0; i < POT_SIZE; i++)
        Delta[i] = new int[POT_SIZE];

    SOLUTION best_solution;

    iterated_local_search(best_solution, berlin52, max_iterations, max_no_improve);

    cout << endl << endl << "搜索完成！ 最优路线总长度 = " << best_solution.cost << endl;
    cout << "最优访问散列点序列如下：" << endl;
    for (int i = 0; i < POT_SIZE; i++)
    {
        cout << setw(4) << setiosflags(ios::left) << best_solution.permutation[i];
    }

    cout << endl << endl;

    return 0;
}




int distance_2pot(pot c1, pot c2)
{
    int distance = 0;
    distance = sqrt((double)((c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y)));

    return distance;
}


int cost_total(int* pots_permutation, POTS* pots)
{
    int total_distance = 0;
    int c1, c2;
 
    for (int i = 0; i < POT_SIZE; i++)
    {
        c1 = pots_permutation[i];
        if (i == POT_SIZE - 1) //最后一个散列点和第一个散列点计算距离
        {
            c2 = pots_permutation[0];
        }
        else
        {
            c2 = pots_permutation[i + 1];
        }
        total_distance += distance_2pot(pots[c1], pots[c2]);
    }

    return total_distance;
}


void random_permutation(int* pots_permutation)
{
    int i, r, temp;
    for (i = 0; i < POT_SIZE; i++)
    {
        pots_permutation[i] = i; //初始化散列点排列，初始按顺序排
    }


    for (i = 0; i < POT_SIZE; i++)
    {
        //散列点排列顺序随机打乱
        r = rand() % (POT_SIZE - i) + i;
        temp = pots_permutation[i];
        pots_permutation[i] = pots_permutation[r];
        pots_permutation[r] = temp;
    }
}





void swap_element(int* p, int begin, int end)
{
    int temp;
    while (begin < end)
    {
        temp = p[begin];
        p[begin] = p[end];
        p[end] = temp;
        begin++;
        end--;
    }
}


void two_opt_swap(int* pots_permutation, int* new_pots_permutation, int index_i, int index_j)
{
    for (int i = 0; i < POT_SIZE; i++)
    {
        new_pots_permutation[i] = pots_permutation[i];
    }

    swap_element(new_pots_permutation, index_i, index_j);
}



int calc_delta(int i, int k, int* tmp, POTS* pots) {
    int delta = 0;

    if (i == 0)
    {
        if (k == POT_SIZE - 1)
        {
            delta = 0;
        }
        else
        {
            delta = 0
                - distance_2pot(pots[tmp[k]], pots[tmp[k + 1]])
                + distance_2pot(pots[tmp[i]], pots[tmp[k + 1]])
                - distance_2pot(pots[tmp[POT_SIZE - 1]], pots[tmp[i]])
                + distance_2pot(pots[tmp[POT_SIZE - 1]], pots[tmp[k]]);
        }

    }
    else
    {
        if (k == POT_SIZE - 1)
        {
            delta = 0
                - distance_2pot(pots[tmp[i - 1]], pots[tmp[i]])
                + distance_2pot(pots[tmp[i - 1]], pots[tmp[k]])
                - distance_2pot(pots[tmp[0]], pots[tmp[k]])
                + distance_2pot(pots[tmp[i]], pots[tmp[0]]);
        }
        else
        {
            delta = 0
                - distance_2pot(pots[tmp[i - 1]], pots[tmp[i]])
                + distance_2pot(pots[tmp[i - 1]], pots[tmp[k]])
                - distance_2pot(pots[tmp[k]], pots[tmp[k + 1]])
                + distance_2pot(pots[tmp[i]], pots[tmp[k + 1]]);
        }
    }

    return delta;
}




void Update(int i, int k, int* tmp, POTS* pots) {
    if (i && k != POT_SIZE - 1) {
        i--; k++;
        for (int j = i; j <= k; j++) {
            for (int l = j + 1; l < POT_SIZE; l++) {
                Delta[j][l] = calc_delta(j, l, tmp, pots);
            }
        }

        for (int j = 0; j < k; j++) {
            for (int l = i; l <= k; l++) {
                if (j >= l) continue;
                Delta[j][l] = calc_delta(j, l, tmp, pots);
            }
        }
    }
    else {
        for (i = 0; i < POT_SIZE - 1; i++)
        {
            for (k = i + 1; k < POT_SIZE; k++)
            {
                Delta[i][k] = calc_delta(i, k, tmp, pots);
            }
        }
    } 

}


void local_search(SOLUTION& best_solution, POTS* pots, int max_no_improve)
{
    int count = 0;
    int i, k;

    int inital_cost = best_solution.cost; //初始花费

    int now_cost = 0;

    SOLUTION* current_solution = new SOLUTION; //为了防止爆栈……直接new了，你懂的

    for (i = 0; i < POT_SIZE - 1; i++)
    {
        for (k = i + 1; k < POT_SIZE; k++)
        {
            Delta[i][k] = calc_delta(i, k, best_solution.permutation, pots);
        }
    }

    do
    {
    
        for (i = 0; i < POT_SIZE - 1; i++)
        {
            for (k = i + 1; k < POT_SIZE; k++)
            {
          
                two_opt_swap(best_solution.permutation, current_solution->permutation, i, k);
                now_cost = inital_cost + Delta[i][k];
                current_solution->cost = now_cost;
                if (current_solution->cost < best_solution.cost)
                {
                    count = 0; //better cost found, so reset
                    for (int j = 0; j < POT_SIZE; j++)
                    {
                        best_solution.permutation[j] = current_solution->permutation[j];
                    }
                    best_solution.cost = current_solution->cost;
                    inital_cost = best_solution.cost;
                    Update(i, k, best_solution.permutation, pots);
                }

            }
        }

        count++;

    } while (count <= max_no_improve);
}

bool AcceptanceCriterion(int* pots_permutation, int* old_pots_permutation, POTS* p_pots)
{
    int acceptance = 500; //接受条件,与当前最解相差不超过acceptance
    int old_cost = cost_total(old_pots_permutation, p_pots);
    int new_cost = cost_total(pots_permutation, p_pots);

    if ((new_cost <= (old_cost + acceptance)) || (new_cost >= (old_cost - acceptance)))
    {
        return true;
    }

    return false;
}

void double_bridge_move(int* pots_permutation, int* new_pots_permutation)
{
    int temp_perm[POT_SIZE];

    int pos1 = 1 + rand() % (POT_SIZE / 4);
    int pos2 = pos1 + 1 + rand() % (POT_SIZE / 4);
    int pos3 = pos2 + 1 + rand() % (POT_SIZE / 4);

    int i;
    vector<int> v;

    for (i = 0; i < pos1; i++)
    {
        v.push_back(pots_permutation[i]);
    }

    for (i = pos3; i < POT_SIZE; i++)
    {
        v.push_back(pots_permutation[i]);
    }
    for (i = pos2; i < pos3; i++)
    {
        v.push_back(pots_permutation[i]);
    }


    for (i = pos1; i < pos2; i++)
    {
        v.push_back(pots_permutation[i]);
    }


    for (i = 0; i < (int)v.size(); i++)
    {
        temp_perm[i] = v[i];
    }

    if (AcceptanceCriterion(pots_permutation, temp_perm, pots))
    {
        memcpy(new_pots_permutation, temp_perm, sizeof(temp_perm));//accept
    }


}


void perturbation(POTS* pots, SOLUTION& best_solution, SOLUTION& current_solution)
{
    double_bridge_move(best_solution.permutation, current_solution.permutation);
    current_solution.cost = cost_total(current_solution.permutation, pots);
}


void iterated_local_search(SOLUTION& best_solution, POTS* pots, int max_iterations, int max_no_improve)
{
    SOLUTION* current_solution = new SOLUTION;

  
    random_permutation(best_solution.permutation);


    best_solution.cost = cost_total(best_solution.permutation, pots);
    local_search(best_solution, pots, max_no_improve); //初始搜索

    for (int i = 0; i < max_iterations; i++)
    {
        perturbation(pots, best_solution, *current_solution); 
        local_search(*current_solution, pots, max_no_improve);

   
        if (current_solution->cost < best_solution.cost)
        {
            for (int j = 0; j < POT_SIZE; j++)
            {
                best_solution.permutation[j] = current_solution->permutation[j];
            }
            best_solution.cost = current_solution->cost;
        }
        cout << setw(13) << setiosflags(ios::left) << "迭代搜索 " << i << " 次\t" << "最优解 = " << best_solution.cost << " 当前解 = " << current_solution->cost << endl;
    }



}
