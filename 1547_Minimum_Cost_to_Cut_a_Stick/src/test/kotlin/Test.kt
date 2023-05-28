import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = SolutionDP()
    }

    @Test
    fun test01(){
        val n = 7
        val cuts = intArrayOf(1,3,4,5)
        val expectation = 16

        assertEquals(expectation, solution.minCost(n, cuts))
    }

    @Test
    fun test02(){
        val n = 9
        val cuts = intArrayOf(5,6,1,4,2)
        val expectation = 22

        assertEquals(expectation, solution.minCost(n, cuts))
    }

    @Test
    fun test03(){
        val n = 30
        val cuts = intArrayOf(18,15,13,7,5,26,25,29)
        val expectation = 92

        assertEquals(expectation, solution.minCost(n, cuts))
    }
}