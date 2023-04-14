import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Sol

    @BeforeTest
    fun setup(){
        solution = SolutionBetter()
    }

    @Test
    fun test01(){
        var p:TreeNode
        var q:TreeNode

        val root = TreeNode(3).apply {
            left = TreeNode(5).apply {
                left = TreeNode(6)
                right = TreeNode(2).apply {
                    left = TreeNode(7)
                    right = TreeNode(4)
                }
            }

            right = TreeNode(1).apply {
                left = TreeNode(0)
                right = TreeNode(8)
            }

            p = this.left!!
            q = this.right!!
        }

        val result = solution.lowestCommonAncestor(root, p,q)
        assertEquals(3, result!!.`val`)
    }

    @Test
    fun test02(){
        var p:TreeNode
        var q:TreeNode

        val root = TreeNode(3).apply {
            left = TreeNode(5).apply {
                left = TreeNode(6)
                right = TreeNode(2).apply {
                    left = TreeNode(7)
                    right = TreeNode(4)
                    q = this.right!!
                }
            }

            right = TreeNode(1).apply {
                left = TreeNode(0)
                right = TreeNode(8)
            }

            p = this.left!!
        }

        val result = solution.lowestCommonAncestor(root, p,q)
        assertEquals(5, result!!.`val`)
    }
}