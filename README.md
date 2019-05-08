# airbed
airbed coding

### 题目1：Collatz conjecture（考拉兹猜想）
        * 如果一个数是奇数，接下来将它转换为3*n+1
        * 如果一个数是偶数，接下来将它转换为 n/2
        * 这个数最终转换为1
        * 步数：就是将这个数转换为1的次数
##### 问题：给定一个整数n，求区间[1，n]中的n个数 分别转换为1所需步数 的最大值。
##### 思路：分为奇数、偶数两种情况---递归计算
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/collatz_conjecture/CollatzConjecture.java)



### 题目3：RoundPrices（价格取整）
        * 在美团上订一家酒店的费用包括（基础价格 + 服务费 + 清洁费...等等）
        * 给定费用数组X（浮点数数组）例如：30.9，2.4，3.9
        * 现在允许你对每个价格进行向上取整（ceil）或者向下取整（floor）操作
        * 要求输出对每一个费用处理后的数组Y：31，2，4
      
        * 要求输出应满足以下条件：
        * sum(Y) = round(sum(x)) //round是将x求和之后的四舍五入值
        * minmize (|y1-x1| + |y2-x2| + ... + |yn-xn|)	//使每个价格变化值的和最小

##### 思路：
        * 这些价格都要调整（floor向下取整或者ceil向上取整）
        * 而如果对所有价格都进行floor的话，那么sum(Y)可能会低于round值，所以要取一些价格的ceil值
        * 所以我们要考虑的就是对哪些价格进行ceil处理即可
        * 我们将原价和其ceil值差值最小的价格进行ceil操作
      
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/round_prices/RoundPrices.java)

