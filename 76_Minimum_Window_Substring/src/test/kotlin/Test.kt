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
        val output = solution.minWindow("ADOBECODEBANC","ABC")
        assertEquals("BANC", output)
    }

    @Test
    fun test02(){
        val output = solution.minWindow("a","a")
        assertEquals("a", output)
    }

    @Test
    fun test03(){
        val output = solution.minWindow("a","aa")
        assertEquals("", output)
    }
}