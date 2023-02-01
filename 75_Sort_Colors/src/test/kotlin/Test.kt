import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = intArrayOf(2,0,2,1,1,0)
        solution.sortColors(input)
        assertArrayEquals(intArrayOf(0,0,1,1,2,2), input)
    }

    @Test
    fun test02(){
        val input = intArrayOf(2,0,1)
        solution.sortColors(input)
        assertArrayEquals(intArrayOf(0,1,2), input)
    }

    @Test
    fun test03(){
        val input = intArrayOf(1,2,0)
        solution.sortColors(input)
        assertArrayEquals(intArrayOf(0,1,2), input)
    }
}