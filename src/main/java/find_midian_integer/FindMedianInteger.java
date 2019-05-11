package main.java.find_midian_integer;

public class FindMedianInteger {

    /**
     * 题目：
     * 在一个存有巨量 整数的文件中，找到中位数
     * 因为是整数，所以就确定了 数值的边界，Integer.MIN_VALUE， Integer.MAX_VALUE
     */
    /*
    思路：
        二分法：
        1。设定最小值为 min, 最大值为 max，num = (min + (max-min)/2)
        2。遍历文件，统计文件的数量 totaal
        3. 统计 文件中 小于 num的 数量count ，
                如果count < total/2,则更新 num = (num + (max-num)/2),重复第3
                如果count > total/2,则更新 num = (min + (num-min)/2),重复第3
                如果count == total/2,返回符合要求的数字,结束
     */
    //                              count
    private long search(int[] nums, int k, long left, long right){
        if(left >= right) return left;

        long res = left;
        long guess = left + (right - left) / 2;
        int count = 0;
        for(int num  : nums){
            if(num <= guess){
                count++;
                res = Math.max(res,num);
            }
        }
        if(count == k)
            return res;
        else if(count < k){
            return search(nums, k, Math.max(res + 1, guess), right);
        }else {
            return search(nums, k, left, res);
        }
    }
    //我们用 数组 代替 大文件，算法思想是一致的
    public double findMedian(int[] nums){
        int len = 0;
        for(int num : nums){
            len++;
        }
        if(len % 2 == 1){
            return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE );
        }else {
            return (double) (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE)
                    + search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
        }
    }
    public static void main(String[] args) {
        FindMedianInteger sol = new FindMedianInteger();
        int[] nums1 = new int[]{3, -2, 7};
        int[] nums2 = new int[]{-100, 99, 3, 0, 5, 7, 11, 66, -33};
        int[] nums3 = new int[]{4, -100, 99, 3, 0, 5, 7, 11, 66, -33};

        System.out.println(sol.findMedian(nums1));//3.0
        System.out.println(sol.findMedian(nums2));//5.0
        System.out.println(sol.findMedian(nums3));//4.5
    }

}
