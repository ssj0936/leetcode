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
        val result = solution.numDecodings("12")
        assertEquals(2, result)
    }

    @Test
    fun test02(){
        val result = solution.numDecodings("226")
        assertEquals(3, result)
    }

    @Test
    fun test03(){
        val result = solution.numDecodings("06")
        assertEquals(0, result)
    }

    @Test
    fun test04(){
        val result = solution.numDecodings("27")
        assertEquals(1, result)
    }
}