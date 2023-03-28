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
        val input = arrayOf("2","1","+","3","*")
        val output = 9
        assertEquals(output, solution.evalRPN(input))
    }

    @Test
    fun test02(){
        val input = arrayOf("4","13","5","/","+")
        val output = 6
        assertEquals(output, solution.evalRPN(input))
    }

    @Test
    fun test03(){
        val input = arrayOf("10","6","9","3","+","-11","*","/","*","17","+","5","+")
        val output = 22
        assertEquals(output, solution.evalRPN(input))
    }
}