import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol
    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        assertEquals(
            expected = 5,
            actual = solution.maxProfit(intArrayOf(7,1,5,3,6,4))
        )
    }

    @Test
    fun test02(){
        assertEquals(
            expected = 0,
            actual = solution.maxProfit(intArrayOf(7,6,5,4,3,1))
        )
    }
}