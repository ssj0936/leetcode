/*
96. Unique Binary Search Trees(Medium)
Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

Example 1:
Input: n = 3
Output: 5

Example 2:
Input: n = 1
Output: 1

Constraints:

1 <= n <= 19
 */


/*
想了一下子，最好畫個圖 腦袋會比較清楚
這題用DP解，dp[i] 代表有i個 升冪排列node的bst數量

經過畫圖之後，會比較清楚。
n=0 就是1
n=1 就是1
n=2 開始可以用推導的
樹需要有一個root，假設是1，抓出來 那左子樹數量 會是0個點排列而成的子樹數量(i)，右子樹數量 會是0個點排列而成的子樹數量(j)
可能的樹的樣子就是i*j

這樣可能比較抽象，換到n=3好了
root 為 1時，左子樹應該要有0個升冪排列node，右子樹應該要有2個升冪排列node。
root 為 2時，左子樹應該要有1個升冪排列node，右子樹應該要有1個升冪排列node
root 為 3時，左子樹應該要有2個升冪排列node，右子樹應該要有0個升冪排列node
其中0-2個升冪排列node的樹的數量 都是之前算過的 就可以拿來算

這樣就會清楚許多，每一回合就把其中一個點抓出來當root，然後算有幾種樹，加總起來就是n的樹的數量
 */

class Solution {
    fun numTrees(n: Int): Int {
        if(n==1) return 1

        val dp = IntArray(n+1)
        //0個點 算1個
        //1個點 算1個
        dp[0] = 1
        dp[1] = 1
        for(i in 2 .. n){
//            var sum = 0

            //從範圍內 抽一個node出來當root
            //然後左邊樹數*右邊樹數 然後全部加總
            for(j in 1 .. i){
                dp[i] += dp[j-1] * dp[i-j]
            }
//            dp[i] = sum
        }
        return dp[n]
    }
}
fun main(args: Array<String>) {
    val result = Solution().numTrees(3)
    println(result)
}