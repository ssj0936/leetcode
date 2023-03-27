import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val height = intArrayOf(2,1,5,6,2,3)
        val expectation = 10
        assertEquals(expectation, solution.largestRectangleArea(height))
    }

    @Test
    fun test02(){
        val height = intArrayOf(2,4)
        val expectation = 4
        assertEquals(expectation, solution.largestRectangleArea(height))
    }
}