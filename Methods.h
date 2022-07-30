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
        //逛一圈，看看最后的总距离是多少
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

    //获取随机散列点排列
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




    //颠倒数组中下标begin到end的元素位置
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


    //邻域动作 反转index_i <-> index_j 间的元素
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
        去重处理，对于Delta数组来说，对于散列点序列1-2-3-4-5-6-7-8-9-10，如果对3-5应用了邻域操作2-opt ， 事实上对于
        7-10之间的翻转是不需要重复计算的。 所以用Delta提前预处理一下。
        当然由于这里的计算本身是O（1） 的，事实上并没有带来时间复杂度的减少（更新操作反而增加了复杂度）
        如果delta计算 是O（n）的，这种去重操作效果是明显的。
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
        }// 如果不是边界，更新(i-1, k + 1)之间的 
        else {
            for (i = 0; i < POT_SIZE - 1; i++)
            {
                for (k = i + 1; k < POT_SIZE; k++)
                {
                  
                }
            }
        }// 边界要特殊更新 

    }

    //本地局部搜索，边界条件 max_no_improve
    //best_SOLUTION最优解
    //current_SOLUTION当前解
    void local_search(SOLUTION1& best_SOLUTION, POTS* pots, int max_no_improve)
    {
        int count = 0;
        int i, k;

        int inital_cost = best_SOLUTION.cost; //初始花费

        int now_cost = 0;

        SOLUTION1* current_SOLUTION1 = new SOLUTION1; //为了防止爆栈……直接new了，你懂的

        for (i = 0; i < POT_SIZE - 1; i++)
        {
            for (k = i + 1; k < POT_SIZE; k++)
            {
                
            }
        }

        do
        {
            //枚举排列
            for (i = 0; i < POT_SIZE - 1; i++)
            {
                for (k = i + 1; k < POT_SIZE; k++)
                {
                    //邻域动作
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
    //判断接受准则
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

    //将散列点序列分成4块，然后按块重新打乱顺序。
    //用于扰动函数
    void double_bridge_move(int* pots_permutation, int* new_pots_permutation, POTS* pots)
    {
        int temp_perm[POT_SIZE];

        int pos1 = 1 + rand() % (POT_SIZE / 4);
        int pos2 = pos1 + 1 + rand() % (POT_SIZE / 4);
        int pos3 = pos2 + 1 + rand() % (POT_SIZE / 4);

        int i;
        vector<int> v;
        //第一块
        for (i = 0; i < pos1; i++)
        {
            v.push_back(pots_permutation[i]);
        }

        //第二块
        for (i = pos3; i < POT_SIZE; i++)
        {
            v.push_back(pots_permutation[i]);
        }
        //第三块
        for (i = pos2; i < pos3; i++)
        {
            v.push_back(pots_permutation[i]);
        }

        //第四块
        for (i = pos1; i < pos2; i++)
        {
            v.push_back(pots_permutation[i]);
        }


        for (i = 0; i < (int)v.size(); i++)
        {
            temp_perm[i] = v[i];
        }
        //if accept判断是否接受当前解
        if (AcceptanceCriterion(pots_permutation, temp_perm, pots))
        {
            memcpy(new_pots_permutation, temp_perm, sizeof(temp_perm));//accept
        }


    }

    //扰动
    void perturbation(POTS* pots, SOLUTION1& best_SOLUTION, SOLUTION1& current_SOLUTION)
    {
        double_bridge_move(best_SOLUTION.permutation, current_SOLUTION.permutation, pots);
        current_SOLUTION.cost = cost_total(current_SOLUTION.permutation, pots);
    }

    //迭代搜索
    //max_iterations用于迭代搜索次数
   
 
      //max_no_improve用于局部搜索边界条件
      void iterated_local_search(SOLUTION1& best_solution, POTS* pots, int max_iterations, int max_no_improve)
      {
          SOLUTION1* current_solution = new SOLUTION1;

          //获得初始随机解
          random_permutation(best_solution.permutation);


          best_solution.cost = cost_total(best_solution.permutation, pots);
          local_search(best_solution, pots, max_no_improve); //初始搜索

          for (int i = 0; i < max_iterations; i++)
          {
              perturbation(pots, best_solution, *current_solution); //扰动+判断是否接受新解
              local_search(*current_solution, pots, max_no_improve);//继续局部搜索

              //找到更优解
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
};