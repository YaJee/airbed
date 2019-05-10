package main.java.travel_buddy;

import java.util.*;

public class TravelBuddy {
    private List<Buddy> buddies;
    private Set<String> myWishList;

    public TravelBuddy(Set<String> myWishList, Map<String,Set<String>> friendsWishList){
        this.buddies = new ArrayList<>();
        this.myWishList = myWishList;

        //统计伙伴，如果相似度符合要求，则加入伙伴列表
        for(String name : friendsWishList.keySet()){
            Set<String> wishList = friendsWishList.get(name);

            //计算相似度
            Set<String> intersection = new HashSet<>(wishList);
            intersection.retainAll(myWishList);
            int similarity = intersection.size();

            //只有相似度 > 50%的 才是兄弟
            if(similarity >= wishList.size() / 2){
                buddies.add(new Buddy(name, similarity, wishList));
            }
        }
    }

    /**
     * @return  获取伙伴列表
     */
    public List<Buddy> getSortedBuddies(){
        Collections.sort(buddies);
        List<Buddy> res = new ArrayList<>(buddies);
        return res;
    }
    public List<String> recommendCities(int k){
        List<String> res = new ArrayList<>();
        List<Buddy> buddies = getSortedBuddies();

        int i=0;
        while (k > 0 && i < buddies.size()){
            //找到 myWhisList 中不存在 && res 也不存在的 城市列表
            Set<String> diff = new HashSet<>(buddies.get(i).wishList);
            diff.removeAll(myWishList);
            diff.removeAll(res);

            if(diff.size() <= k){
                res.addAll(diff);
                k -= diff.size();
                i++;
            }else {
                Iterator<String> it = diff.iterator();
                while (k > 0){
                    res.add(it.next());
                    k--;
                }
            }
        }
        return res;
    }

    class Buddy implements Comparable<Buddy>{

        String name;
        int similarity;
        Set<String> wishList;

        Buddy(String name, int similarity, Set<String> wishList){
            this.name = name;
            this.similarity = similarity;
            this.wishList = wishList;
        }
        @Override   //按 similarity 降序排列
        public int compareTo(Buddy that){
            return that.similarity/that.wishList.size() - this.similarity/this.wishList.size();
        }
    }

    public static void main(String[] args) {

        Set<String> myWishList = new HashSet<>(Arrays.asList(new String[]{"a", "b", "c", "d"}));

        Set<String> wishList1 = new HashSet<>(Arrays.asList(new String[]{"a", "b", "e", "f"}));//e f
        //  Set<String> wishList2 = new HashSet<>(Arrays.asList(new String[]{"a", "c", "d", "g"}));//g
        Set<String> wishList2 = new HashSet<>(Arrays.asList(new String[]{"a", "c", "f", "g"}));//g
        Set<String> wishList3 = new HashSet<>(Arrays.asList(new String[]{"c", "f", "e", "g"}));

        Map<String, Set<String>> friendWishLists = new HashMap<>();
        friendWishLists.put("Buddy1", wishList1);
        friendWishLists.put("Buddy2", wishList2);
        friendWishLists.put("Buddy3", wishList3);

        TravelBuddy sol = new TravelBuddy(myWishList, friendWishLists);

        //1.打印buddys
        List<TravelBuddy.Buddy> buddies = sol.getSortedBuddies();
        for(TravelBuddy.Buddy buddy : buddies){
            System.out.print(buddy.name + " ");
        }
        System.out.println();
        //2.打印城市推荐
        List<String> res = sol.recommendCities(10);
        System.out.println(res);


    }
}
