package main.java.menu_combination_sum;

import java.util.ArrayList;
import java.util.List;


public class MenuCombinationSum {

    /**
     * 思路：
     * step1 : 把菜单价格*100转成整数
     * step2 : 回溯法
     */

    public List<List<Double>> getCombos(double[] prices,double target){
        List<List<Double>> res = new ArrayList<>();
        if(prices == null || prices.length == 0 || target <= 0){
            return res;
        }
        //原价 * 100
        int centsTarget = (int) Math.round(target * 100);
        int [] centsPrices = new int[prices.length];
        //原价 * 100
        for(int i=0; i<prices.length; i++){
            centsPrices[i] = (int) Math.round(prices[i]*100);
        }

        DFS(centsPrices,0, centsTarget, new ArrayList<Double>(), res, prices);
        return res;
    }
    //               整数价格      当前下标    目标价格       一个结果            返回值               原价
    public void DFS(int[] cand, int cur, int target, List<Double> path, List<List<Double>> res,double[] prices){
        if(target == 0){
            res.add(new ArrayList(path));
            return;
        }
        if(target < 0) return;
        for(int i=cur; i< cand.length; i++){
            if(i > cur && cand[i] == cand[i-1]) continue;
            path.add(prices[i]);

            DFS(cand, i+1, target - cand[i], path, res, prices);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        MenuCombinationSum cb = new MenuCombinationSum();
        double [] prices = {10.02, 1.11, 2.22, 3.01, 4.02, 2.00, 5.03};
        List<List<Double>> combos = cb.getCombos(prices,7.03);
        System.out.println(combos);
        System.out.println((int)(4.02*100));
    }
}


