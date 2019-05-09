package main.java.meeting_time;

import main.java.common.Inteval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingTime {

    /**
     * 思路：
     *  1.将所有interval 放入 ori_intervals
     *  2.对ori_intervals 排序 : 按 start 升序
     *  3.对ori_intervals 合并区间
     *  4.找出空隙
     */
    /**
     * @param intervals     每个人要参加会议的集合的集合
     * @param k             人数
     * @return
     */
    public List<Inteval> getAvailableIntervals(List<List<Inteval>> intervals, int k){
        //1.将所有interval 放入 ori_intervals
        List<Inteval> ori_intervals = new ArrayList<>();
        for(int i = 0; i < k; i++){
            for(int j = 0; j < intervals.get(i).size(); j++){
                ori_intervals.add(intervals.get(i).get(j));
            }
        }
        //2.对ori_intervals 排序 : 按 start 升序
        Collections.sort(ori_intervals);

        //3.合并区间
        List<Inteval> comb_intervals = new ArrayList<>();
        int tmp_start = ori_intervals.get(0).start;
        int tmp_end = ori_intervals.get(0).end;

        for(int i = 1; i < ori_intervals.size(); i++){
            if(tmp_end > ori_intervals.get(i).start){
                if(ori_intervals.get(i).end > tmp_end)
                    tmp_end = ori_intervals.get(i).end;
            }else{
                comb_intervals.add(new Inteval(tmp_start,tmp_end));
                tmp_start = ori_intervals.get(i).start;
                tmp_end = ori_intervals.get(i).end;
            }
        }
        comb_intervals.add(new Inteval(tmp_start,tmp_end));

        //4.找出空隙
        Collections.sort(comb_intervals);
        List<Inteval> res_intervals = new ArrayList<>();
        for(int i = 1; i < comb_intervals.size(); i++){
            int t_start = comb_intervals.get(i-1).end;
            int t_end = comb_intervals.get(i).start;
            res_intervals.add(new Inteval(t_start,t_end));
        }
        return res_intervals;
    }


    public static void main(String[] args) {
        List<List<Inteval>> intervals = new ArrayList<List<Inteval>>(){{
            add(new ArrayList<Inteval>(){{
                add(new Inteval(1,3));
                add(new Inteval(6,7));
            }});
            add(new ArrayList<Inteval>(){{
                add(new Inteval(2,4));
            }});
            add(new ArrayList<Inteval>(){{
                add(new Inteval(2,3));
                add(new Inteval(9,12));
            }});
        }};
        MeetingTime sol = new MeetingTime();


        List<Inteval> res = sol.getAvailableIntervals(intervals,3);
        for(int i = 0; i < res.size(); i++)
            System.out.print(res.get(i).toString() + " ");
       // System.out.println(res);
    }
}

