package main.java.sliding_puzzle;

import java.util.*;

public class SlidingPuzzle {


    /**
     * canSlove:是否能移动成功：成功返回移动次数，失败返回-1
     * 思路：
     * 1.将二维数组 映射到 一维空间，即映射为字符串
     * 2.将 board 中的 每一个状态 都作为图的一个节点
     * 3.则只需要找到 start 到 target 最短距离 即可；target : "123450"
     * 4.采用BFS,每次在字符串中交换"0"和其他能到达的位置，并用hashset存储这个状态
     */
    public int canSlove(int[][] board){
        //1.将二维 映射到 一维
        String target = "123450";
        String start = "";

        //2.初始化start
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                start += board[i][j];
            }
        }

        HashSet<String> visited = new HashSet<>();
        //2."0" 可以被交换的位置 有：
        int[][] dirs = new int[][] {
                {1, 3 },            //在下标0 可移动到下标 1，3
                {0, 2, 4 },         //在下标1 可移动到下标 0，2，4
                {1, 5 },
                {0, 4 },
                {1, 3, 5 },
                {2, 4 }
        };


        Queue<String> queue = new LinkedList<>();
        //将当前状态入队列
        queue.offer(start);
        visited.add(start);
        int res = 0;
        //4.
        while (!queue.isEmpty()){

            int size = queue.size();
            for(int i = 0; i < size; i++){
                String cur = queue.poll();
                if(cur.equals(target)){
                    return res;
                }
                int zero_pos = cur.indexOf('0');
                //交换到 可能的 位置
                for(int dir : dirs[zero_pos]){
                    String next = swap(cur,zero_pos,dir);
                    if(visited.contains(next)){
                        continue;
                    }
                    visited.add(next);
                    queue.offer(next);
                }
            }
            res++;
        }
        return -1;
    }

    private String swap(String str, int i, int j){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(i,str.charAt(j));
        sb.setCharAt(j,str.charAt(i));
        return sb.toString();
    }

    /**
     * getPath：得到移动路径
     * 思路：
     * 1.将二维数组 映射到 一维空间，即映射为字符串
     * 2.将 board 中的 每一个状态 都作为图的一个节点
     * 3.则只需要找到 start 到 target 最短距离对应的路径 即可；target : "123450"
     * 4.采用BFS,每次在字符串中交换"0"和其他能到达的位置，并用hashset存储这个状态，用Queue存储这个状态对应的路径
     */
    public List<String> getPath(int[][] board){
        //1.将二维 映射到 一维
        String target = "123450";
        String start = "";

        //2.初始化start
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                start += board[i][j];
            }
        }

        HashSet<String> visited = new HashSet<>();
        //2."0" 可以被交换的位置 有：
        int[][] dirs = new int[][] {
                {1, 3 },            //在下标0 可移动到下标 1，3
                {0, 2, 4 },         //在下标1 可移动到下标 0，2，4
                {1, 5 },
                {0, 4 },
                {1, 3, 5 },
                {2, 4 }
        };
        //2."0" 可交换位置对应的移动方向
        String[][] dir_words = new String[][]{
                {"right", "down"},
                {"left","right","down"},
                {"left","down"},
                {"up","right"},
                {"up","left","right"},
                {"up","left"}
        };


        Queue<String> queue = new LinkedList<>();
        //存储 移动的路径
        Queue<List<String>> paths = new LinkedList<>();

        //将当前状态入队列
        queue.offer(start);
        paths.offer(new ArrayList<>());
        visited.add(start);
        int res = 0;
        //4.
        while (!queue.isEmpty()){

            int size = queue.size();

            for(int i = 0; i < size; i++){
                String cur = queue.poll();
                List<String> cur_path = paths.poll();

                if(cur.equals(target)){
                    return cur_path;

                }
                int zero_pos = cur.indexOf('0');
                //交换到 可能的 位置
                for(int k=0; k<dirs[zero_pos].length; k++){
                    String next = swap(cur,zero_pos,dirs[zero_pos][k]);
                    if(visited.contains(next)){
                        continue;
                    }
                    List<String> newPath = new ArrayList<>(cur_path);
                    newPath.add(dir_words[zero_pos][k]);

                    visited.add(next);
                    queue.offer(next);
                    paths.offer(newPath);
                }

            }

        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 0, 5}
        };
        int[][] matrix2 = {
                {4, 1, 2},
                {5, 0, 3}
        };
        SlidingPuzzle sol = new SlidingPuzzle();
        int res_count = sol.canSlove(matrix2);

        List<String> res = sol.getPath(matrix2);

        System.out.println(res_count);
        System.out.println(res);

    }
}
