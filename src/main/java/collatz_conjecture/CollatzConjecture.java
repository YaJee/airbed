package main.java.collatz_conjecture;

import java.util.HashMap;
import java.util.Map;


public class CollatzConjecture {
    /*
    题目：Collatz conjecture（考拉兹猜想）
        ● 如果一个数是奇数，接下来将它转换为3*n+1
        ● 如果一个数是偶数，接下来将它转换为 n/2
        ● 这个数最终转换为1
        ● 步数：就是将这个数转换为1的次数
    问题：给定一个整数n，求区间[1，n]中的n个数 分别转换为1所需步数 的最大值。

    思路：分为奇数、偶数两种情况---递归计算
     */


    //解法1：递归过深，爆栈
    public class Solution{

        private int findSteps(int num){
            if( num <= 1) return 1;
            if( num % 2 == 1) return 1 + findSteps(num / 2);
            return  1 + findSteps(3*num +1);
        }
        public int findeLongestSteps(int num){
            if(num < 1) return 0;

            int res = 0;
            for(int i=1; i<=num; i++){
                int t = findSteps(i);
                res = Math.max(res,t);
            }
            return res;
        }
    }

    //解法2：使用Map 记忆
    public class Solution2{
        Map<Integer, Integer> map = new HashMap<>();

        private int findSetps(int num){

            if(num <= 1) return  1;

            if(map.containsKey(num)) return map.get(num);
            //转换
            num = num % 2 == 0 ? num/2 : 3*num+1;
            if(map.containsKey(num)) return map.get(num)+1;

            int t = findSetps(num);
            map.put(num,t);
            return t+1;
        }
        public int findeLongestSteps(int num){
            if(num < 1) return 0;

            int res = 0;
            for(int i=1; i<=num; i++){
                int t = findSetps(i);
                map.put(i,t);
                res = Math.max(res, t);
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution2 s2 = new CollatzConjecture().new Solution2();
        System.out.println(s2.findeLongestSteps(1));
        System.out.println(s2.findeLongestSteps(10));
        System.out.println(s2.findeLongestSteps(37));
        System.out.println(s2.findeLongestSteps(10));
    }
}
