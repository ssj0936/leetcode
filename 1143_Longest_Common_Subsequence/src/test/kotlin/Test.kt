import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val text1 = "abcde"
        val text2 = "ace"
        val expectation = 3
        assertEquals(expectation, solution.longestCommonSubsequence(text1, text2))
    }

    @Test
    fun test02(){
        val text1 = "abc"
        val text2 = "abc"
        val expectation = 3
        assertEquals(expectation, solution.longestCommonSubsequence(text1, text2))
    }

    @Test
    fun test03(){
        val text1 = "abc"
        val text2 = "def"
        val expectation = 0
        assertEquals(expectation, solution.longestCommonSubsequence(text1, text2))
    }

}