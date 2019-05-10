package main.java.regular_expression;

public class RegularExpression {


    /**
     * @param s 待匹配 的 字符串
     * @param p 匹配模版
     * @return
     */
    //dp[i][j] ： s的前i个字符是否和 p的前j个匹配

    // '.' 匹配任意单个字符。
    // '*' 匹配零个或多个前面的元素。
    // '+' 匹配一个或多个前面的元素。
    public boolean isMatch(String s, String p){

        if(s == null || p == null) return false;

        boolean [][] dp = new boolean[s.length()+1][p.length()+1];

        //1.初始化
        dp[0][0] = true;
        for(int j = 0; j < p.length(); j++){
            if(p.charAt(j) == '*' && dp[0][j-1]){
                dp[0][j+1] = true;
            }
        }

        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < p.length(); j++){
                if(p.charAt(j) == s.charAt(i)){
                    dp[i+1][j+1] = dp[i][j];
                }
                if(p.charAt(j) == '.'){
                    dp[i+1][j+1] = dp[i][j];
                }

                if(p.charAt(j) == '*' || p.charAt(j) == '+'){
                    if(p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(j)){

                        if(p.charAt(j) == '*'){
                            dp[i+1][j+1] = (dp[i][j+1] || dp[i+1][j] || dp[i+1][j-1]);
                        }else{
                            dp[i+1][j+1] = (dp[i][j+1] || dp[i+1][j]);
                        }

                    }else{   //如果p的上一个 和 s[i]不匹配

                        if(p.charAt(j) == '*')
                            dp[i+1][j+1] = dp[i+1][j-1];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public static void main(String[] args) {
        RegularExpression sol = new RegularExpression();

        System.out.println(sol.isMatch("saaaa", "sa*"));
        System.out.println(sol.isMatch("ssaaaa", "s+a*"));
        System.out.println(sol.isMatch("saaaa", "s+a*"));
        System.out.println(sol.isMatch("saaaa", "s+b*"));
        System.out.println(sol.isMatch("saaaab", "s+a*"));
        System.out.println(sol.isMatch("saaaab", "s+b*"));

    }
}

