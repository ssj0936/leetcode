import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val price = intArrayOf(7,1,5,3,6,4)
        val expectation = 7
        assertEquals(expectation, solution.maxProfit(price))
    }

    @Test
    fun test02(){
        val price = intArrayOf(1,2,3,4,5)
        val expectation = 4
        assertEquals(expectation, solution.maxProfit(price))
    }

    @Test
    fun test03(){
        val price = intArrayOf(7,6,4,3,1)
        val expectation = 0
        assertEquals(expectation, solution.maxProfit(price))
    }
}