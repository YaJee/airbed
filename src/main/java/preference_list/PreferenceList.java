package main.java.preference_list;



import java.util.*;

public class PreferenceList {

    public List<Integer> getPreference(List<List<Integer>> preferences){
        //入度
        Map<Integer,Integer> in_degredd = new HashMap<>();
        //边集
        Map<Integer,Set<Integer>> adj = new HashMap<>();

        //1。建立图
        for(List<Integer> list : preferences){

            for(int i = 0; i < list.size() - 1; i++){
                int from = list.get(i);
                int to = list.get(i + 1);

                if(!adj.containsKey(from)){
                    in_degredd.put(from, 0);
                    adj.put(from, new HashSet<>());
                }
                if(!adj.containsKey(to)){
                    in_degredd.put(to, 0);
                    adj.put(to, new HashSet<>());
                }
                //加入边集
                if(!adj.get(from).contains(to)){
                    Set<Integer> tmp_set = adj.get(from);
                    tmp_set.add(to);
                    adj.put(from,tmp_set);
                }
                //增加入度
                in_degredd.put(to, in_degredd.getOrDefault(to, 0) + 1);
            }
        }

        //2。拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        //将入度为0 的 入队列
        for(int in : in_degredd.keySet()){
            if(in_degredd.get(in) == 0)
                queue.offer(in);
        }
        List<Integer> res = new ArrayList<>();
        while(!queue.isEmpty()){
            int id = queue.poll();
            res.add(id);
            Set<Integer> neighbors = adj.get(id);
            for(int nei : adj.get(id)){
                int degree = in_degredd.get(nei) - 1;
                in_degredd.put(nei,degree);
                if(degree == 0) queue.offer(nei);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        PreferenceList sol = new PreferenceList();
        List<List<Integer>> preferences = new ArrayList<>();
        List<Integer> p1 = new ArrayList<Integer>(){{
            add(3);
            add(5);
            add(7);
            add(9);
        }};
        List<Integer> p2 = new ArrayList<Integer>(){{
            add(2);
            add(3);
            add(8);
        }};
        List<Integer> p3 = new ArrayList< Integer>(){{
            add(5);
            add(8);
        }};
        preferences.add(p1);
        preferences.add(p2);
        preferences.add(p3);
        List<Integer> res = sol.getPreference(preferences);
        System.out.println(res);


    }
}
