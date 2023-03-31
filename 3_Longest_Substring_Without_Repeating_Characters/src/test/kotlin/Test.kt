import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Solution

    @BeforeTest
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = "abcabcbb"
        val expectation = 3
        assertEquals(expectation, solution.lengthOfLongestSubstring(input))
    }

    @Test
    fun test02(){
        val input = "bbbbb"
        val expectation = 1
        assertEquals(expectation, solution.lengthOfLongestSubstring(input))
    }

    @Test
    fun test03(){
        val input = "pwwkew"
        val expectation = 3
        assertEquals(expectation, solution.lengthOfLongestSubstring(input))
    }

    @Test
    fun test04(){
        val input = "tmmzuxt"
        val expectation = 5
        assertEquals(expectation, solution.lengthOfLongestSubstring(input))
    }
}