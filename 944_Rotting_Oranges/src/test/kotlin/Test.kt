import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution02()
    }

    @Test
    fun test01(){
        val grid = arrayOf(
            intArrayOf(2,1,1),intArrayOf(1,1,0),intArrayOf(0,1,1),)
        assertEquals(4, solution.orangesRotting(grid))
    }

    @Test
    fun test02(){
        val grid = arrayOf(
            intArrayOf(2,1,1),intArrayOf(0,1,1),intArrayOf(1,0,1),)
        assertEquals(-1, solution.orangesRotting(grid))
    }

    @Test
    fun test03(){
        val grid = arrayOf(
            intArrayOf(0,2))
        assertEquals(0, solution.orangesRotting(grid))
    }

    @Test
    fun test04(){
        val grid = arrayOf(
            intArrayOf(0))
        assertEquals(0, solution.orangesRotting(grid))
    }
}