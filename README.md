# airbed
airbed coding

 题目1：Collatz conjecture（考拉兹猜想）
        ● 如果一个数是奇数，接下来将它转换为3*n+1
        ● 如果一个数是偶数，接下来将它转换为 n/2
        ● 这个数最终转换为1
        ● 步数：就是讲这个数转换为1的次数
 问题：给定一个整数n，求区间[1，n]中的n个数 分别转换为1所需步数 的最大值。
 思路：分为奇数、偶数两种情况---递归计算
 [代码跳转：](https://github.com/YaJee/airbed/blob/master/src/main/java/collatz_conjecture/CollatzConjecture.java)
