package main.java.palindrome_pairs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {

    /**
     * step1 : 使用map 存储每一个 单词 和 下标
     * step2 : 对于数组中每一个单词，分割为str1 str2 两部分。检查str1 str2是否是 回文串
     *         如果 str1 是回文串，使用str1 作为中间部分，str2作为右部分，查看map中是否包含str2的逆串。
     *         如果包含，则使用这个字符串作为左部分，与中、右组成回文串。
     * step3 : 对str2左同样操作（str2作为中间部分）
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if(words.length == 0) return  res;

        //step 1
        Map<String, Integer> map = new HashMap<>();
        for(int i=0 ; i<words.length; i++){
            map.put(words[i],i);
        }

        for(int i=0; i<words.length; i++){

            for(int j=0; j<=words[i].length(); j++){

                String str1 = words[i].substring(0,j);
                String str2 = words[i].substring(j);

                //step 2
                if(isPalindrome(str1)){
                    String reverseStr2 = new StringBuilder(str2).reverse().toString();
                    //使str1.length() != 0 ，确保不放入重复的 结果
                    if(map.containsKey(reverseStr2) && map.get(reverseStr2) != i && str1.length() != 0){
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(map.get(reverseStr2));//left
                        newPair.add(i);//right
                        res.add(newPair);
                    }

                }

                if(isPalindrome(str2)){
                    String reverseStr1 = new StringBuilder(str1).reverse().toString();
                    if(map.containsKey(reverseStr1) && map.get(reverseStr1) != i ){
                        List<Integer> newPair = new ArrayList<>();
                        newPair.add(i);//left
                        newPair.add(map.get(reverseStr1));//right

                        res.add(newPair);
                    }
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        PalindromePairs pp = new PalindromePairs();

        String [] test = new String[]{"abcd", "dcba", "lls", "s", "sssll"};
        List<List<Integer>> res = pp.palindromePairs(test);

        System.out.println(res);
    }
}
