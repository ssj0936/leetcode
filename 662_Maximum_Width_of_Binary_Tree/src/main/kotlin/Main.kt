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

interface Sol{
    fun widthOfBinaryTree(root: TreeNode): Int
}

class Solution:Sol {
    override fun widthOfBinaryTree(root: TreeNode): Int {
        var maxWidth = 1

        val queue = ArrayDeque<TreeNode?>().apply {
            offer(root)
        }
        while (true){
            var isAllChildNull = true
            var sizeInThisLevel = queue.size

            //把下一層的塞進queue中
            repeat(sizeInThisLevel){
                val node = queue.poll()
                queue.offer(node?.left)
                queue.offer(node?.right)
                isAllChildNull = isAllChildNull && node?.left==null && node?.right==null
            }
            if(isAllChildNull)
                break

            while (queue.stackPeek()==null)
                queue.pop()
            while (queue.queuePeek()==null)
                queue.poll()

            maxWidth = maxOf(maxWidth, queue.size)
        }

        return maxWidth
    }

    private fun <T> ArrayDeque<T>.offer(el:T) = this.addLast(el)
    private fun <T> ArrayDeque<T>.poll():T = this.removeFirst()
    private fun <T> ArrayDeque<T>.pop():T = this.removeLast()
    private fun <T> ArrayDeque<T>.stackPeek():T = this.last()
    private fun <T> ArrayDeque<T>.queuePeek():T = this.first()
}

class SolutionLeetcode:Sol {

    override fun widthOfBinaryTree(root: TreeNode): Int {
        var maxWidth = Int.MIN_VALUE

        val queue = ArrayDeque<Pair<TreeNode, Int>>().apply {
            addLast(root to 0)
        }
        while (queue.isNotEmpty()){
            //因為最後queue中會因為都是leaf沒有child了，會是空的，放到後面去結算會多一道判斷queue是否為空
            val size = queue.last().getIndex() - queue.first().getIndex() + 1
            maxWidth = maxOf(maxWidth, size)


            var sizeInThisLevel = queue.size
            //把下一層塞進queue中
            repeat(sizeInThisLevel){
                val poll = queue.removeFirst()
                val node = poll.getNode()
                val index = poll.getIndex()

                //跟之前的做法不一樣，如果是child是null就不放進去了，之前放進去是因為需要用null的個數來算數，現在把index一起帶進去就不需要了
                node.left?.let {
                    queue.addLast(it to index*2+1)
                }

                node.right?.let{
                    queue.addLast(it to index*2+2)
                }
            }
        }

        return maxWidth
    }

    private fun Pair<TreeNode, Int>.getNode() = first
    private fun Pair<TreeNode, Int>.getIndex() = second
}