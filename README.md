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

### 题目2：PalindromePairs（回文对，leetcode336）
        * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，
          使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
               
        * 示例 1:
            输入: ["abcd","dcba","lls","s","sssll"]
            输出: [[0,1],[1,0],[3,2],[2,4]] 
            解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
        * 示例 2:
            输入: ["bat","tab","cat"]
            输出: [[0,1],[1,0]] 
            解释: 可拼接成的回文串为 ["battab","tabbat"]
            
            
##### 思路：
        * step1 : 使用map 存储每一个 单词 和 下标
        * step2 : 对于数组中每一个单词，分割为str1 str2 两部分。检查str1 str2是否是 回文串
                  如果 str1 是回文串，使用str1 作为中间部分，str2作为右部分，查看map中是否包含str2的逆串。
                  如果包含，则使用这个字符串作为左部分，与中、右组成回文串。
        * step3 : 对str2左同样操作（str2作为中间部分）
        
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/palindrome_paires/PalindromePairs.java)



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


### 题目4：Vector2DIterator(实现二维数组遍历器)
        * 给定一个二维数组，实现一个遍历类，允许遍历和删除数组中的元素
        * 需提供三个方法：
          boolean hasNext()： 如果还有下一个元素返回true,否则false
          int next() ：返回数组的下一个元素
          void remove()： 删除上一次next()操作时返回的元素。
##### 思路：         
       * 维护两个变量row和col，将row和col初始化为0;
       * 对于next函数，获得返回值后，执行后移操作：检查当前row是否小于总行数，col是否>=当前行的列数，
         如果都成立，说明要转到下一行，则row自增1，col初始化为0;
       * 对于 hasNext 函数，检查当前row是否<总行数，col是否<当前行的列数，
         如果都成立返回true,否则返回flase
       * 对于remove()函数，判断是否col==0，如果col == 0,则删除上一行最后一个元素，
         否则删本行上一个元素即可。如果删除元素后导致元素所在行空了，则删除所在行。
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/vector2d_iterator/Vector2DIterator.java)
