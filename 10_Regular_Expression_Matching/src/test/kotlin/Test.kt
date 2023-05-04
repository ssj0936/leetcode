import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test00(){
        val s = "aa"
        val p = "a"
        val expectation = false
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test01(){
        val s = "aa"
        val p = "a*"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test02(){
        val s = "ab"
        val p = ".*"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test03(){
        val s = "mississippi"
        val p = "mis*is*p*."
        val expectation = false
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test03_5(){
        val s = "ippi"
        val p = "s*p*."
        val expectation = false
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test04(){
        val s = "a"
        val p = "ab*"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test05(){
        val s = "bbbba"
        val p = ".*a*a"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }
}