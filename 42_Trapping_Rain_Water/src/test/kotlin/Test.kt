import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution2()
    }

    @Test
    fun test01(){
        val input = intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)
        val output = 6
        assertEquals(output, solution.trap(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(4,2,0,3,2,5)
        val output = 9
        assertEquals(output, solution.trap(input))
    }
}