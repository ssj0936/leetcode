import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Solution

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = "1 + 1"
        var expectation = 2
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test02(){
        val input = " 2-1 + 2 "
        var expectation = 3
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test03(){
        val input = "(1+(4+5+2)-3)+(6+8)"
        var expectation = 23
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test04(){
        val input = "16 + 51"
        var expectation = 67
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test05(){
        val input = "1-(     -2)"
        var expectation = 3
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test06(){
        val input = "-2+ 1"
        var expectation = -1
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test07(){
        val input = "(7)-(0)+(4)"
        var expectation = 11
        assertEquals(expectation, solution.calculate(input))
    }

    @Test
    fun test08(){
        val input = "2-4-(8+2-6+(8+4-(1)+8-10))"
        var expectation = -15
        assertEquals(expectation, solution.calculate(input))
    }
}