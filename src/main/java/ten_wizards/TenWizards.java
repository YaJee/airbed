package main.java.ten_wizards;


import java.util.*;

public class TenWizards {

    class Wizard implements Comparable<Wizard>{
        int id;     //下标
        int dist;   //到9的距离

        Wizard(int id){
            this.id = id;
            this.dist = Integer.MAX_VALUE;
        }
        @Override
        public int compareTo(Wizard that){
            return this.dist - that.dist;
        }
    }

    /**
     * 解法1 BFS
     * 从 source 节点开始遍历 能到达到 节点
     * 数组 parents[i] 代表：Wizards[i] 到Wizards[0] 路径上的 最优父节点
     */
    public class Solution {

        /**
         * @param wizards   相邻的wizard数组
         * @param source    开始wizard
         * @param target    目标wizard
         */
        public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target){

            int n = wizards.size();
            int[] parents = new int[n];                 //记录，并更新每个wizard 的父节点
            Map<Integer, Wizard> map = new HashMap<>(); //初始化每个wizard_id,与其dist信息
            for(int i = 0; i < n; i++){
                parents[i] = i;
                map.put(i, new Wizard(i));
            }
            map.get(source).dist = 0;                   //设定 source到source的距离为0
            Queue<Wizard> queue = new LinkedList<>();
            queue.offer(map.get(source));

            while (!queue.isEmpty()){
                Wizard cur_wizard  = queue.poll();
                List<Integer> neighbors = wizards.get(cur_wizard.id);

                for(int neighbor : neighbors){
                    Wizard next = map.get(neighbor);           //cur_wizard 相邻 的 Wizard
                    int weight = (int) Math.pow(next.id - cur_wizard.id, 2);
                    if(next.dist > cur_wizard.dist + weight){ //next到source的距离  > next经过cur_wizard到达source的距离
                        parents[next.id] = cur_wizard.id;
                        next.dist = cur_wizard.dist + weight;
                    }
                    queue.offer(next);
                }
            }

            List<Integer> res = new ArrayList<>();          //找到路径
            int t = target;
            while (t != source){
                res.add(t);
                t = parents[t];
            }
            res.add(source);
            Collections.reverse(res);
            return res;

        }
    }

    /**
     * 解法2 Dijkstra 迪杰斯特拉
     */
    public class Solution2{

        public List<Integer> getShortestPath(List<List<Integer>> wizards,int source, int target){
            if(wizards == null || wizards.size() == 0) return null;
            int n = wizards.size();
            int [] parents = new int[n];               //记录每个wizard 的父节点

            Map<Integer, Wizard> map = new HashMap<>();//初始化每个wizard_id,与其dist信息
            for(int i = 0; i < n; i++){
                parents[i] = i;
                map.put(i, new Wizard(i));
            }

            map.get(source).dist = 0;                   //设定 source到source 的距离为0
            Queue<Wizard> pq = new PriorityQueue<>(n);
            pq.offer(map.get(source));

            while(!pq.isEmpty()){
                Wizard cur_wizard = pq.poll();
                List<Integer> neighbors = wizards.get(cur_wizard.id);

                for(int neighbor : neighbors){
                    Wizard next = map.get(neighbor);
                    int weight = (int) Math.pow(next.id - cur_wizard.id, 2);

                    if(next.dist > cur_wizard.dist + weight){
                        parents[next.id] = cur_wizard.id;
                        pq.remove(next);        //先删除
                        next.dist = cur_wizard.dist + weight;
                        pq.offer(next);         //再插入，使之重新排序
                    }
                }
            }

            List<Integer> res = new ArrayList<>();
            int t = target;
            while( t != source){
                res.add(t);
                t = parents[t];
            }
            res.add(source);
            Collections.reverse(res);
            return  res;
        }
    }
    public static void main(String[] args) {
        Solution sol = new TenWizards().new Solution();
        Solution2 sol2 = new TenWizards().new Solution2();

        int[][] ids = {{1, 5, 9}, {2, 3, 9}, {4}, {}, {}, {9}, {}, {}, {}, {}};
        List<List<Integer>> wizards = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            List<Integer> wizard = new ArrayList<>();
            for (int j = 0; j < ids[i].length; j++) {
                wizard.add(ids[i][j]);
            }
            wizards.add(wizard);
        }

        List<Integer> res = sol.getShortestPath(wizards, 0, 9);
        List<Integer> res2 = sol2.getShortestPath(wizards, 0, 9);

        System.out.println(res);//0 5 9
        System.out.println(res2);//0 5 9

    }
}
