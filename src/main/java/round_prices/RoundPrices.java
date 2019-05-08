package main.java.round_prices;

import java.util.Arrays;
import java.util.Comparator;


public class RoundPrices {

    public class Solution {
        /**
         *思路：
            ● 这些价格都要调整（floor向下取整或者ceil向上取整）
            ● 而如果对所有价格都进行floor的话，那么sum(Y)可能会低于round值，所以要取一些价格的ceil值
            ● 所以我们要考虑的就是对哪些价格进行ceil处理即可
            ● 我们将原价和其ceil值差值最小的价格进行ceil操作
         */
        public int [] roundUp(double[] arr){

            int n = arr.length;
            NumWithDiff [] arrWithDiff = new NumWithDiff[n];

            double sum = 0.0;
            int floorSum = 0;
            for(int i=0; i<n; i++){
                int floor = (int)arr[i];
                int ceil = floor;            //向上取整
                if(floor <  arr[i]) ceil++;

                floorSum += floor;           //向下取整和

                sum += arr[i];               //原价总和
                arrWithDiff[i] = new NumWithDiff(ceil,ceil-arr[i]);
            }

            int round_sum = (int)Math.round(sum);
            int diff = round_sum - floorSum;

            //排序
            Arrays.sort(arrWithDiff,new Comparator<NumWithDiff>(){
                @Override
                public int compare(NumWithDiff n1, NumWithDiff n2){
                    //升序排列
                    if(n1.diffWithCeil <= n2.diffWithCeil) return -1;
                    else return 1;
                }
            });

            int [] res = new int[n];
            int i=0;
            for(; i<diff; i++){
                res[i] = arrWithDiff[i].num;    //这些放ceil,
            }
            for(; i<n; i++){
                res[i] = arrWithDiff[i].num - 1;//剩下的放floor
            }
            return res;
        }

        class NumWithDiff{
            int num;            //向上取整
            double diffWithCeil;//向上取整后，与原值的差

            public NumWithDiff(int n, double c){
                this.num = n;
                this.diffWithCeil = c;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new RoundPrices().new Solution();
        double[] arr = {1.2, 3.7, 2.3, 4.8};
        int [] res = sol.roundUp(arr);
        for(int i=0; i<res.length; i++)
            System.out.print(res[i] + " ");
        System.out.println();
    }
}
