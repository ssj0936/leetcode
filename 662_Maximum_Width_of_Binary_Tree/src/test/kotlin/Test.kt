import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = SolutionLeetcode()

    @Test
    fun test01(){
        val root = TreeNode(1).apply{
            left = TreeNode(3).apply {
                left = TreeNode(5)
                right = TreeNode(3)
            }
            right = TreeNode(2).apply {
                right = TreeNode(9)
            }
        }

        val expectation = 4

        assertEquals(expectation, solution.widthOfBinaryTree(root))
    }

    @Test
    fun test02(){
        val root = TreeNode(1).apply{
            left = TreeNode(3).apply {
                left = TreeNode(5).apply {
                    left = TreeNode(6)
                }
            }
            right = TreeNode(2).apply {
                right = TreeNode(9).apply {
                    left = TreeNode(7)
                }
            }
        }

        val expectation = 7

        assertEquals(expectation, solution.widthOfBinaryTree(root))
    }
}