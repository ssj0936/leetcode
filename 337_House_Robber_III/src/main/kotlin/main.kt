/*
337. House Robber III(Medium)
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses
in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

Example 1:
Input: root = [3,2,3,null,3,null,1]
Output: 7
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:
Input: root = [3,4,5,1,3,null,1]
Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.

Constraints:
The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 10^4
 */

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
class Solution {


    /*
    很幸運的一次就對了

    基本的想法是用類似DP的方式，以recursive的方式從葉子往上做紀錄
    從整棵樹來看，這棵樹的maxRobbedAmount 可以分成兩種，1)結果包含root的 2)結果不包含root的
    1相對好處理，就是把root的值，加上左右子樹不含root值的maxRobbedAmount
    2的話，第一時間可能會想錯，想成左右子樹含root值的maxRobbedAmount相加。但其實子樹root其實沒有硬性規定說一定要包含，
    說不定不含子樹root的max值會更大，但依然可以被放進不含樹根的結果裡面。所以這邊需要做含子root和不含子root的考量，就代表遞迴需要把兩種可能都回傳
    這邊用Pair來做處理

    再來就是決定遞迴的終止條件：如果左右子是null的話就當作是0來做計算

    接著就是把遞迴串起來
     */
    fun rob(root: TreeNode?): Int {
        if(root==null) return 0
        val result = getMaxValuePair(root)
        return Math.max(result.first,result.second)
    }

    //first含root的最大值
    //second不含root的最大值
    private fun getMaxValuePair(root: TreeNode):Pair<Int,Int>{
        val (leftMaxWithRoot, leftMaxWithoutRoot) = root.left?.let { getMaxValuePair(it) } ?: Pair(0,0)
        val (rightMaxWithRoot, rightMaxWithoutRoot) = root.right?.let { getMaxValuePair(it) } ?: Pair(0,0)
        val rootValue = root.`val`

        val maxWithRoot = rootValue + leftMaxWithoutRoot + rightMaxWithoutRoot
        val maxWithoutRoot = Math.max(leftMaxWithRoot,leftMaxWithoutRoot) + Math.max(rightMaxWithRoot,rightMaxWithoutRoot)
        return Pair(maxWithRoot, maxWithoutRoot)
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}