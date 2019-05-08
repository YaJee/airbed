package main.java.vector2d_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
思路：
  ● 维护两个变量row和col，将row和col初始化为0;
  ● 对于next函数，获得返回值后，执行后移操作：检查当前row是否小于总行数，col是否>=当前行的列数，如果成立，说明要转到下一行，则row自增1，col初始化为0;
  ● 对于 hasNext 函数，检查当前row是否<总行数，col是否<当前行的列数，如果都成立返回true,否则返回flase
  ● 对于remove()函数，判断是否col==0，如果col == 0,则删除上一行最后一个元素，否则删本行上一个元素即可。如果删除元素后导致元素所在行空了，则删除所在行。
 */
public class Vector2DIterator implements Iterator<Integer>{

    private int row,col;
    private List<List<Integer>> vec2d;

    public Vector2DIterator(List<List<Integer>> vec){
        row = 0;
        col = 0;
        vec2d = vec;
    }

    @Override
    public boolean hasNext() {

        return row < vec2d.size() && col < vec2d.get(row).size();
    }

    @Override
    public Integer next() {
        int val = vec2d.get(row).get(col);
        col++;

        //后移
        while(row < vec2d.size() && col >= vec2d.get(row).size()){
            col = 0;
            row ++;
        }
        return val;
    }

    /**
     * 删除上一次调用 next()的值
     */
    @Override
    public void remove() {

        List<Integer> listToBeRemoved;

        int rowToBeRemoved = row;
        int colToBeRemoved = col;
        if(col == 0){//删除上一行最后一个元素
            rowToBeRemoved --;
            colToBeRemoved = vec2d.get(rowToBeRemoved).size() - 1;
            listToBeRemoved = vec2d.get(rowToBeRemoved);
        }else {
            colToBeRemoved--;
            listToBeRemoved = vec2d.get(rowToBeRemoved);
        }
        listToBeRemoved.remove(colToBeRemoved);
        if(listToBeRemoved.isEmpty()){
            vec2d.remove(listToBeRemoved);
            row --;
        }
        if(col > 0) col --;
    }

    public static void main(String[] args) {
        List<List<Integer>> test = new ArrayList<>();

        test.add(new ArrayList<Integer>(){{
            add(1);
            add(2);
        }});
        test.add(new ArrayList<Integer>(){{
            add(3);
        }});
        test.add(new ArrayList<Integer>(){{
            add(4);
            add(5);
            add(6);
        }});

        Vector2DIterator vector = new Vector2DIterator(test);

        while (vector.hasNext()){
            System.out.println(vector.next());
        }
        vector.remove();
        System.out.println(vector.vec2d.size());
        System.out.println(vector.hasNext());


    }
}
