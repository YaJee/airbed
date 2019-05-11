package main.java.implement_queue_with_fixedArray;

import java.util.ArrayList;
import java.util.List;

public class ImplementQueueWithFixedArray {
    public QueueWithFixedArray queue;
    public ImplementQueueWithFixedArray(int num){
        this.queue = new QueueWithFixedArray(num);
    }
    /*
    实现 Queue : 使用固定大小的 Arrays
     */
    public class QueueWithFixedArray {
        private int fixedSize;

        private int count;
        private int head;
        private int tail;
        private List<Object> headList;
        private List<Object> tailList;

        public QueueWithFixedArray(int fixedSize){
            this.fixedSize = fixedSize;

            this.count = 0;
            this.head = 0;
            this.tail = 0;
            this.headList = new ArrayList<>();
            this.tailList = this.headList;
        }
        public void offer(int num){
            if(tail == fixedSize -1){
                List<Object> newList = new ArrayList<>();
                newList.add(num);
                tailList.add(newList);
                tailList = newList;
                tail = 0;
            }else {
                tailList.add(num);
            }
            count++;
            tail++;
        }
        public Integer poll(){
            if(count == 0){
                return null;
            }
            int num = (int)headList.get(head);
            head++;
            count--;

            if(head == fixedSize - 1){
                List<Object> newList = (List<Object>)headList.get(head);
                headList.clear();
                headList = newList;
                head = 0;
            }
            return num;
        }
        public int size(){
            return count;
        }
    }

    public static   void main(String[] args) {
        QueueWithFixedArray queue = new ImplementQueueWithFixedArray(5).queue;

        queue.offer(1);
        queue.offer(2);
        int res = queue.poll();
        System.out.println(res);//1
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        res = queue.poll();
        System.out.println(res);//2
        res = queue.poll();
        System.out.println(res);//3
    }
}
