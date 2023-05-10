import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val croakOfFrogs = "croakcroak"
        val expectation = 1
        assertEquals(expectation, solution.minNumberOfFrogs(croakOfFrogs))
    }

    @Test
    fun test02(){
        val croakOfFrogs = "crcoakroak"
        val expectation = 2
        assertEquals(expectation, solution.minNumberOfFrogs(croakOfFrogs))
    }

    @Test
    fun test03(){
        val croakOfFrogs = "croakcrook"
        val expectation = -1
        assertEquals(expectation, solution.minNumberOfFrogs(croakOfFrogs))
    }

    @Test
    fun test04(){
        val croakOfFrogs = "croakcroa"
        val expectation = -1
        assertEquals(expectation, solution.minNumberOfFrogs(croakOfFrogs))
    }
}