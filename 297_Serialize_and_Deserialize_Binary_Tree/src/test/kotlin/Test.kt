import org.junit.jupiter.api.BeforeEach
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Solution

    @BeforeEach
    fun setup(){
        solution = CodecNeo()
    }

    @org.junit.jupiter.api.Test
    fun test01(){
//        root = [1,2,3,null,null,4,5]
        val root = TreeNode(1).apply {
            left = TreeNode(2)
            right = TreeNode(3).apply {
                left = TreeNode(4)
                right = TreeNode(5)
            }
        }

        assertEquals("[1,2,3,null,null,4,5]", solution.serialize(root))
    }
}