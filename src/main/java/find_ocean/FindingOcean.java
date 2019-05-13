package main.java.find_ocean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindingOcean {
    /*
        Finding Ocean
        思路：BFS
     */
    public class Solution {

        public void floodFill(char[][] board, int i, int j, char oldColor, char newColor) {
            if (board[i][j] != oldColor || board[i][j] == newColor) {
                return;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(i * board[0].length + j);
            board[i][j] = newColor;

            while (!queue.isEmpty()) {
                int pos = queue.poll();
                int m = pos / board[0].length;
                int n = pos % board[0].length;

                if (m + 1 < board.length && board[m + 1][n] == oldColor) {
                    queue.add((m + 1) * board[0].length + n);
                    board[m + 1][n] = newColor;
                }
                if (m - 1 >= 0 && board[m - 1][n] == oldColor) {
                    queue.add((m - 1) * board[0].length + n);
                    board[m - 1][n] = newColor;
                }
                if (n + 1 < board[0].length && board[m][n + 1] == oldColor) {
                    queue.add(m * board[0].length + n + 1);
                    board[m][n + 1] = newColor;
                }
                if (n - 1 >= 0 && board[m][n - 1] == oldColor) {
                    queue.add(m * board[0].length + n - 1);
                    board[m][n - 1] = newColor;
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new FindingOcean().new Solution();
        List<String> testData = new ArrayList<String>() {{
            add("WWWLLLW");
            add("WWLLLWW");
            add("WLLLLWW");
        }};
        char[][] map = new char[testData.size()][testData.get(0).length()];
        for (int i = 0; i < testData.size(); i++)
            for (int j = 0; j < testData.get(i).length(); j++)
                map[i][j] = testData.get(i).charAt(j);

        sol.floodFill(map, 0, 0, 'W', 'O');

        //打印结果
        for (int i = 0; i < testData.size(); i++){
            System.out.println(new String(map[i]));
        }

        System.out.println(map[0][0]);//0


        testData = new ArrayList<String>() {{
            add("LLLLLLLLLLLLLLLLLLLL");
            add("LLLLLLLLLLLLLLLLLLLL");
            add("LLLLLLLLLLLLLLWLLLLL");
            add("LLWWLLLLLLLLLLLLLLLL");
            add("LLWWLLLLLLLLLLLLLLLL");
            add("LLLLLLLLLLLLLLLLLLLL");
            add("LLLLLLLWWLLLLLLLLLLL");
            add("LLLLLLLLWWLLLLLLLLLL");
            add("LLLLLLLLLWWWLLLLLLLL");
            add("LLLLLLLLLLWWWWWWLLLL");
            add("LLLLLLLLLLWWWWWWLLLL");
            add("LLLLLLLLLLWWWWWWLLLL");
            add("LLLLWWLLLLWWWWWWLLLL");
            add("LLLLWWWLLLWWWWWWWWWW");
            add("LLLLLWWWWWWWWWWWLLLL");
            add("LLLLLLLLLLLLLLWWWWLL");
            add("LLLLLLLLLLLLLLWLLLLL");
            add("LLLLWLLLLLLLLLLLLWLL");
            add("LLLLLLLLLLLLLLLLLLWL");
        }};

        map = new char[testData.size()][testData.get(0).length()];
        for (int i = 0; i < testData.size(); i++)
            for (int j = 0; j < testData.get(i).length(); j++)
                map[i][j] = testData.get(i).charAt(j);
        sol.floodFill(map, 9, 12, 'W', 'O');

        //打印结果
        for (int i = 0; i < testData.size(); i++){
            System.out.println(new String(map[i]));
        }
        System.out.println( map[9][11]);//0
    }

}

