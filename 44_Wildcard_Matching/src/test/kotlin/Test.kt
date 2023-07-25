import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val s = "aa"
        val p = "a"
        val expectation = false
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test02(){
        val s = "aa"
        val p = "*"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test03(){
        val s = "cb"
        val p = "?a"
        val expectation = false
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test04(){
        val s = "aa"
        val p = "a*"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test05(){
        val s = "a"
        val p = ""
        val expectation = false
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test06(){
        val s = "adceb"
        val p = "*a*b"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test07(){
        val s = ""
        val p = "******"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test08(){
        val s = "ho"
        val p = "**ho"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }

    @Test
    fun test09(){
        val s = "abcabczzzde"
        val p = "*abc???de*"
        val expectation = true
        assertEquals(expectation, solution.isMatch(s, p))
    }
}