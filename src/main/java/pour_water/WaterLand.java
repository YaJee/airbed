package main.java.pour_water;

public class WaterLand {

    /**
     *
     * @param heights   陆地高度数组
     * @param location  倒水位置
     * @param water     水量
     */
    public void pourWater(int [] heights, int location, int water){

        //waters[i] : 在i位置到到水量
        int[] waters = new int[heights.length];
        int pourLocation;

        while (water > 0){
            int left = location -1;
            //1.找到最左边 能倒水的位置
            while (left >= 0){
                //如果 左边 比右边高，则找到
                if( heights[left] + waters[left]  > heights[left + 1] + waters[left + 1]){
                    break;
                }
                left --;
            }
            //是否符合要求
            if(heights[left + 1] + waters[left + 1] < heights[location] + waters[location]){
                pourLocation = left + 1;
                waters[pourLocation] ++;
                water--;
                continue;
            }
            //如果左边 倒满了
            int right = location + 1;
            while(right < heights.length){
                if(heights[right] + waters[right] > heights[right-1] + waters[right - 1]){
                    break;
                }
                right++;
            }
            if(heights[right-1] + waters[right-1] < heights[location] + waters[location]){
                pourLocation = right - 1;
                waters[pourLocation] ++;
                water--;
                continue;
            }
            //如果 右边也倒满了,在 原始位置 倒一单位的水

            pourLocation = location;
            waters[pourLocation]++;
            water--;

        }
        print(heights,waters);

    }
    public void print(int[] heights, int[] waters){
        int n = heights.length;
        int maxHeight = 0;
        for(int i = 0; i < n; i++){
            maxHeight = Math.max(maxHeight, heights[i] + waters[i]);
        }
        //从上往下打印
        for(int height = maxHeight; height >= 0; height--){
            for(int i = 0; i < n; i++){
                if( height <= heights[i]){                                      //打印陆地
                    System.out.print("+");
                }else if( height > heights[i] && height <= heights[i] + waters[i]){//打印水
                    System.out.print("W");
                }else {                                                             //打印空
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        WaterLand sol = new WaterLand();
        int[] waterLand = new int[]{5, 4, 2, 1, 2, 3, 2, 1, 0, 1, 2, 4};
        sol.pourWater(waterLand, 5, 1);
        sol.pourWater(waterLand, 5, 5);
        sol.pourWater(waterLand, 5, 10);
        sol.pourWater(waterLand, 5, 20);
        sol.pourWater(waterLand, 5, 30);
        sol.pourWater(waterLand, 5, 50);
        sol.pourWater(waterLand, 5, 100);
    }
}
