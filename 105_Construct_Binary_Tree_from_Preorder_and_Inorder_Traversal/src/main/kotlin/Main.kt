/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
Medium
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.



Example 1:
https://assets.leetcode.com/uploads/2021/02/19/tree.jpg
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]


Constraints:

1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.

 */
fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

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

//preorder: root, left, right
//inorder:left, root, right

/*
簡單的說，每次都先從preorder拿第一位出來，因為是preorder，所以第一位一定是root
拿到root後 以此為分割點去切割inorder，root前的為left child的inorder，root後的為right child的inorder
再回來看preorder，去掉一開始的root，往後取leftInorder.size位的sub-array為left child的preorder
從尾巴往回取rightPreorder.size位的sub-array為right child的preorder

然後遞回建出一棵樹

可以加速的點：
1. 反轉inorder，用hashmap加速做indexOf的速度
2. 不用每次都把sun array建出來，可以傳index就好
 */
interface Sol{
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode?
}

class Solution01:Sol {
    override fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return foo(preorder, inorder)
    }

    private fun foo(preorder: IntArray, inorder: IntArray):TreeNode?{
        if(preorder.isEmpty() && inorder.isEmpty())
            return null

        if(preorder.size==1 && inorder.size==1)
            return TreeNode(preorder[0])

        val root = preorder[0]
        val leftInorder = if(inorder.indexOf(root)==0) intArrayOf() else inorder.sliceArray(0..inorder.indexOf(root)-1)
        val rightInorder = if(inorder.indexOf(root) == inorder.lastIndex) intArrayOf() else inorder.sliceArray(inorder.indexOf(root)+1..inorder.lastIndex)

        val leftPreorder = if(leftInorder.isEmpty()) intArrayOf() else preorder.sliceArray(1..leftInorder.size)
        val rightPreorder = if(rightInorder.isEmpty()) intArrayOf() else preorder.sliceArray((preorder.lastIndex-rightInorder.size+1)..preorder.lastIndex)

        return TreeNode(root).apply {
            left = foo(leftPreorder, leftInorder)
            right = foo(rightPreorder, rightInorder)
        }
    }
}

class Solution02:Sol {
    override fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        return foo(preorder, inorder, 0, preorder.lastIndex, 0, inorder.lastIndex)
    }

    private fun foo(preorder: IntArray, inorder: IntArray, preLeft:Int, preRight:Int, inLeft:Int, inRight:Int):TreeNode?{
        if(preLeft>preRight || inLeft>inRight) // empty array
            return null

        if(preLeft==preRight && inLeft==inRight)
            return TreeNode(preorder[preLeft])

        val root = preorder[preLeft]
        val rootIndex = inorder.indexOf(root)

        //left child inorder
        var leftInorderLeft:Int
        var leftInorderRight:Int
        if(rootIndex==inLeft){
            leftInorderLeft = Int.MAX_VALUE
            leftInorderRight = Int.MIN_VALUE
        }else{
            leftInorderLeft = inLeft
            leftInorderRight = rootIndex-1
        }

        //right child inorder
        var rightInorderLeft:Int
        var rightInorderRight:Int
        if(rootIndex == inRight){
            rightInorderLeft = Int.MAX_VALUE
            rightInorderRight = Int.MIN_VALUE
        }else{
            rightInorderLeft = rootIndex+1
            rightInorderRight = inRight
        }

        //left child preorder
        var leftPreorderLeft:Int
        var leftPreorderRight:Int
        if(rootIndex==inLeft){
            leftPreorderLeft = Int.MAX_VALUE
            leftPreorderRight = Int.MIN_VALUE
        }else{
            leftPreorderLeft = preLeft+1
            leftPreorderRight = leftPreorderLeft + (leftInorderRight - leftInorderLeft + 1) -1
        }

        //right child preorder
        var rightPreorderLeft:Int
        var rightPreorderRight:Int
        if(rootIndex == inRight){
            rightPreorderLeft = Int.MAX_VALUE
            rightPreorderRight = Int.MIN_VALUE
        }else{
            rightPreorderLeft = preRight - (rightInorderRight - rightInorderLeft +1) +1
            rightPreorderRight = preRight
        }

        return TreeNode(root).apply {
            left = foo(preorder,inorder,leftPreorderLeft,leftPreorderRight,leftInorderLeft,leftInorderRight)
            right = foo(preorder,inorder,rightPreorderLeft,rightPreorderRight,rightInorderLeft,rightInorderRight)
        }
    }
}

//final, 用上hashmap index以及簡化條件
class Solution03:Sol {
    override fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inMap = HashMap<Int, Int>()
        for (i in inorder.indices) {
            inMap.put(inorder[i], i)
        }

        return foo(preorder, 0, preorder.lastIndex, 0, inorder.lastIndex, inMap)
    }

    private fun foo(preorder: IntArray, preLeft:Int, preRight:Int, inLeft:Int, inRight:Int, inMap: HashMap<Int, Int>):TreeNode?{
        if(preLeft>preRight || inLeft>inRight) // empty array
            return null

        val rootNode = TreeNode(preorder[preLeft])
        if(preLeft==preRight && inLeft==inRight)
            return rootNode

        val root = preorder[preLeft]
        val rootIndex = inMap.get(root) ?: -1

        //left child inorder
        val leftInorderLeft:Int = inLeft
        val leftInorderRight:Int = rootIndex-1

        //right child inorder
        var rightInorderLeft:Int = rootIndex+1
        var rightInorderRight:Int = inRight

        val leftInorderSize = (rootIndex-1) - inLeft +1
        val rightInorderSize = inRight - (rootIndex+1) +1

        //left child preorder
        var leftPreorderLeft:Int = preLeft+1
        var leftPreorderRight:Int = leftPreorderLeft + leftInorderSize -1

        //right child preorder
        var rightPreorderLeft:Int = preRight - rightInorderSize +1
        var rightPreorderRight:Int = preRight

        return TreeNode(root).apply {
            left = foo(preorder,leftPreorderLeft,leftPreorderRight,leftInorderLeft,leftInorderRight,inMap)
            right = foo(preorder,rightPreorderLeft,rightPreorderRight,rightInorderLeft,rightInorderRight,inMap)
        }
    }
}

class Solution04:Sol {
    var preIndex = 0
    override fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        val inMap = HashMap<Int, Int>()
        for (i in inorder.indices) {
            inMap.put(inorder[i], i)
        }

        return foo(preorder, 0, inorder.lastIndex, inMap)
    }

    private fun foo(preorder: IntArray,inLeft:Int, inRight:Int, inMap: HashMap<Int, Int>):TreeNode?{
        if(inLeft>inRight) // empty array
            return null

        if(inLeft==inRight)
            return TreeNode(preorder[preIndex++])

        val root = preorder[preIndex++]
        val rootIndex = inMap.get(root) ?: -1

        //left child inorder
        val leftInorderLeft:Int = inLeft
        val leftInorderRight:Int = rootIndex-1

        //right child inorder
        var rightInorderLeft:Int = rootIndex+1
        var rightInorderRight:Int = inRight

        return TreeNode(root).apply {
            left = foo(preorder,leftInorderLeft,leftInorderRight,inMap)
            right = foo(preorder,rightInorderLeft,rightInorderRight,inMap)
        }
    }
}

/*備註：
看到這個解法鰻屌的，既然都是用prefix第一位開始，prefix就是中左右
那遞迴的順序照著中間(本次會處理完) 左邊 右邊遞迴，那就可以prefix順著一位一位切下來，專注在inorder上就好
 */
class Solution {
    var index = -1
    fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
        index = 0
        val map = HashMap<Int, Int>()
        for (i in inorder.indices)
            map[inorder[i]] = i
        return helper(preorder, inorder, map, 0, inorder.size - 1)
    }

    fun helper(preorder: IntArray, inorder: IntArray, map: HashMap<Int, Int>, left: Int, right: Int): TreeNode? {
        if (left > right)
            return null
        val root = TreeNode(preorder[index++])
        val inorderIndex = map[root.`val`]!!
        root.left = helper(preorder, inorder, map, left, inorderIndex - 1)
        root.right = helper(preorder, inorder, map, inorderIndex + 1, right)
        return root
    }
}