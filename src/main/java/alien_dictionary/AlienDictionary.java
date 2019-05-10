package main.java.alien_dictionary;

import java.util.*;

public class AlienDictionary {

    /**
     * 外星人的字典
     * 思路：
     * 1.建图
     * 2.对图拓扑排序
     */
    public String alienOrder(String[] words){
        //边集---邻接表
        Map<Character,Set<Character>> adj = new HashMap<>();
        //入度
        Map<Character, Integer> in_degree = new HashMap<>();
        for(String word : words){
            for(char c : word.toCharArray()){
                in_degree.put(c,0);
            }
        }

        String res = "";
        if(words == null || words.length == 0) return res;

        //1.建立边集
        for(int i = 0; i < words.length - 1; i++){
            String w1 = words[i];
            String w2 = words[i + 1];

            int len = Math.min(w1.length(), w2.length());
            int j=0;
            for(; j < len; j++){
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if(c1 != c2){     //只能进入一次
                    Set<Character> set = adj.get(c1);
                    if(set == null){
                        set = new HashSet<Character>();
                    }
                    if(!set.contains(c2)){
                        set.add(c2);
                        adj.put(c1, set);
                        in_degree.put(c2,in_degree.get(c2)+1);
                    }
                    break; //退出循环
                }
            }
            if(j >= len && w1.length() > w2.length()){//推导不出来，所以无效，返回""
                return "";
            }
        }

        //2.拓扑排序

        Queue<Character> queue = new LinkedList<>();    //存储入度为0的
        for(Character c : in_degree.keySet()){
            if(in_degree.get(c) == 0)
                queue.offer(c);
        }

        while (!queue.isEmpty()){
            char c = queue.poll();
            res += ""+c;
            if(adj.containsKey(c)){
                for(Character nei : adj.get(c)){
                    in_degree.put(nei,in_degree.get(nei) - 1);
                    if(in_degree.get(nei) == 0){
                        queue.offer(nei);
                    }
                }
            }
        }
        if( res.length() != in_degree.keySet().size()){
            return "";
        }
        return res;
    }
}
