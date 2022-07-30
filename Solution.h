#pragma once
#include <iostream>
#include <cmath>
#include <stdlib.h>
#include <time.h>
#include <vector>
#include <windows.h>
#include <memory.h>
#include <string.h>
#include <iomanip>
#define   POT_SIZE 52 //散列点数量
typedef struct  Solution1
{
    int permutation   [POT_SIZE]; //散列点排列
    int cost;                        //该排列对应的总路线长度
}SOLUTION1;
typedef struct candidate
{
    int x;
    int y;
}pot, POTS;

class Solution{ 
public:
// 计算邻域操作优化值 
int calc_delta(int i, int k, int* tmp, POTS* pots);

//计算两个散列点间距离
int distance_2pot(pot c1, pot c2);

//根据产生的散列点序列，计算旅游总距离
int cost_total(int* pots_permutation, POTS* pots);

//获取随机散列点排列, 用于产生初始解
void random_permutation(int* pots_permutation);

//颠倒数组中下标begin到end的元素位置, 用于two_opt邻域动作
void swap_element(int* p, int begin, int end);

//邻域动作 反转index_i <-> index_j 间的元素
void two_opt_swap(int* pots_permutation, int* new_pots_permutation, int index_i, int index_j);

//本地局部搜索，边界条件 max_no_improve
void local_search(SOLUTION1& best, POTS* pots, int max_no_improve);

//判断接受准则
bool AcceptanceCriterion(int* pots_permutation, int* old_pots_permutation, POTS* p_pots);

//将散列点序列分成4块，然后按块重新打乱顺序。
//用于扰动函数
void double_bridge_move(int* pots_permutation, int* new_pots_permutation);

//扰动
void perturbation(POTS* pots, SOLUTION1& best_solution, SOLUTION1& current_solution);

////迭代搜索
//void iterated_local_search(SOLUTION1& best, POTS* pots, int max_iterations, int max_no_improve);

// 更新Delta 
void Update(int i, int k, int* tmp, POTS* pots);





};

