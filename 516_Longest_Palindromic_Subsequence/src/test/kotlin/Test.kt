import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = "bbbab"
        val expectation = 4
        assertEquals(expectation, solution.longestPalindromeSubseq(input))
    }

    @Test
    fun test02(){
        val input = "cbbd"
        val expectation = 2
        assertEquals(expectation, solution.longestPalindromeSubseq(input))
    }
}