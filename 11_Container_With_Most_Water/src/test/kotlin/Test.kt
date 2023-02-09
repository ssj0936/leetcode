import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = intArrayOf(1,8,6,2,5,4,8,3,7)
        val expectation = 49
        assertEquals(expectation, solution.maxArea(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(1,1)
        val expectation = 1
        assertEquals(expectation, solution.maxArea(input))
    }
}