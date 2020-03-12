package com.coding.training.algorithmic.dp;

/**
 * https://blog.csdn.net/qq_40636117/article/details/80941638
 *
 * 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins =
 * [1, 2, 5]
 *  amount =
 * 11
 *
 * 输出:
 * 3
 *
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *
 * 输入: coins =
 * [2]
 * , amount =
 * 3
 *
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 * 硬币面额数组 c[3]={2,3,5}, 找零11元
 *
 * 1.找零0元：不存在的，∴dp[0]=0;
 *
 * 2.找零1元：还是不存在，∴dp[1]=0;
 *
 *     但是题目要求不存在的话输出-1，所以把dp[1]记为-1
 *
 * 3.找零2元：
 *
 *     （1）找零0元+1枚2元硬币：找零方案f1=dp[0]+1(1表示1枚2元硬币！),f1=1
 *
 *      没有其他找零方法了，∴dp[2]=f1=1;
 *
 *     （找零1元+1枚1元硬币？不存在的）
 *
 * 4.找零3元：
 *
 *     （1）找零0元+1枚3元硬币：f1=dp[0]+1(1的意义同上！),f1=1
 *
 *      没有其他找零方案了，∴p[3]=f1=1;
 *
 *     （找零2元+1枚1元硬币？找零2元的方案倒是有，但没有1元硬币啊）
 *
 * 5.找零4元：
 *
 *     （1）找零2元+1枚2元硬币：f1=dp[2]+1=2
 *
 *     没有其他方案了，所以dp[4]=f1=2
 *
 * 6.找零5元：
 *
 *     （1）找零2元+1枚3元硬币：f1=dp[2]+1=2
 *
 *     （2）找零3元+1枚2元硬币：f2=dp[3]+1=2
 *
 *     （3）找零0元+1枚5元硬币：f3=dp[0]+1=1;
 *
 *     ∴dp[5]=min(f1,f2,f3)=1
 *
 * 7.找零6元：
 *
 *     （1）找零3元+1枚3元硬币：f1=dp[3]+1=2
 *
 *     （2）找零4元+1枚2元硬币：f2=dp[4]+1=3
 *
 *     ∴dp[6]=min(f1,f2)=2
 *
 * 8.找零7元：
 *
 *     （1）找零4元+1枚3元硬币：f1=dp[4]+1=3
 *
 *     （2）找零5元+1枚2元硬币：f2=dp[5]+1=2
 *
 *     ∴dp[7]=min(f1,f2)=2
 *
 * 9.找零8元：
 *
 *     （1）找零3元+1枚5元硬币：f1=dp[3]+1=2
 *
 *     （2）找零6元+1枚2元硬币：f2=dp[6]+1=3
 *
 *     ∴dp[8]=min(f1,f2)=2
 *
 * 10.找零9元：
 *
 *     （1）找零4元+1枚5元硬币：f1=dp[4]+1=3
 *
 *     （2）找零6元+1枚3元硬币：f2=dp[6]+1=3
 *
 *     （3）找零7元+1枚2元硬币：f3=dp[7]+1=3
 *
 *     ∴dp[9]=min(f1,f2,f3)=3
 *
 * 11.找零10元：
 *
 *     （1）找零5元+1枚5元硬币：f1=dp[5]+1=2
 *
 *     （2）找零7元+1枚3元硬币：f2=dp[7]+1=3
 *
 *     （3）找零8元+1枚2元硬币：f3=dp[8]+1=3
 *
 *     ∴dp[10]=min(f1,f2,f3)=2
 *
 * 12.找零11元(最终解)：
 *
 *     （1）找零6元+1枚5元硬币：f1=dp[6]+1=3
 *
 *     （2）找零8元+1枚3元硬币：f2=dp[8]+1=3
 *
 *     （3）找零9元+1枚2元硬币：f3=dp[9]+1=4
 *
 *     ∴dp[11]=min(f1,f2,f3)=3
 */
public class Sample008 {
    public int coinChange(int[] coins, int amount) {//硬币数组，找零面额
        if(amount==0){
            return 0;
        }

        int len=coins.length;
        int []dp=new int[amount+1];
        dp[0]=0;
        for(int i=1;i<=amount;++i){
            int h=amount;
            for(int j=0;j<len;++j){
                if(coins[j]<=i && dp[i-coins[j]]!=-1){
                    if(dp[i-coins[j]]<=h){
                        h=dp[i-coins[j]];
                    }
                }
            }
            if(h<i+1){
                dp[i]=h+1;
            }else{dp[i]=-1;}
        }
        return dp[amount];
    }
}
