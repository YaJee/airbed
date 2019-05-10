package main.java.flight_list;

import java.util.*;

public class FlightList {
    /*
        从 start 到 target 最多 中转 k 次多最少价格
        BFS
     */

    public int minCost(List<String> lines, String source, String target, int k){
        if(lines.size() == 0 || k < 0) return 0;

        //初始化航班
        Map<String, Flight> nodes = new HashMap<>();
        for(String line : lines){
            String[] s = line.trim().split(",");
            String[] t = s[0].trim().split("->");
            String from = t[0];
            String to = t[1];
            int cost = Integer.valueOf(s[1].trim());
            if(!nodes.containsKey(from)) nodes.put(from,new Flight(from));
            if(!nodes.containsKey(to)) nodes.put(to,new Flight(to));
            nodes.get(from).nextNodes.put(to,cost);
        }
        //BFS

        boolean find = false;
        Queue<String> queue = new LinkedList<>();   //BFS
        Queue<Integer> cost = new LinkedList<>();   //BFS中 从source到当前节点的成本
        queue.offer(source);
        cost.offer(0);

        int stops = -1;
        while (!queue.isEmpty()){
            stops++;
            if(stops > k+1){
                break;
            }
            int qSize = queue.size();
            for(int i = 0; i < qSize; i++){
                Flight cur_fli = nodes.get(queue.poll());
                int cur_cost = cost.poll();

                cur_fli.minCost = Math.min(cur_fli.minCost, cur_cost);

                if(cur_fli.name.equals(target)){
                    find = true;
                    continue;
                }

                for( String next : cur_fli.nextNodes.keySet()){
                    int next_cost = cur_cost + cur_fli.nextNodes.get(next);
                    //此处判断 既可减少 多余步骤，又可避免环
                    if(next_cost < nodes.get(next).minCost && (stops < k || stops == k && next.equals(target))){
                        queue.offer(next);
                        cost.offer(next_cost);
                    }
                }
            }
        }
        return find ? nodes.get(target).minCost : -1;

    }
    class Flight {
        String name;
        int minCost;        //当前节点到达 source的成本
        Map<String, Integer> nextNodes;//可以到达的城市，及其成本

        Flight(String name){
            this.name = name;
            this.minCost = Integer.MAX_VALUE;
            this.nextNodes = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        FlightList sol = new FlightList();
        List<String> lines = new ArrayList<>();

        lines.add("A->B,100");
        lines.add("A->C,400");
        lines.add("B->C,100");
        lines.add("C->D,100");
        lines.add("C->A,10");

        System.out.println(sol.minCost(lines,"A","D",0));
        System.out.println(sol.minCost(lines,"A","D",1));
        System.out.println(sol.minCost(lines,"A","D",2));
    }
}
