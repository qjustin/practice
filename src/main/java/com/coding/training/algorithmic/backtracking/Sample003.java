package com.coding.training.algorithmic.backtracking;

public class Sample003 {

    public static void main(String[] args) {

        char buf[] = {'A', 'B', 'C', 'D'};


        permutation(buf, 0, buf.length - 1);
        System.out.println("*******************************");
        Combination();

    }

    /*
     * 全排列
     *
     */

    public static void permutation(char[] buf, int start, int end) {

        if (start == end) {// 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可

            for (int i = 0; i <= end; i++) {

                System.out.print(buf[i]);

            }

            System.out.println();

        } else {// 多个字母全排列

            for (int i = start; i <= end; i++) {
                char temp = buf[start];// 交换数组第一个元素与后续的元素
                buf[start] = buf[i];
                buf[i] = temp;

                permutation(buf, start + 1, end);// 后续元素递归全排列

                temp = buf[start];// 将交换后的数组还原
                buf[start] = buf[i];
                buf[i] = temp;

            }

        }

    }

    /*
     * 全组合
     *
     */
    public static void Combination() {
        /*基本思路：求全组合，则假设原有元素n个，则最终组合结果是2^n个。原因是：
         * 用位操作方法：假设元素原本有：a,b,c三个，则1表示取该元素，0表示不取。故去a则是001，取ab则是011.
         * 所以一共三位，每个位上有两个选择0,1.所以是2^n个结果。
         * 这些结果的位图值都是0,1,2....2^n。所以可以类似全真表一样，从值0到值2^n依次输出结果：即：
         * 000,001,010,011,100,101,110,111 。对应输出组合结果为：
        空,a, b ,ab,c,ac,bc,abc.
        这个输出顺序刚好跟数字0~2^n结果递增顺序一样
        取法的二进制数其实就是从0到2^n-1的十进制数
         * ******************************************************************
         * *
         * */
        String[] str = {"A", "B", "C", "D"};
        int n = str.length;                                  //元素个数。
        //求出位图全组合的结果个数：2^n
        int nbit = 1 << n;                                     // “<<” 表示 左移:各二进位全部左移若干位，高位丢弃，低位补0。:即求出2^n=2Bit。
        System.out.println("全组合结果个数为：" + nbit);

        for (int i = 0; i < nbit; i++) {                        //结果有nbit个。输出结果从数字小到大输出：即输出0,1,2,3,....2^n。
            System.out.print("组合数值  " + i + " 对应编码为： ");
            for (int j = 0; j < n; j++) {                        //每个数二进制最多可以左移n次，即遍历完所有可能的变化新二进制数值了
                int tmp = 1 << j;
                if ((tmp & i) != 0) {                            //& 表示与。两个位都为1时，结果才为1
                    System.out.print(str[j]);
                }
            }
            System.out.println();
        }
    }


    /*
    import java.util.*;
public class Main {
    public static void swap(int a[],int x,int y)
    {
        int temp=a[x];
        a[x]=a[y];
        a[y]=temp;
    }
    public static void allsort(int a[],int begin,int end)
    {
        if(begin==end)
        {
            System.out.println(Arrays.toString(a));
        }

        for(int i=begin;i<=end;i++)
        {
            swap(a,begin,i);
            allsort(a,begin+1,end);
            swap(a,begin,i);
        }
    }
    public static void main(String[] args) {
        int a[]={1,2,3,4,5};
        allsort(a,0,a.length-1);
    }
}

import java.util.*;
public class Main {

    public static void main(String[] args) {

        int a[]={1,2,3,4,5};

        int l = a.length;

        int nBit = 1<<l;

        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < l; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    System.out.print(a[j]);
                }
            }
            System.out.println();
        }

    }
}

     */
}
