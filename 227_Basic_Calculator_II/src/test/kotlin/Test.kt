import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = "3+2*2"
        val expectation = 7
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test02(){
        val input = " 3/2 "
        val expectation = 1
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test03(){
        val input = " 3+5 / 2 "
        val expectation = 5
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test04(){
        val input = "0-2147483647"
        val expectation = -2147483647
        assertEquals(expectation, solution.calculate(input))
    }
}