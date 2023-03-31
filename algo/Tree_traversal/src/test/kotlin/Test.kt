import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

class Test {
    lateinit var inorder:Traversal
    lateinit var preorder:Traversal
    lateinit var postorder:Traversal
    private val tree1 = TreeNode(4).apply {
        left = TreeNode(2).apply {
            left = TreeNode(1)
            right = TreeNode(3)
        }

        right = TreeNode(6).apply {
            left = TreeNode(5)
            right = TreeNode(7)
        }
    }

    private val inorderTree1 = intArrayOf(1,2,3,4,5,6,7)
    private val preorderTree1 = intArrayOf(4,2,1,3,6,5,7)
    private val postorderTree1 = intArrayOf(1,3,2,5,7,6,4)

    @BeforeTest
    fun setup(){
        inorder = Inorder()
        preorder = Preorder()
        postorder = Postorder()
    }

    @Test
    fun testInorderRecursive(){
        assertContentEquals(inorderTree1, inorder.traversalRecursive(tree1))
    }

    @Test
    fun testInorderIterative(){
        assertContentEquals(inorderTree1, inorder.traversalIterative(tree1))
    }

    @Test
    fun testPreorderRecursive(){
        assertContentEquals(preorderTree1, preorder.traversalRecursive(tree1))
    }

    @Test
    fun testPreorderIterative(){
        assertContentEquals(preorderTree1, preorder.traversalIterative(tree1))
    }

    @Test
    fun testPostorderRecursive(){
        assertContentEquals(postorderTree1, postorder.traversalRecursive(tree1))
    }

    @Test
    fun testPostorderIterative(){
        assertContentEquals(postorderTree1, postorder.traversalIterative(tree1))
    }

    @Test
    fun testPostorderIterativeBetter(){
        assertContentEquals(postorderTree1, (postorder as Postorder).traversalIterativeBetter(tree1))
    }

}