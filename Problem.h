#pragma once
 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
using namespace std;

class Problem{

    void ProblemRead( ){
        FILE* fp, * read;
        char s[305];
        int i, n, l, num;
        if ((fp = fopen("text01.txt", "w")) == NULL)
        {
            printf("can't open file\n");
            exit(1);
        }
        if ((read = fopen("text01.txt", "r")) == NULL)
        {
            printf("can't open file\n");
            exit(1);
        }
        while (scanf("%d ", &n) != EOF)
        {
            if (n == 0) { break; }
            for (i = 0; i < n; i++)
            {
                gets_s(s);
                l = strlen(s);
                fprintf(fp, "%d ", l);
                fputs(s, fp);
                fputc('\n', fp);
                memset(s, 0, sizeof(s));
            }
        }
        rewind(fp);
        while (fscanf(read, "%d ", &num + 1) != EOF)//num+1这个接收就不对啊
        {
            fgets(s, num, read);
            for (i = 0; i <= n; i++) 
            {
                putchar(s[i]);
            }
        }
        fclose(fp);
        fclose(read);
        return ;
    }


   

};

