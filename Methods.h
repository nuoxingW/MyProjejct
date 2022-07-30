#pragma once
#include <stdlib.h>
#include <vector>
 
#include "Solution.h"
#include "Solution.h"
using namespace std;
 

 


class Methods
{
    
public :
    int permutation[POT_SIZE];
    int** Delta;
    
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
        //��һȦ�����������ܾ����Ƕ���
        for (int i = 0; i < POT_SIZE; i++)
        {
            c1 = pots_permutation[i];
            if (i == POT_SIZE - 1) //���һ��ɢ�е�͵�һ��ɢ�е�������
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

    //��ȡ���ɢ�е�����
    void random_permutation(int* pots_permutation)
    {
        int i, r, temp;
        for (i = 0; i < POT_SIZE; i++)
        {
            pots_permutation[i] = i; //��ʼ��ɢ�е����У���ʼ��˳����
        }


        for (i = 0; i < POT_SIZE; i++)
        {
            //ɢ�е�����˳���������
            r = rand() % (POT_SIZE - i) + i;
            temp = pots_permutation[i];
            pots_permutation[i] = pots_permutation[r];
            pots_permutation[r] = temp;
        }
    }




    //�ߵ��������±�begin��end��Ԫ��λ��
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


    //������ ��תindex_i <-> index_j ���Ԫ��
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


    /*
        ȥ�ش�������Delta������˵������ɢ�е�����1-2-3-4-5-6-7-8-9-10�������3-5Ӧ�����������2-opt �� ��ʵ�϶���
        7-10֮��ķ�ת�ǲ���Ҫ�ظ�����ġ� ������Delta��ǰԤ����һ�¡�
        ��Ȼ��������ļ��㱾����O��1�� �ģ���ʵ�ϲ�û�д���ʱ�临�Ӷȵļ��٣����²������������˸��Ӷȣ�
        ���delta���� ��O��n���ģ�����ȥ�ز���Ч�������Եġ�
    */

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
        }// ������Ǳ߽磬����(i-1, k + 1)֮��� 
        else {
            for (i = 0; i < POT_SIZE - 1; i++)
            {
                for (k = i + 1; k < POT_SIZE; k++)
                {
                  
                }
            }
        }// �߽�Ҫ������� 

    }

    //���ؾֲ��������߽����� max_no_improve
    //best_SOLUTION���Ž�
    //current_SOLUTION��ǰ��
    void local_search(SOLUTION1& best_SOLUTION, POTS* pots, int max_no_improve)
    {
        int count = 0;
        int i, k;

        int inital_cost = best_SOLUTION.cost; //��ʼ����

        int now_cost = 0;

        SOLUTION1* current_SOLUTION1 = new SOLUTION1; //Ϊ�˷�ֹ��ջ����ֱ��new�ˣ��㶮��

        for (i = 0; i < POT_SIZE - 1; i++)
        {
            for (k = i + 1; k < POT_SIZE; k++)
            {
                
            }
        }

        do
        {
            //ö������
            for (i = 0; i < POT_SIZE - 1; i++)
            {
                for (k = i + 1; k < POT_SIZE; k++)
                {
                    //������
                    two_opt_swap(best_SOLUTION.permutation, current_SOLUTION1->permutation, i, k);
               
                    current_SOLUTION1->cost = now_cost;
                    if (current_SOLUTION1->cost < best_SOLUTION.cost)
                    {
                        count = 0; //better cost found, so reset
                        for (int j = 0; j < POT_SIZE; j++)
                        {
                            best_SOLUTION.permutation[j] = current_SOLUTION1->permutation[j];
                        }
                        best_SOLUTION.cost = current_SOLUTION1->cost;
                        inital_cost = best_SOLUTION.cost;
                        Update(i, k, best_SOLUTION.permutation, pots);
                    }

                }
            }

            count++;

        } while (count <= max_no_improve);
    }
    //�жϽ���׼��
    bool AcceptanceCriterion(int* pots_permutation, int* old_pots_permutation, POTS* p_pots)
    {
        int acceptance = 500; //��������,�뵱ǰ���������acceptance
        int old_cost = cost_total(old_pots_permutation, p_pots);
        int new_cost = cost_total(pots_permutation, p_pots);

        if ((new_cost <= (old_cost + acceptance)) || (new_cost >= (old_cost - acceptance)))
        {
            return true;
        }

        return false;
    }

    //��ɢ�е����зֳ�4�飬Ȼ�󰴿����´���˳��
    //�����Ŷ�����
    void double_bridge_move(int* pots_permutation, int* new_pots_permutation, POTS* pots)
    {
        int temp_perm[POT_SIZE];

        int pos1 = 1 + rand() % (POT_SIZE / 4);
        int pos2 = pos1 + 1 + rand() % (POT_SIZE / 4);
        int pos3 = pos2 + 1 + rand() % (POT_SIZE / 4);

        int i;
        vector<int> v;
        //��һ��
        for (i = 0; i < pos1; i++)
        {
            v.push_back(pots_permutation[i]);
        }

        //�ڶ���
        for (i = pos3; i < POT_SIZE; i++)
        {
            v.push_back(pots_permutation[i]);
        }
        //������
        for (i = pos2; i < pos3; i++)
        {
            v.push_back(pots_permutation[i]);
        }

        //���Ŀ�
        for (i = pos1; i < pos2; i++)
        {
            v.push_back(pots_permutation[i]);
        }


        for (i = 0; i < (int)v.size(); i++)
        {
            temp_perm[i] = v[i];
        }
        //if accept�ж��Ƿ���ܵ�ǰ��
        if (AcceptanceCriterion(pots_permutation, temp_perm, pots))
        {
            memcpy(new_pots_permutation, temp_perm, sizeof(temp_perm));//accept
        }


    }

    //�Ŷ�
    void perturbation(POTS* pots, SOLUTION1& best_SOLUTION, SOLUTION1& current_SOLUTION)
    {
        double_bridge_move(best_SOLUTION.permutation, current_SOLUTION.permutation, pots);
        current_SOLUTION.cost = cost_total(current_SOLUTION.permutation, pots);
    }

    //��������
    //max_iterations���ڵ�����������
   
 
      //max_no_improve���ھֲ������߽�����
      void iterated_local_search(SOLUTION1& best_solution, POTS* pots, int max_iterations, int max_no_improve)
      {
          SOLUTION1* current_solution = new SOLUTION1;

          //��ó�ʼ�����
          random_permutation(best_solution.permutation);


          best_solution.cost = cost_total(best_solution.permutation, pots);
          local_search(best_solution, pots, max_no_improve); //��ʼ����

          for (int i = 0; i < max_iterations; i++)
          {
              perturbation(pots, best_solution, *current_solution); //�Ŷ�+�ж��Ƿ�����½�
              local_search(*current_solution, pots, max_no_improve);//�����ֲ�����

              //�ҵ����Ž�
              if (current_solution->cost < best_solution.cost)
              {
                  for (int j = 0; j < POT_SIZE; j++)
                  {
                      best_solution.permutation[j] = current_solution->permutation[j];
                  }
                  best_solution.cost = current_solution->cost;
              }
              cout << setw(13) << setiosflags(ios::left) << "�������� " << i << " ��\t" << "���Ž� = " << best_solution.cost << " ��ǰ�� = " << current_solution->cost << endl;
          }
      }
};