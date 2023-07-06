import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(1,2,3)
        val target = 4
        val expectation = 7

        assertEquals(expectation, solution.combinationSum4(input, target))
    }

    @Test
    fun test02(){
        val input = intArrayOf(9)
        val target = 3
        val expectation = 0

        assertEquals(expectation, solution.combinationSum4(input, target))
    }
}