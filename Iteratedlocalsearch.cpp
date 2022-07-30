#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <time.h>
#include <vector>
#include <windows.h>
#include <memory.h>
#include <string.h>
#include <iomanip>
#include "Solution.h"
#include "Methods.h"

using namespace std;



#define DEBUG
//散列点排列
//散列点坐标数组
POTS pots[POT_SIZE];
using namespace std;
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


    Methods method;
    srand(1);
    int max_iterations = 600;
    int max_no_improve = 50;
    //初始化指针数组 
    int** Delta = new int* [POT_SIZE];
    for (int i = 0; i < POT_SIZE; i++)
        Delta[i] = new int[POT_SIZE];

    SOLUTION1 best_solution;
    method.iterated_local_search(best_solution, berlin52, max_iterations, max_no_improve);

    cout << endl << endl << "搜索完成！ 最优路线总长度 = " << best_solution.cost << endl;
    cout << "最优访问散列点序列如下：" << endl;
    for (int i = 0; i < POT_SIZE; i++)
    {
        cout << setw(4) << setiosflags(ios::left) << best_solution.permutation[i];
    }

    cout << endl << endl;

    return 0;
}