import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class Test {
    private lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution01()
    }

    @Test
    fun test01(){
        val input = arrayOf(intArrayOf(1,2,3),intArrayOf(4,5,6),intArrayOf(7,8,9))
        val expectation = listOf(1,2,3,6,9,8,7,4,5)
        assertContentEquals(expectation, solution.spiralOrder(input))
    }

    @Test
    fun test02(){
        val input = arrayOf(intArrayOf(1,2,3,4),intArrayOf(5,6,7,8),intArrayOf(9,10,11,12))
        val expectation = listOf(1,2,3,4,8,12,11,10,9,5,6,7)
        assertContentEquals(expectation, solution.spiralOrder(input))
    }
}