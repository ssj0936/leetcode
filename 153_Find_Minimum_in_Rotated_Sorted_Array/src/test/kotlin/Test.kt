import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol
    @BeforeEach
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = intArrayOf(3,4,5,1,2)
        val expectedOutput = 1
        assertEquals(expectedOutput, solution.findMin(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(4,5,6,7,0,1,2)
        val expectedOutput = 0
        assertEquals(expectedOutput, solution.findMin(input))
    }

    @Test
    fun test03(){
        val input = intArrayOf(11,13,15,17)
        val expectedOutput = 11
        assertEquals(expectedOutput, solution.findMin(input))
    }
}