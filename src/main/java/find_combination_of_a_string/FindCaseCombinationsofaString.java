package main.java.find_combination_of_a_string;

import java.util.ArrayList;
import java.util.List;

public class FindCaseCombinationsofaString {

    public List<String> strComb(String text){

        List<String> res = new ArrayList<>();
        if(text == null || text.length() == 0) return res;

        char[] chars = text.toCharArray();

        for(int i = 0; i < Math.pow(2,chars.length); i++){
            char [] cur = new char[chars.length];
            for(int j = 0; j < chars.length; j++){
                //将 i 的二进制 右移 j 位 ，然后和 1 位与运算
                boolean upper = ( i >>> j & 1 ) != 0 ? true : false;
                cur[j] = upper ? Character.toUpperCase(chars[j]) : Character.toLowerCase(chars[j]);
            }
            res.add(new String(cur));
        }
        return res;
    }

    public static void main(String[] args) {
        FindCaseCombinationsofaString sol = new FindCaseCombinationsofaString();
        List<String> res = sol.strComb("airbed");
        System.out.println(res.size());//64
        System.out.println(res.get(0));//"airbed
        System.out.println(res.get(1));//Airbed
        System.out.println(res.get(62));//"aIRBED
        System.out.println(res.get(63));//"AIRBED

    }

}
