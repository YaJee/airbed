package main.java.common;

public class Inteval implements Comparable<Inteval> {

    public int start;
    public int end;
    public Inteval(int s, int e){
        start = s;
        end = e;
    }
    @Override
    public String toString(){
        return "["+start+","+end+"]";
    }
    @Override
    public int compareTo(Inteval that){
//        if(this.start == that.start){
//            return that.end - this.end; //end 按 降序排列
//        }
        return this.start - that.start;
    }
}