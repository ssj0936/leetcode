import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    var solution = Solution()

    @Test
    fun test01(){
        val root = TreeNode(1).apply {
            right = TreeNode(2).apply {
                left = TreeNode(3)
                right = TreeNode(4).apply {
                    left = TreeNode(5).apply {
                        right = TreeNode(6).apply {
                            right = TreeNode(7)
                        }
                    }
                    right = TreeNode(8)
                }
            }
        }

        val expectation = 3
        assertEquals(expectation, solution.longestZigZag(root))
    }

    @Test
    fun test02() {
        val root = TreeNode(1).apply {
            left = TreeNode(2).apply {
                right = TreeNode(4).apply {
                    left = TreeNode(5).apply {
                        right = TreeNode(7)
                    }
                    right = TreeNode(6)
                }
            }
            right = TreeNode(3)
        }

        val expectation = 4
        assertEquals(expectation, solution.longestZigZag(root))
    }
}