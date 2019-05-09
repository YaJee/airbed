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

### 题目2：PalindromePairs 回文对 (leetcode336）
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



### 题目3：RoundPrices（价格取整）（leetcode560 plus）
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


### 题目4：Vector2DIterator(实现二维数组遍历器)(leetcode251 plus)
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

### 题目5：DisplayPage(将搜索结果分页)
       * 实现分页显示。
       * 给了以下一些输入数据，要求将以下行分页显示，每页x行，其中每行已经按score排好序，
       * 分页显示的时候如果有相同host_id的行，则将后面同host id的行移到下一页。
       * 每行的数据代表："host_id,listing_id,score,city"
       * 示例 1:
        输入：
           [
               "1,28,310.6,SF",
               "4,5,204.1,SF",
               "20,7,203.2,Oakland",
               "6,8,202.2,SF",
               "6,10,199.1,SF",
               "1,16,190.4,SF",
               "6,29,185.2,SF",
               "7,20,180.1,SF",
               "6,21,162.1,SF",
               "2,18,161.2,SF",
               "2,30,149.1,SF",
               "3,76,146.2,SF",
               "2,14,141.1,San Jose"
           ]
         输出：（3页）
             1,28,310.6,SF
             4,5,204.1,SF
             20,7,203.2,Oakland
             6,8,202.2,SF
             7,20,180.1,SF
              
             6,10,199.1,SF
             1,16,190.4,SF
             2,18,161.2,SF
             3,76,146.2,SF
             6,29,185.2,SF
              
             6,21,162.1,SF
             2,30,149.1,SF
             2,14,141.1,San Jose
             
##### 思路： 
        * 定义set：记录 当前页 的重复host_id
        * 定义res：用于添加记录作为返回值
        * 定义reachEnd：标记 是否遍历到input最后一个元素
        过程：
        * 遍历input,优先将set中不存在的记录添加进 res;
        * 如果遍历到最后一个，重新设置遍历器，并将reachEnd = ture
        * 当reachEnd = ture，无论set中是否存在，都将记录加入res;
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/display_page/DisplayPage.java)

### 题目6：MenuCombinationSum菜单价格组合（消费K元的组合）(leetcode40 plus)
        * 给定一个菜单价格列表，找出所有的可能组合，使菜单价格的组合和等于一个给定的价格K.
        * 示例 1:
         输入：
            [10.02, 1.11, 2.22, 3.01, 4.02, 2.00, 5.03] , 7.03 
         输出：
            [[3.01, 4.02], 
             [2.0, 5.03]
            ]
##### 思路：    
     * step1 : 把菜单价格*100转成整数
     * step2 : 回溯法   
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/menu_combination_sum/MenuCombinationSum.java)
    
### 题目7：Sliding Puzzle（移动谜题）(leetcode773 plus)
         * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
         * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
         * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
         * 任务一：给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
           int canSlove(int[][] board)
         * 任务二：如果能解开谜题，则返回移动路径
           List<String> getPath(int[][] board)
           
         *示例1:
           输入：board = [[1,2,3],[5,4,0]]
           输出：-1
           解释：没有办法完成谜板
         *示例2:
           输入：board = [[4,1,2],[5,0,3]]
           输出1：5
           输出2：[left, up, right, right, down]
           
##### 思路：
       * 函数1：int canSlove(int[][] board)
         1.将二维数组 映射到 一维空间，即映射为字符串
         2.将 board 中的 每一个状态 都作为图的一个节点
         3.则只需要找到 start 到 target 最短距离 即可；target : "123450"
         4.采用BFS,每次在字符串中交换"0"和其他能到达的位置，并用hashset存储这个状态
       
       * 函数2： List<String> getPath(int[][] board)
         1.将二维数组 映射到 一维空间，即映射为字符串
         2.将 board 中的 每一个状态 都作为图的一个节点
         3.则只需要找到 start 到 target 最短距离对应的路径 即可；target : "123450"
         4.采用BFS,每次在字符串中交换"0"和其他能到达的位置，并用hashset存储这个状态，用Queue存储这个状态对应的路径
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/sliding_puzzle/SlidingPuzzle.java)

### 题目8：10 Wizards（10个向导）（单源最短路径问题）
       * 10个向导，0-9，现在给出每个向导能到达的向导列表。
       * 定义：一个向导到另一个向导到距离为 下标差的平方。
       * 求向导0到向导9的最短距离。   
       
       * 示例1:
         输入：wizards(二维数组)
              wizards[0] : [ 1, 4, 5]		（向导0，能到达 1，4，5）
              wizards[4] : [ 9 ]			（向导4，能到达 9）
         输出：[0, 4, 9]                      (0到9的最短路径)
         解释：(0-4)^2+(4-9)^2=41 (wizards[0]->wizards[4]->wizards[9])
##### 思路： 
       * BFS 
         或
       * 迪杰斯特拉       
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/ten_wizards/TenWizards.java)
