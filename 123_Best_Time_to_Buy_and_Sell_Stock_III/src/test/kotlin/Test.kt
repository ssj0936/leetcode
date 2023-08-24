import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(3,3,5,0,0,3,1,4)
        val expectation = 6
        assertEquals(expectation, solution.maxProfit(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(1,2,3,4,5)
        val expectation = 4
        assertEquals(expectation, solution.maxProfit(input))
    }

    @Test
    fun test03(){
        val input = intArrayOf(7,6,4,3,1)
        val expectation = 0
        assertEquals(expectation, solution.maxProfit(input))
    }

    @Test
    fun test04(){
        val input = intArrayOf(1,2)
        val expectation = 1
        assertEquals(expectation, solution.maxProfit(input))
    }

    @Test
    fun test05(){
        val input = intArrayOf(1,4,2)
        val expectation = 3
        assertEquals(expectation, solution.maxProfit(input))
    }
}