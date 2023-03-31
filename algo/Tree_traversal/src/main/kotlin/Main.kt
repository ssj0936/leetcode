import java.util.ArrayDeque

//fun main(args: Array<String>) {
//    println("Hello World!")
//
//    // Try adding program arguments at Run/Debug configuration
//    println("Program arguments: ${args.joinToString()}")
//}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

interface Traversal{
    fun traversalRecursive(root:TreeNode?):IntArray
    fun traversalIterative(root:TreeNode?):IntArray
}

class Inorder:Traversal{
    private val buffer = mutableListOf<Int>()
    override fun traversalRecursive(root: TreeNode?): IntArray {
        recursive(root)
        return buffer.toIntArray()
    }

    private fun recursive(node: TreeNode?){
        if(node == null) return

        recursive(node.left)
        buffer.add(node.`val`)
        recursive(node.right)
    }

    override fun traversalIterative(root: TreeNode?): IntArray {
        if(root == null)
            return buffer.toIntArray()

        val stack = ArrayDeque<TreeNode>()
        var pointer = root

        while (pointer!=null || stack.isNotEmpty()){
            if(pointer != null){
                stack.push(pointer)
                pointer = pointer.left
            }else{
                val node = stack.pop()
                buffer.add(node.`val`)

                pointer = node.right
            }
        }
        return buffer.toIntArray()
    }
}

class Preorder:Traversal{
    private val buffer = mutableListOf<Int>()
    override fun traversalRecursive(root: TreeNode?): IntArray {
        recursive(root)
        return buffer.toIntArray()
    }

    private fun recursive(node: TreeNode?){
        if(node==null) return
        buffer.add(node.`val`)
        recursive(node.left)
        recursive(node.right)
    }

    override fun traversalIterative(root: TreeNode?): IntArray {
        if(root == null)
            return buffer.toIntArray()

        val stack = ArrayDeque<TreeNode>().apply {
            push(root)
        }

        while (stack.isNotEmpty()){
            val node = stack.pop()
            buffer.add(node.`val`)

            node.right?.let{
                stack.push(it)
            }
            node.left?.let{
                stack.push(it)
            }
        }
        return buffer.toIntArray()
    }
}

class Postorder:Traversal{
    private val buffer = mutableListOf<Int>()
    override fun traversalRecursive(root: TreeNode?): IntArray {
        recursive(root)
        return buffer.toIntArray()
    }

    private fun recursive(node: TreeNode?){
        if(node==null) return
        recursive(node.left)
        recursive(node.right)
        buffer.add(node.`val`)
    }

    override fun traversalIterative(root: TreeNode?): IntArray {
        if(root == null)
            return buffer.toIntArray()

        val stack = ArrayDeque<TreeNode>().apply {
            push(root)
        }

        while (stack.isNotEmpty()){
            val node = stack.peek()
            if(node.left == null && node.right == null)
                buffer.add(stack.pop().`val`)
            else {
                node.right?.let {
                    stack.push(it)
                    node.right = null
                }
                node.left?.let {
                    stack.push(it)
                    node.left = null
                }
            }
        }
        return buffer.toIntArray()
    }

    fun traversalIterativeBetter(root: TreeNode?): IntArray {
        if(root == null)
            return buffer.toIntArray()

        val stack = ArrayDeque<TreeNode>()
        var pointer = root
        var justPassed:TreeNode? = null

        while (pointer!=null || stack.isNotEmpty()){
            if(pointer!=null){
                stack.push(pointer)
                pointer = pointer.left
            }else{
                val node = stack.peek()

                if(node.right==null || justPassed == node.right){
                    buffer.add(node.`val`)
                    justPassed = stack.pop()
                }else{
                    pointer = node.right
                }
            }
        }
        return buffer.toIntArray()
    }
}