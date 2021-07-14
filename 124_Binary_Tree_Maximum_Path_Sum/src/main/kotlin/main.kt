/*
124. Binary Tree Maximum Path Sum(Hard)
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
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

/*
類似DP的手法，dp[i]代表以該點為盡頭的max path value
從leaf loop through每一個節點 就能算出每一個節點為盡頭的max path value

但答案不一定會是以root為節點，也有可能答案的root是轉折，從左接到右這樣
所以需要一個紀錄器，記錄在算以每一個節點為盡頭的max path value的同時
也同時去檢查以左右子為盡頭的值，若都為正數，那答案可能就不一定是max(left,right)+root，則有可能會是left + right + root
這個不需要一層一層帶下去，所以用額外的記錄器來紀錄即可

 */
class Solution {

    //紀錄器
    //-1000 <= Node.val
    //所以初始用Int.MIN_VALUE
    var maxValue = Int.MIN_VALUE

    fun maxPathSum(root: TreeNode?): Int {
        if(root!=null){
            calMaxPathValue(root)
        }
        return maxValue
    }

    private fun calMaxPathValue(root: TreeNode):Int{
        var tmpMaxValue: Int

        //左右皆null 回傳自己 順便紀錄
        if(root.left==null && root.right==null){
            tmpMaxValue = root.`val`

            if(tmpMaxValue>maxValue)
                maxValue = tmpMaxValue
        }else{
            //子點為null就回傳0 否則就取以該點為盡頭的MaxPathValue
            val leftMaxPathValue = if(root.left==null) 0 else calMaxPathValue(root.left!!)
            val rightMaxPathValue = if(root.right==null) 0 else calMaxPathValue(root.right!!)

            //這是會回傳的value
            //子點皆負數，那就回傳自己就好
            //否則就取最大值加上自己
            tmpMaxValue =
                root.`val`+
                    if(leftMaxPathValue<0 && rightMaxPathValue<0) // 自己就好
                        0
                    else // 取最大值
                        Math.max(leftMaxPathValue,rightMaxPathValue)

            //先前提到path不一定是以root為盡頭
            //如果兩子點都是正數，那就挑戰一下以root作為 左右兩邊子點為盡頭的path的接點
            //所以就是 自己 加上 左右max path
            if((leftMaxPathValue>0 && rightMaxPathValue>0 && (root.`val`+leftMaxPathValue+rightMaxPathValue)>maxValue))
                maxValue = root.`val`+leftMaxPathValue+rightMaxPathValue

            //否則的話 就用先前計算的以root為盡頭的max path value去挑戰就好
            else if(tmpMaxValue>maxValue)
                maxValue = tmpMaxValue
        }
        return tmpMaxValue
    }
}
fun main(args: Array<String>) {
    println("Hello World!")
}