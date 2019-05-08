package main.java.display_page;

import java.util.*;

public class DisplayPage {

    /**
     * 定义set：记录 当前页 的重复host_id
     * 定义res：用于添加记录作为返回值
     * 定义reachEnd：标记 是否遍历到input最后一个元素
     * 过程：
     * 遍历input,优先将set中不存在的记录添加进 res;
     * 如果遍历到最后一个，重新设置遍历器，并将reachEnd = ture
     * 当reachEnd = ture，无论set中是否存在，都将记录加入res;
     */
    public class Solution {

        public List<String> displayPages(List<String> input, int pageSize) {
            List<String> res = new ArrayList<>();
            if (input == null || input.size() == 0) {
                return res;
            }

            Set<String> set = new HashSet<>();       //记录有无重复
            Iterator<String> iter = input.iterator();
            boolean reachEnd = false;
            int count = 0;
            while (iter.hasNext()) {
                String curr = iter.next();
                String hostId = curr.split(",")[0];

                if (!set.contains(hostId) || reachEnd) {//hostid 未重复
                    res.add(curr);
                    set.add(hostId);
                    iter.remove();
                    count++;
                }

                if (count == pageSize) {        //添加分页 分隔符
                    if (!input.isEmpty()) {
                        res.add(" ");
                    }
                    set.clear();
                    count = 0;
                    reachEnd = false;
                    iter = input.iterator();
                }

                if (!iter.hasNext()) {           //到达最后一个
                    iter = input.iterator();
                    reachEnd = true;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Solution sol = new DisplayPage().new Solution();
        String[] strs = new String[]{
                "1,28,310.6,SF",
                "4,5,204.1,SF",
                "20,7,203.2,Oakland",
                "6,8,202.2,SF",
                "6,10,199.1,SF",
                "1,16,190.4,SF",
                "6,29,185.2,SF",
                "7,20,180.1,SF",
                "6,21,162.1,SF",
                "2,18,161.2,SF",
                "2,30,149.1,SF",
                "3,76,146.2,SF",
                "2,14,141.1,San Jose"

        };
        List<String> input = new ArrayList<>(Arrays.asList(strs));
        List<String> result = sol.displayPages(input, 5);
        for(int i=0; i<result.size(); i++){
            System.out.println(result.get(i));
        }
    }

}
