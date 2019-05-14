package main.java.simulate_diplomacy;

import java.util.*;

public class SimulateDiplomacy {

    public  List<String> evaluateActions(List<String> actions){
        Map<String,Army> armys = new HashMap<>();
        Map<String,City> citys= new HashMap<>();

        Comparator<Army> cmp = new Comparator<Army>() {
            @Override
            public int compare(Army o1, Army o2) {
                return o2.strength - o1.strength;
            }
        };

        //1.初始化城市
        for (String action : actions) {
            //将每个aciton拆解
            String[] arr = action.split(" ");
            String armyname = arr[0];
            String armycity = arr[1];
            String ac = arr[2];

            //初始化城市
            if(!citys.containsKey(armycity))
                citys.put(armycity,new City(armycity));
            if(ac.equals("Move") && !citys.containsKey(arr[3]))
                citys.put(arr[3],new City(arr[3]));

            //初始化军队
            if(!armys.containsKey(armyname)){
                armys.put(armyname,new Army(armyname,armycity));
                if(citys.get(armycity).armys.size() == 0)
                    citys.get(armycity).armys.add(armys.get(armyname));
            }

        }

        //2.确定军队最终位置
        for (String action : actions) {
            //将每个aciton拆解
            String[] arr = action.split(" ");
            if(arr.length == 4 &&  arr[2].equals("Move") ){
                String armyname = arr[0];
                String fromcityname = arr[1];
                String tocityname = arr[3];

                City fromCity = citys.get(fromcityname);
                City toCity = citys.get(tocityname);

                Army army = armys.get(armyname);
                toCity.armys.add(army);

                //从原来城市删除
                if(fromCity != null && fromCity.armys.contains(army)){
                    fromCity.armys.remove(army);
                }
            }
        }

        //3.确定支援关系
        for (String action : actions) {
            //将每个aciton拆解
            String[] arr = action.split(" ");
            if (arr.length == 4 && arr[2].equals("Support")) {
                String armyname = arr[0];
                String fromcityname = arr[1];
                String supportarmyname = arr[3];

                Army supportArmy = armys.get(supportarmyname);

                //没有被攻击         && 对方没有被攻击(在交战)
                if (citys.get(fromcityname).armys.size() < 2 && citys.get(supportArmy.oriCity).armys.size() < 2) {
                    supportArmy.strength++;
                }
            }
        }

        //4.处理返回结果
        List<String> res = new ArrayList<>();
        for (City city : citys.values()) {

            if (city.armys.size() == 1)
                res.add("" + city.armys.get(0).name + " " + city.name);
            else if (city.armys.size() > 1) {
                Collections.sort(city.armys, cmp);

                Army army0 = city.armys.get(0);
                Army army1 = city.armys.get(1);
                //全死
                if (army0.strength == army1.strength) {
                    for (Army army : city.armys)
                        res.add("" + army.name + " [dead]");

                } else {
                    res.add("" + army0.name + " " + city.name);
                    for (int i = 1; i < city.armys.size(); i++)
                        res.add("" + city.armys.get(i).name + " [dead]");
                }
            }
        }
        Collections.sort(res);
        return res;
    }
    public   class Army {
        String name;
        int strength = 1;
        String oriCity;
        Army(String _name,String _oriCity){
            name = _name;
            oriCity = _oriCity;
        }
    }
    public  class City {
        String name;
        List<Army> armys = new ArrayList<>();
        City(String _name){
            name = _name;
        }
    }
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
//        input.add("A Munich Hold");
//        input.add("B Bohemia Move Munich");
//        input.add("C Warsaw Support B");


//        input.add("A Munich Hold");
//        input.add("B Warsaw Move Bohemia");

//        input.add("A Munich Hold");
//        input.add("B Bohemia Move Munich");
//        input.add("C Prussia Move Munich");
//        input.add("D Warsaw Hold");

        input.add("A Munich Support B");
        input.add("B Bohemia Move Prussia");
        input.add("C Prussia Hold");
        input.add("D Warsaw Move Munich");

//        input.add("A Munich Support B");
//        input.add("B Oakland Move Munich");


        SimulateDiplomacy t1 = new SimulateDiplomacy();
        List<String> res = t1.evaluateActions(input);
        for(String str : res)
            System.out.println(str);
    }
}
