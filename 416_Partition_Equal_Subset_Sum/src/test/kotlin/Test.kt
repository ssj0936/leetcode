import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution1DDP()
    }

    @Test
    fun test01(){
        val input = intArrayOf(1,5,11,5)
        assertEquals(true, solution.canPartition(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(1,2,3,5)
        assertEquals(false, solution.canPartition(input))
    }
}