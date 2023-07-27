import java.util.LinkedList

/*
297. Serialize and Deserialize Binary Tree
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.


Example 1:
Input: root = [1,2,3,null,null,4,5]
Output: [1,2,3,null,null,4,5]

Example 2:
Input: root = []
Output: []

Constraints:

The number of nodes in the tree is in the range [0, 10^4].
-1000 <= Node.val <= 1000

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

/**
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

interface Solution{
    fun serialize(root: TreeNode?): String
    fun deserialize(data: String): TreeNode?
}

class Codec():Solution {
    // Encodes a URL to a shortened URL.
    override fun serialize(root: TreeNode?): String {
        if(root==null)
            return "[]"

        val result = mutableListOf<String>()
        val queue = LinkedList<TreeNode?>().apply {
            add(root)
        }

        while (queue.isNotEmpty()){
            val length = queue.size
            var isAtLeastOneChild = false
            repeat(length){
                val node = queue.poll()
                if(node==null){
                    result.add("null")
                    queue.add(null)
                    queue.add(null)
                }else{
                    result.add(node.`val`.toString())
                    isAtLeastOneChild = isAtLeastOneChild || node.left!=null
                    isAtLeastOneChild = isAtLeastOneChild || node.right!=null
                    queue.add(node.left)
                    queue.add(node.right)
                }
            }

            if (!isAtLeastOneChild)
                break
        }

        return result.joinToString(prefix = "[", postfix = "]", separator = ",")
    }

    // Decodes your encoded data to tree.
    override fun deserialize(data: String): TreeNode? {
        if(data=="[]")
            return null

        val newData:MutableList<Any> = ("_," + data.substring(0+1 .. data.lastIndex-1)).split(",").toMutableList()
        newData[0] = TreeNode(Int.MAX_VALUE)
        for(i in 1..newData.lastIndex){
            val value = newData[i].toString().toIntOrNull()
            val parentIndex = i/2
            if(value != null) {
                var node = TreeNode(value)
                newData[i] = node
                if(i%2==0)
                    (newData[parentIndex] as TreeNode).left = node
                else
                    (newData[parentIndex] as TreeNode).right = node
            }
        }

        return newData[1] as TreeNode
    }
}

class CodecNeo:Solution{
    override fun serialize(root: TreeNode?): String {
        if(root==null)
            return "[]"

        val result = mutableListOf<String>()
        val queue = LinkedList<TreeNode?>().apply {
            add(root)
        }

        while (queue.isNotEmpty()) {
            val length = queue.size
            var isAtLeastOneChild = false

            repeat(length){
                val node = queue.poll()
                if(node==null){
                    result.add("null")
                }else{
                    result.add(node.`val`.toString())
                    isAtLeastOneChild = isAtLeastOneChild  || node.left!=null  || node.right!=null
                    queue.add(node.left)
                    queue.add(node.right)
                }
            }
            if (!isAtLeastOneChild)
                break
        }

        return result.joinToString(prefix = "[", postfix = "]", separator = ",")
    }

    override fun deserialize(data: String): TreeNode? {
        if(data=="[]")
            return null
        val newData:MutableList<Any> = ("_," + data.substring(0+1 .. data.lastIndex-1)).split(",").toMutableList()

//        println(newData)
        newData[0] = TreeNode(0)

        var parentIndex = 0
//        var nullCount = 0
//        var levelElementCount = 1
//        var levelElementCountTmp = levelElementCount

        for(i in 1..newData.lastIndex){
//            --levelElementCount

            val value = newData[i].toString().toIntOrNull()
            if(value != null) {
                var node = TreeNode(value)
                newData[i] = node
                if(i%2==0)
                    (newData[parentIndex] as TreeNode).left = node
                else {
                    (newData[parentIndex] as TreeNode).right = node
                }
            }/*else{
                ++nullCount
            }*/
            //指到parent的右側，代表parent已經完整了，parent index往後
            if(i%2==1) {
                do{++parentIndex}
                while (newData[parentIndex].toString() == "null")
            }
            /*
            if(levelElementCount == 0){
//                parentIndex = i - levelElementCountTmp + 1

                levelElementCount = (levelElementCountTmp - nullCount)*2
                levelElementCountTmp = levelElementCount
                nullCount = 0
            }*/
        }
        return newData[1] as TreeNode
    }

}