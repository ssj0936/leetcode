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
    fun test1(){
        assertEquals(true,
            solution.canFinish(2, arrayOf(intArrayOf(1,0))))
    }

    @Test
    fun test2(){
        assertEquals(false,
            solution.canFinish(2, arrayOf(intArrayOf(1,0),intArrayOf(0,1))))
    }
}