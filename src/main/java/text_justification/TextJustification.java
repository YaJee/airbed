package main.java.text_justification;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {


    public List<String> fullJustify(String[] words, int maxWidth){
        int left=0;
        List<String> result = new ArrayList<>();

        while( left < words.length){
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }
        return result;
    }

    /**
     * 将单词调整为一行
     */
    private String justify(int left, int right, String[] words, int maxWidht){

        //只有一个单词
        if(right - left == 0) return padResult(words[left],maxWidht);

        boolean isLastLine = right == words.length - 1;
        int numSpaces = right - left;                                       //间隔总数
        int totalSpace = maxWidht - wordsLength(left, right, words);        //空格总数

        //是最后一行的话，单词用 " " 间隔
        String space = isLastLine ? " " : getBlank(totalSpace / numSpaces);
        int remainder = isLastLine ? 0 : totalSpace % numSpaces; //剩余空格数

        StringBuilder result = new StringBuilder();
        //最后多加了一次space
        for(int i = left; i <= right; i++){
            result.append(words[i])
                    .append(space)
                    .append(remainder-- > 0 ? " " : "");
        }
        return padResult(result.toString().trim(), maxWidht);

    }
    //将result 末尾补充maxWidth-result.length个空格
    private String padResult(String result, int maxWidth){
        return result + getBlank(maxWidth - result.length());
    }
    //所有单词的字符长度
    private int wordsLength(int left, int right, String[] words){
        int wordsLength = 0;
        for(int i = left; i <= right; i++) wordsLength += words[i].length();
        return wordsLength;
    }
    //获得长度为length的空字符串
    private String getBlank(int length){
        return new String(new char[length]).replace('\0',' ');
    }

    /**
     * 找到可构成一行的最右边的单词下标
     */
    public int findRight(int left, String[] words, int maxWidth){
        int right = left;
        int sum = words[right++].length();

        while (right < words.length && (sum +1 + words[right].length()) <= maxWidth)
            sum += 1 + words[right++].length();

        return right - 1;
    }


    public static void main(String[] args) {
        TextJustification sol = new TextJustification();
        String[] words = new String[]{
                "This", "is", "an", "example", "of", "text", "justification."
        };

        System.out.println(sol.fullJustify(words,16));
        List<String> res = sol.fullJustify(words,16);
        for(String str : res){
            System.out.println("|"+str+"|");
        }
    }
}
