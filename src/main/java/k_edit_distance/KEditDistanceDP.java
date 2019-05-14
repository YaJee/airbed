package main.java.k_edit_distance;

import java.util.ArrayList;
import java.util.List;

public class KEditDistanceDP {

    //获取word1 和 word2的编辑距离
    //设dp[i][j]: word1前i 到 word2前j的编辑距离
    public int mindistance(String word1, String word2){
        int m = word1.length();
        int n = word2.length();

        int[][] cost = new int[m+1][n+1];
        for(int i = 0; i <= m; i++)
            cost[i][0] = i;

        for(int i = 1; i <= n; i++)
            cost[0][i] = i;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(word1.charAt(i) == word2.charAt(j)){
                    cost[i + 1][j + 1] = cost[i][j];
                }else{
                    int a = cost[i][j];
                    int b = cost[i][j + 1];
                    int c = cost[i + 1][j];
                    cost[i + 1][j + 1] = 1 + Math.min(a,Math.min(b,c));

                }
            }
        }
        return cost[m][n];
    }
    public List<String> getKEditDistance(String[] words, String target, int k) {

        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0 || target == null ||
                target.length() == 0 || k < 0) {
            return res;
        }
        for(int i = 0; i < words.length; i++){
            int dis = mindistance(words[i],target);
            if(dis <= k) res.add(words[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        KEditDistanceDP sol = new KEditDistanceDP();
        String[] input = new String[]{"abcd", "abc", "abd", "ad", "c", "cc","b"};
        String target = "abcd";
        int k = 2;
        List<String> res = sol.getKEditDistance(input, target, k);
        System.out.println(res);//4
        System.out.println(res.size());//4

    }
}
