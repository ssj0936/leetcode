import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = SolutionBetterSpace()
    }

    @Test
    fun test01(){
        val n = 5
        val minProfit = 3
        val group = intArrayOf(2,2)
        val profit = intArrayOf(2,3)
        val expectation = 2
        assertEquals(expectation, solution.profitableSchemes(n, minProfit, group, profit))
    }

    @Test
    fun test02(){
        val n = 10
        val minProfit = 5
        val group = intArrayOf(2,3,5)
        val profit = intArrayOf(6,7,8)
        val expectation = 7
        assertEquals(expectation, solution.profitableSchemes(n, minProfit, group, profit))
    }

    @Test
    fun test03(){
        val n = 64
        val minProfit = 0
        val group = intArrayOf(80, 40)
        val profit = intArrayOf(88,88)
        val expectation = 2
        assertEquals(expectation, solution.profitableSchemes(n, minProfit, group, profit))
    }
}