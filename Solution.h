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
#define   POT_SIZE 52 //ɢ�е�����
typedef struct  Solution1
{
    int permutation   [POT_SIZE]; //ɢ�е�����
    int cost;                        //�����ж�Ӧ����·�߳���
}SOLUTION1;
typedef struct candidate
{
    int x;
    int y;
}pot, POTS;

class Solution{ 
public:
// ������������Ż�ֵ 
int calc_delta(int i, int k, int* tmp, POTS* pots);

//��������ɢ�е�����
int distance_2pot(pot c1, pot c2);

//���ݲ�����ɢ�е����У����������ܾ���
int cost_total(int* pots_permutation, POTS* pots);

//��ȡ���ɢ�е�����, ���ڲ�����ʼ��
void random_permutation(int* pots_permutation);

//�ߵ��������±�begin��end��Ԫ��λ��, ����two_opt������
void swap_element(int* p, int begin, int end);

//������ ��תindex_i <-> index_j ���Ԫ��
void two_opt_swap(int* pots_permutation, int* new_pots_permutation, int index_i, int index_j);

//���ؾֲ��������߽����� max_no_improve
void local_search(SOLUTION1& best, POTS* pots, int max_no_improve);

//�жϽ���׼��
bool AcceptanceCriterion(int* pots_permutation, int* old_pots_permutation, POTS* p_pots);

//��ɢ�е����зֳ�4�飬Ȼ�󰴿����´���˳��
//�����Ŷ�����
void double_bridge_move(int* pots_permutation, int* new_pots_permutation);

//�Ŷ�
void perturbation(POTS* pots, SOLUTION1& best_solution, SOLUTION1& current_solution);

////��������
//void iterated_local_search(SOLUTION1& best, POTS* pots, int max_iterations, int max_no_improve);

// ����Delta 
void Update(int i, int k, int* tmp, POTS* pots);





};

