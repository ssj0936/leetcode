import kotlin.test.Test
import kotlin.test.assertEquals

class Test {

    val solution = Solution()

    @Test
    fun test01(){
        val input = arrayOf(
            intArrayOf(9,9,4), intArrayOf(6,6,8), intArrayOf(2,1,1)
        )

        val expectation = 4

        assertEquals(expectation, solution.longestIncreasingPath(input))
    }

    @Test
    fun test02(){
        val input = arrayOf(
            intArrayOf(3,4,5), intArrayOf(3,2,6), intArrayOf(2,2,1)
        )

        val expectation = 4

        assertEquals(expectation, solution.longestIncreasingPath(input))
    }
}