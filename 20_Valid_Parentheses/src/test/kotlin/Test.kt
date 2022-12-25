import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class Test {
    private lateinit var solution : Sol

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val result = solution.isValid("()")
        assertEquals(result, true)
    }

    @Test
    fun test02(){
        val result = solution.isValid("()[]{}")
        assertEquals(result, true)
    }

    @Test
    fun test03(){
        val result = solution.isValid("(]")
        assertEquals(result, false)
    }
}