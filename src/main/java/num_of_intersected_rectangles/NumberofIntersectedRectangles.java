package main.java.num_of_intersected_rectangles;

import java.util.HashSet;
import java.util.Set;

public class NumberofIntersectedRectangles {


    /**
     * 思路：并查集
     */
    public class Solution {
        /**
         * 左下角：r1[0][0]：x, r1[0][1] : y
         * 右上脚：r1[1][0]：x, r1[1][1] : y
         */
        /**
         * 判断 r1 是否 和 r2 是包含关系；r1完全在r2内部 或 r2完全在r1内部
         * @param r1
         * @param r2
         * @return
         */
        private boolean intersect(int[][] r1, int[][] r2){
            return r1[0][0] < r2[0][0] && r1[0][1] < r2[0][1] && r1[1][0] > r2[1][0] &&  r1[1][1] > r2[1][1] ||
                    r2[0][0] < r1[0][0] && r2[0][1] < r1[0][1] && r2[1][0] > r1[1][0] &&  r2[1][1] > r1[1][1] ;
        }

        public int countIntersection(int [][][] rectangles){
            if(rectangles == null || rectangles.length == 0) return 0;

            int n = rectangles.length;

            int[] parents = new int[n];         //计算父节点

            for(int i = 0; i < n; i++){
                parents[i] = i;
            }
            //1
            for(int i = 0; i < n - 1; i++){
                for(int j = i + 1; j < n; j++){
                     if(intersect(rectangles[i], rectangles[j])){
                         int root1 = find(i, parents);
                         int root2 = find(j, parents);
                         if(root1 != root2){
                             parents[root1] = root2;
                         }
                     }
                }
            }

            //2.统计根矩形
            Set<Integer> set = new HashSet<>();
            for( int i = 0; i < n; i++){
                set.add( find(i,parents) );
            }
            return set.size();
        }
        private int find(int val, int[] parents){
            while( parents[val] != val){
                val = parents[val];
            }
            return val;
        }

    }
    public static void main(String[] args) {
        Solution sol = new NumberofIntersectedRectangles().new Solution();
        int[][][] rectangles = {
                {{-3, -2}, {2, 1}},
                {{10, 8}, {15, 10}},
                {{1, 0}, {7, 4}},
                {{12, 9}, {16, 12}},
                {{-2, -1}, {5, 3}}
        };
        System.out.println(sol.countIntersection(rectangles));//5

    }
}
