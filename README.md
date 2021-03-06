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

### 题目9：meeting time（员工开会）
       * 每个员工可能参加多场会议，现给定每个员工要参加的会议的开始、结束时间，输出所有员工都空闲的时间间隔。
       * 示例1:
        输入：[
                [ [1, 3], [6, 7]],  //员工1 要参加都会议时间
                [ [2, 4]],          //员工2
                 [ [2, 3], [9, 12]]  //员工2
             ],
             3                      //3位员工
        输出:[[4, 6], [7, 9]]       //所有员工都空闲都时间
##### 思路：
        *  1.将所有interval(会议) 提取到list中
        *  2.对list排序 : 按 会议开始时间 升序
        *  3.对list 合并区间,将有时间重叠的会议合并起来
        *  4.找出空隙
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/meeting_time/MeetingTime.java)

### 题目10：RegularExpression（匹配正则表达式）（leetcode10 plus）
        * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 和'+'的正则表达式匹配。
            '.' 匹配任意单个字符。
            '*' 匹配零个或多个前面的元素。
            '+' 匹配一个或多个前面的元素。
        
        * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
        * 说明:
            s 可能为空，且只包含从 a-z 的小写字母。
            p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 * 和 +。
          
        * 示例 1:
          输入:
            s = "aa"
            p = "a"
          输出: false
          解释: "a" 无法匹配 "aa" 整个字符串。 
        * 示例 2:
          输入:
            s = "aa"
            p = "a*"
          输出: true
          解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
        * 示例 3:
          输入:
            s = "ab"
            p = ".*"
          输出: true
          解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
        * 示例 4:
          输入:
            s = "aab"
            p = "c*a*b"
          输出: true
          解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
        * 示例 5:
          输入:
            s = "aab"
            p = "c+a*b"
          输出: false
##### 思路：动态规划
        * 定义dp[i][j] : s的前i个和 p的前j个 是否完全匹配。
        * 1, If p.charAt(j) == s.charAt(i) 		:  dp[i][j] = dp[i-1][j-1];
        * 2, If p.charAt(j) == '.' 	 	 	:  dp[i][j] = dp[i-1][j-1];
        * 3, If p.charAt(j) == '*' || If p.charAt(j) == '+': 			//这种情况，还有可能使dp[i][j] = true
               细分为两种情况：
               1,if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                    ■ if p.charAt(j) == '*':
                           dp[i][j] = dp[i-1][j]    //例子:如果p为 ab*, 则视为abb* 	//重复多次
                        or dp[i][j] = dp[i][j-1]     //例子:如果p为 ab*, 则视为ab		//重复1次
                        or dp[i][j] = dp[i][j-2]     //例子:如果p为 ab*, 则视为a		//重复0次
                    ■ if p.charAt(j) == '+':
                           dp[i][j] = dp[i-1][j]    //例子:如果p为 ab*, 则视为abb+   //重复多次
 	                    or dp[i][j] = dp[i][j-1]     //例子:如果p为 ab+, 则视为ab	    //重复1次
               2,if p.charAt(j-1) != s.charAt(i) : 
                    ■ if p.charAt(j) == '*':
                           dp[i][j] = dp[i][j-2]     //例子:如果p为 ab*, 则视为a	    //重复0次
                    ■ if p.charAt(j) == '+':
                           dp[i][j] = false       				                //这种情况 dp[i][j] = false          
        * 4. p.charAt(j) ！= '*' && p.charAt(j) ！= '+'    : dp[i][j] = false;	//这种情况 dp[i][j] = false
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/regular_expression/RegularExpression.java)
   
### 题目11：alien dictionary(另类字典）leetcode 269
        * 有一种新的语言由拉丁字母组成，不过字母顺序我们不知道啊，现给定一个按照外星字典序排列的单词列表，请推导出外星字典序   
        * 示例 1:
            输入:
                [
                  "wrt",
                  "wrf",
                  "er",
                  "ett",
                  "rftt"
                ]
            输出: "wertf"
        * 示例 2:
            输入:
                [
                  "z",
                  "x"
                ]
            输出: "zx"     
        * 示例 3:
            输入:
                [
                  "z",
                  "x",
                  "z"
                ]  
            输出: "" 
            解释: 顺序无效, return "".
##### 思路：构建有向图 + 拓扑排序
        1.建立图
          * 比如题目中给的例子1，我们可以推出的顺序关系有：
            t->f
            w->e
            r->t
            e->r
          * 这些就是有向图的边，对于有向图中的每个结点，计算其入度.
        2.拓扑排序
          * 对这个图拓扑排序，然后将遍历路径保存下来返回即可
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/alien_dictionary/AlienDictionary.java)
  
### 题目12：Preference List(爱好列表)（leetcode269 plus）
        * 每个人都有一个preference的排序，在不违反每个人的preference顺序的情况下得到总体的preference的排序 
        * 示例 1 
            输入:
                 [
                    [3, 5, 7, 9], 
                    [2, 3, 8], 
                    [5, 8]
                ] 
            输出：
                [2, 3, 5, 8, 7, 9].	//这是其中一个可能，[2, 3, 5, 7, 8, 9]，[2, 3, 5, 8, 9,7]同样可以
        * 与11题的区别：
            11题中input[i]和input[i+1]有顺序要求，本题中没有 
##### 思路：构建有向图 + 拓扑排序       
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/preference_list/PreferenceList.java)
  
### 题目13：Travel Buddy 旅行同伴）
        * 你有一个旅行清单，你的朋友们也有他们各自的旅行清单，
        * 问题1:如果一个朋友的旅行清单中，超过50%与你重复，则他成为你的旅行伙伴
        * 问题2:推荐旅行城市，输入k,在你的旅行伙伴的清单中推荐k个城市给你，优先推荐相似率最高的伙伴的城市。
        * 示例 1 
            输入:
                [
                    {"a", "b", "c", "d"};			//your list 
                    {"a", "b", "e", "f"};			//f1 list 相似度 2/4
                    {"a", "c", "d", "g"};			//f2 list 相似度 3/4
                    {"c", "f", "e", "g"};			//f3 list 相似度 1/4
                ],
                10							        //推荐10个城市
            输出：
                [f2, f1]						//伙伴列表
                [g, e, f]						//推荐的城市列表，只能推荐3个
##### 思路：  
        * 旅行伙伴：按相似度排序即可
        * 推荐城市：从伙伴城市列表取取 mylist中不存在并且reslist中也不存在的即可
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/travel_buddy/TravelBuddy.java)
  
### 题目14：Flight list(最多中转k次到达目的地的最小代价)）
        * 每一个航班包括departure（出发地）, arrival（目的地）, cost（成本），然后给一个整数k, 表示最多允许k次中转。给定起始地点A，到达地点B, 要求输出从A到B的最小花费
        *示例 1 
            输入:
             [
                "A->B,100",
                "A->C,400",
                "B->C,100",
                "C->D,100",
                "C->A,10"
             ],
             [A,D],	         //起点A，终点D
             1	         //允许中转一次
            输出：
                500
            解释：A->C->D : 成本为500
##### 思路： BFS
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/flight_list/FlightList.java)
  
### 题目15：find median from large file of integers(在大整数文件中找到中位数)
        * 在大整数文件中找到中位数,文件的大小大于计算机内存。
##### 思路： 
        思路：
        
        * 因为是整数文件，所以就确定了 数值的边界，min: Integer.MIN_VALUE; max: Integer.MAX_VALUE
         
        * 二分法：
            1.设定最小值为 min, 最大值为 max，num = (min + (max-min)/2)
            2.遍历文件，统计文件的数量 totaal
            3. 统计 文件中 小于 num的 数量count ，
                    如果count < total/2,则更新 num = (num + (max-num)/2),重复第3
                    如果count > total/2,则更新 num = (min + (num-min)/2),重复第3
                    如果count == total/2,返回符合要求的数字i,结束
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/find_midian_integer/FindMedianInteger.java)

### 题目16：Text Justification（文本左右对齐）leetcode68
        * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
          你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
          要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
          文本的最后一行应为左对齐，且单词之间不插入额外的空格。
        * 说明:
          ● 单词是指由非空格字符组成的字符序列。
          ● 每个单词的长度大于 0，小于等于 maxWidth。
          ● 输入单词数组 words 至少包含一个单词。   
        * 示例:
          输入:
            words = ["This", "is", "an", "example", "of", "text", "justification."]
            maxWidth = 16
          输出:
            [
             "This    is    an",
             "example  of text",
             "justification.  "
           ]  
##### 思路：
        * 从最左边的单词开始：left
          findRight: 找到符合要求的最右边的单词:right
          justify：将[left,right]调整为一行
          justify: 所有情况下，我们将每行末尾填充空字符，直到满足maxWidth
              1. 如果只有一个单词，那这个单词构成本行。
              2. 如果是最后一行，分隔符是" "。
              3. 否则，计算单词间的间隔（取整），如果有多余空格（取余），顺序添加到间隔中（每次一个），直到用完。
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/text_justification/TextJustification.java)
      
### 题目17：Implement  queue with limited size of array（实现队列）
        * 使用固定大小的数组，实现队列QueueWithFixedArray,通过构造函数传入array的大小
          ● offer(int num)
          ● poll()
          ● size()
##### 思路：
  ![imaage text](https://github.com/YaJee/airbed/blob/master/img/ImplQueueWithFixedArray.png)
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/implement_queue_with_fixedArray/ImplementQueueWithFixedArray.java)
 
### 题目18：file system （实现文件系统类）  
        * 实现文件系统类,包含create()和get()方法
          ● create("/a",1)
          ● get("/a") //get 1
          ● create("/a/b",2)
          ● get("/a/b") //get 2
          ● create("/c/d",1) //Error, because "/c" is not existed
          ● get("/c") //Error, because "/c" is not existed  
##### 思路：
        * 使用Map<String, Integer>来保存路径和值         
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/file_system/FileSystem.java)
 
### 题目19：IP Range to CIDR（将ip范围，转换为掩码）
        * 给出一个ipv4的range，找出最少的cidr(掩码)可以覆盖这个range内的所有ip。
        *示例:
            输入:
                "255.0.0.7",10			//"255.0.0.7" -> "255.0.0.16"
            输出:
                [
                    "255.0.0.7/32",
                    "255.0.0.8/29",
                    "255.0.0.16/32"
                ]
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/ip_range_to_cidr/IPRangeToCIDR.java)
 
### 题目20：Find Case Combinations of a String(找到字符串的所有组合)
        * 找到字符串的所有字符组合，如："ab"的字符组合按顺序为  "ab", "Ab", "aB", "AB".即（00,10,01,11）.
        * 因此，你应该输出2^n (n：字符串的字符个数) 个字符串. 
##### 思路：
        * 例子：如求“hello" 的第11个组合
            1.求出10的二进制为01011，倒过来是11010，为1的位上是大写
            2.所以第11个组合是HElLo
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/find_combination_of_a_string/FindCaseCombinationsofaString.java)
 
### 题目21：csv prase (解析字符串)
        * 给定字符串，将其正确解析,有些双引号是有意义的不能删除，
        * 字段的含义依次是：姓名，性别，职业，旅游城市，邮编，家乡，年龄
        * 示例 1 ：
                "Jack ""Ma"" Yun",Male,Teacher,"NerYork,ShangHai",000010, Beijing, 23;
        解析为；[ "Jack "Ma" Yun", "Male", "Teacher", "NerYork,ShangHai", "000010", "Beijing", "23"]
        * 示例 2        
                Jack  Yun,Male,Teacher,"NerYork,ShangHai",000010, Beijing, 23";
        解析为；[ "Jack Yun", "Male", "Teacher", "NerYork,ShangHai", "000010", "Beijing", "23"]
        * 示例 3  
                Jack Yun,Male,Teacher,ShangHai,000010, Beijing, 23;
        解析为；[ "Jack Yun", "Male", "Teacher", "ShangHai", "000010", "Beijing", "23"]
##### 思路：有限状态机
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/csv_parser/CSVParser.java)
  
### 题目22：Pour Water（倒水）
        * 向凹凸不平的陆地上倒水
        * 三个输入：
            heights：数组， heights[i]代表i位置陆地的高度；陆地最左端以外，最右端以外无限高
            water: 将要倾倒的水量
            location:和倾倒水的位置
        * 输出：打印出倒完水后的陆地图案      
        * 示例 1 ：
            输入陆地高度 heights int[]{5,4,2,1,3,2,2,1,0,1,4,3} 陆地图案为:
                +
                ++        +
                ++  +     ++
                +++ +++   ++
                ++++++++ +++
                ++++++++++++
                012345678901（下标）
            在下标5的位置到8个水，之后陆地变为:
                +
                ++        +
                ++www+    ++
                +++w+++www++
                ++++++++w+++
                ++++++++++++
                012345678901（下标）

##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/pour_water/WaterLand.java)

### 题目23：Number of Intersected Rectangles(有效矩形的个数)
        * 给出多个矩形，如果矩形A没有被其他矩形包含，那么矩形A为有效矩形，输出有效矩形的个数。
        * 如果三个矩形：A包含B,B包含C，则有效矩形数量为1个
        * 如果三个矩形：A包含B,C不被任何矩形包含，则有效矩形数量为2个
##### 思路：并查集
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/num_of_intersected_rectangles/NumberofIntersectedRectangles.java)

### 题目24：Finding Ocean(标记海洋)
        * 给定：String二维数组 ："L"代表陆地，"W"代表海洋
               坐标           ：某一海洋中的一个点
        * 实现：将给定坐标所在海洋全部标记为"O"
        
        * 示例 
            输入：
                海洋：
                    {
                     "WWWLLLW",
                     "WWLLLWW",
                     "WLLLLWW"
                    }
                坐标：[0,1]
            输出(打印)：
                    OOOLLLW
                    OOLLLWW
                    OLLLLWW
##### 思路：BFS
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/find_ocean/FindingOcean.java)

### 题目25：Minimum Vertices to Traverse Directed Graph
        * 给定一个有向图的边集，输出最少数量的点集合，可以从这写点出发遍历整个图。
##### 思路：DFS
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/min_vertices_to_traverse_directed_graph/MinimumVerticestoTraverseDirectedGraph.java)
       
### 题目26：Boggle Game
        * 给定一个字母矩阵（二维数组），和一个单词字典，在字母矩阵中找到最多的互不交叉的单词。           
        * 示例 1 ：
          输入：
              字母矩阵：
                 {
                    {'o', 'a', 't', 'h'},
                    {'e', 't', 'a', 'e'},
                    {'i', 'h', 'k', 'r'},
                    {'r', 'a', 'i', 'n'}
            
                 };
              单词字典：{"oath", "pea", "eat", "rain"}  
          输出：
              {"oath", "eat", "rain"}        
##### 思路：
        * 1.根据单词，建立字典树
        * 2.对字母矩阵BFS,查找所有构成一个单词的路径，添加进paths
        * 3.回溯法：在paths中找互不交叉的单词   
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/boggle_game/BoggleGame.java)
          
### 题目27：K Edit Distance (不超过k的编辑距离) (leetcode72 plus )
        * 两个单词的编辑距离： word1 转换成 word2 所使用的最少操作数 
        * 可使用的操作
          ● 插入一个字符
          ● 删除一个字符
          ● 替换一个字符
        * 现给出一个单词数组，和一个目标单词target，求数组中有哪些单词根target的编辑距离不超过k             
        * 示例 1 ：
            输入：
                words : {"abcd", "abc", "abd", "ad", "c", "cc","b"}; 
                target:"abcd"
                k     :2
            输出：
                [abc, abcd, abd, ad]
##### 思路：
        * 动态规划
        * Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.
            f(i, j) = f(i - 1, j - 1)
         Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper   
            f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }
             * f(i, j - 1) represents insert operation
             * f(i - 1, j) represents delete operation
             * f(i - 1, j - 1) represents replace operation
##### 代码：[java代码](https://github.com/YaJee/airbed/blob/master/src/main/java/k_edit_distance/KEditDistanceDP.java)
          