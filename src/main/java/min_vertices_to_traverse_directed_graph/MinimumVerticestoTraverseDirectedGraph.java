package main.java.min_vertices_to_traverse_directed_graph;


import java.util.*;


public class MinimumVerticestoTraverseDirectedGraph {
    /*
        Minimum Vertices to Traverse Directed Graph - DFS
     */
    public class Solution {
        /**
         *
         * @param res           返回结果
         * @param nodes         邻接表
         * @param cur           当前访问的 顶点
         * @param start
         * @param visited       以访问的点
         * @param currVisited   调用一次search中访问的点
         */
        private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start,
                            Set<Integer> visited, Set<Integer> currVisited){
            currVisited.add(cur);
            visited.add(cur);
            //遍历顶点cur 连接的顶点
            for(int next : nodes.get(cur)){

                //如果res中包含next，说明其他顶点可以到达next,则移除next
                //next != start : 确保 next 和 start 不在一个环
                if(res.contains(next) && next != start){
                    res.remove(next);
                }

                if(!currVisited.contains(next)){
                    search(res, nodes, next, start, visited, currVisited);
                }
            }
        }

        /**
         * @param edges 边集
         * @param n     顶点数
         * @return
         */
       public List<Integer> getMin(int[][] edges, int n){
           //邻接表
           Map<Integer, Set<Integer>> nodes = new HashMap<>();

           //1.构建邻接表
           for(int i = 0; i < n; i++){
               nodes.put(i, new HashSet<>());
           }
           for(int [] edge : edges){
               nodes.get(edge[0]).add(edge[1]);
           }

           //2.遍历顶点
           Set<Integer> visited = new HashSet<>();
           Set<Integer> res = new HashSet<>();
           for( int i = 0; i < n; i++){
               if(!visited.contains(i)){
                   res.add(i);
                   visited.add(i);
                   //DFS
                   search(res, nodes, i, i, visited, new HashSet<>());
               }
           }
           return new ArrayList<>(res);
       }
    }

    public static void main(String[] args) {
        Solution sol = new MinimumVerticestoTraverseDirectedGraph().new Solution();
        ////    1->2->3->1, 2->0->0
        ////      0  1  2  3
        ////    0[1, 0, 0, 0]
        ////    1[0, 0, 1, 0]
        ////    2[1, 0, 0, 1]
        ////    3[0, 1, 0, 0]
        int[][] edges = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
        List<Integer> res = sol.getMin(edges, 4);
        System.out.println(res);
        System.out.println(res.size());//1



        ////    0->1->0, 2->3->2->1
        ////      0  1  2  3
        ////    0[0, 1, 0, 0]
        ////    1[1, 0, 0, 0]
        ////    2[0, 1, 0, 1]
        ////    3[0, 0, 1, 0]
        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {2, 3}, {3, 2}};
        res = sol.getMin(edges, 4);
        System.out.println(res);
        System.out.println(res.size());//1



        ////    3->2->1->0  0->1 3->1
        ////      0  1  2  3
        ////    0[0, 1, 0, 0]
        ////    1[1, 0, 0, 0]
        ////    2[0, 1, 0, 0]
        ////    3[0, 1, 1, 0]
        edges = new int[][]{{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
        res = sol.getMin(edges, 4);
        System.out.println(res);
        System.out.println(res.size());//1

        ////      0  1  2  3  4  5  6  7  8  9
        ////    0[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        ////    1[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        ////    2[0, 0, 0 ,0, 0, 0, 0, 0, 0, 1]
        ////    3[0, 0, 0, 1, 0, 1, 0, 1, 0, 0]
        ////    4[0, 0, 0, 0, 0, 0 ,0, 0, 1, 0]
        ////    5[0, 0, 0, 0, 0, 0, 0, 0, 1, 0]
        ////    6[0, 0, 0, 0, 0, 0, 1, 0, 0 ,0]
        ////    7[0, 0, 0, 0, 1, 0, 0, 0, 0, 0]
        ////    8[0, 0, 0, 0, 0, 0, 0, 1, 0, 0]
        ////    9[0, 0, 0, 1, 0, 0, 1, 0, 0, 0]
        edges = new int[][]{{2, 9}, {3, 3}, {3, 5}, {3, 7}, {4, 8}, {5, 8}, {6, 6}, {7, 4}, {8, 7}, {9, 3}, {9, 6}};
        res = sol.getMin(edges, 10);
        System.out.println(res);
        System.out.println(res.size());//3

        edges = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}};
        edges = new int[][]{{0, 1}, {1, 0}, {2, 3}, {3, 2}};
        res = sol.getMin(edges, 4);
        System.out.println(res);
        System.out.println(res.size());//3
    }

}

