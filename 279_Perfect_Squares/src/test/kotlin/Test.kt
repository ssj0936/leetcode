import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution : Sol

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        assertEquals(solution.numSquares(12),3)
    }

    @Test
    fun test02(){
        assertEquals(solution.numSquares(13),2)
    }
}