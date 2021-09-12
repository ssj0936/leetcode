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
        val input = "(()"
        assertEquals(
            solution.sol(input),
            2
        )
    }

    @Test
    fun test02(){
        val input = ")()())"
        assertEquals(
            solution.sol(input),
            4
        )
    }

    @Test
    fun test03(){
        val input = ""
        assertEquals(
            solution.sol(input),
            0
        )
    }

    @Test
    fun test04(){
        val input = "("
        assertEquals(
            solution.sol(input),
            0
        )
    }

    @Test
    fun test05(){
        val input = "((()))())"
        assertEquals(
            solution.sol(input),
            8
        )
    }
}