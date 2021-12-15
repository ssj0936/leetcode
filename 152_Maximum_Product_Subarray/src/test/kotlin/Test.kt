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
        val input = intArrayOf(2,3,-2,4)
        assertEquals(solution.maxProduct(input),6)
    }
}