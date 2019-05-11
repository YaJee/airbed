package main.java.file_system;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {

    public class Solution {
        Map<String,Integer> pathMap;

        public Solution(){
            this.pathMap = new HashMap<>();
            this.pathMap.put("",0);
        }
        public boolean create(String path, int value){
            if(pathMap.containsKey(path)) return false;

            //最后一个"/"的下标
            int lastIndex = path.lastIndexOf("/");
            if(! pathMap.containsKey(path.substring(0,lastIndex))){
                return false;
            }

            pathMap.put(path,value);
            return true;
        }
        public boolean set(String path, int value){
            if( !pathMap.containsKey(path)) return false;

            pathMap.put(path,value);
            return true;
        }
        public Integer get(String path){
            return pathMap.get(path);
        }
    }

    public static void main(String[] args) {
        Solution sol = new FileSystem().new Solution();
        System.out.println(sol.create("/a",1));
        System.out.println((int)sol.get("/a"));
        System.out.println(sol.create("/a/b",2));
        System.out.println((int)sol.get("/a/b"));
        System.out.println(sol.set("/a/b",3));
        System.out.println((int)sol.get("/a/b"));
        System.out.println(sol.create("/c/d",4));
        System.out.println(sol.get("/c/d"));
        System.out.println(sol.set("/c/d",4));
    }
}
