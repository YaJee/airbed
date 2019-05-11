package main.java.ip_range_to_cidr;

import java.util.ArrayList;
import java.util.List;

public class IPRangeToCIDR {

    public class Solution {

        //将ip 转换为 数
        private long ipToLong(String StrIP){
            long[] ip = new long[4];
            String[] ipSec = StrIP.split("\\.");

            for(int k = 0; k < 4; k++){
                ip[k] = Long.valueOf(ipSec[k]);
            }
            return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
        }

        //longip 转化为标准格式ip
        private String longToIP(long longIP){
            StringBuffer sb = new StringBuffer("");
            sb.append(String.valueOf(longIP >>> 24));
            sb.append(".");
            sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
            sb.append(".");
            sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
            sb.append(".");
            sb.append(String.valueOf(longIP & 0x000000FF));
            sb.append(".");
            return sb.toString();
        }

        public List<String> ipRange2Cidr(String startIp, int range){
            //check params
            String a = "";
            long start = ipToLong(startIp);
            long end = start + range - 1;
            List<String> res = new ArrayList<>();

            while (start <= end){

                //1.计算ip二进制从左到右 第一个 1的位置，用来计算最多包含多少个ip
                // e.g. 00000001.00000001.00000001.01101100, return 4 (100)
                long locOfFirstOne = start & (-start);
                int curMask = 32 - (int) ( Math.log(locOfFirstOne)/ Math.log(2) );  //32 - 2 = 30

                //2.计算(end- start)个ip要 占用的二进制位 数,用来计算最多包含多少个ip
                // e.g. between 1.1.1.111 and 1.1.1.120, there are 10 IP address
                // 3 bits to represent 8 IPs, from 1.1.1.112 to 1.1.1.119 (119 - 112 + 1 = 8)
                double currRange = Math.log(end - start + 1) / Math.log(2);     //占用的位数
                int currRangeMask = 32 - (int) Math.floor(currRange);

                //3.取1，2步中可包含最少ip的掩码，即掩码取最大
                curMask = Math.max(currRangeMask, curMask);


                //4.
                String ip = longToIP(start);
                res.add(ip + "/" + curMask);

                //5.跟新start
                start += Math.pow(2,(32 - curMask));
            }
            return res;
        }
    }

    public static void main(String[] args) {

        Solution sol = new IPRangeToCIDR().new Solution();
        List<String> res = sol.ipRange2Cidr("255.0.0.7", 10);
        for(String str : res){
            System.out.println(str);
        }
        System.out.println();
        System.out.println();


        res = sol.ipRange2Cidr("1.1.1.0", 4);
        for(String str : res){
            System.out.println(str);
        }
        System.out.println();
        System.out.println();

        res = sol.ipRange2Cidr("1.1.1.1", 4);

        for(String str : res){
            System.out.println(str);
        }
    }
}
