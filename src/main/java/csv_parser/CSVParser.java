package main.java.csv_parser;

import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    /**
     * 思路：有限状态机
     * 1.把多余的引号去掉
     *  状态 0：无引号
     *  转改 1：有多余引号
     * 2.把每一个单词放到list中
     */

    public static List<String> myParse(String string){
        List<String> resArr = new ArrayList<>();
        boolean flag = false;
        StringBuilder sb = new StringBuilder();

        int len_str = string.length();
        //1.把多余的引号去掉
        for(int i=0; i<len_str; i++){
            //状态0
            if(flag == false){
                if(string.charAt(i) == '\"'){
                    flag = true;
                }else if(string.charAt(i) == ','){
                    resArr.add(sb.toString());
                    sb.setLength(0);
                }else{
                    sb.append(string.charAt(i));
                }
            }
            //状态1：有多余引号
            else{
                if(string.charAt(i) != '\"'){
                    sb.append(string.charAt(i));
                }else{
                    //两两出现时，加一个
                    if(i < (len_str-1) && string.charAt(i+1) == '\"'){
                        sb.append("\"");
                        i++;
                    }else{//状态转换
                        flag = false;
                    }
                }
            }
        }
        if(sb.length() > 0){
            resArr.add(sb.toString());
        }
        return resArr;

    }
    public static void main(String[] args) {
        //              John ""Ma"" pi,male,Doe,"NerYork,ShangHai",000010, Beijing, 23
        String text = "\"John \"\"Ma\"\" pi\",male,Doe,\"NerYork,ShangHai\",000010, Beijing, 23\"";
        System.out.println(text);
        List<String> res = myParse(text);
        System.out.println(res.size());//7
        System.out.println(res);
    }
}
