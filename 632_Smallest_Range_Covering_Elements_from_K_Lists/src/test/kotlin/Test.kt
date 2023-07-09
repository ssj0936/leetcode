import kotlin.test.Test
import kotlin.test.assertContentEquals

class Test {
    private val solution = Solution()


    @Test
    fun test01(){
        val input = listOf(
            listOf(4,10,15,24,26),
            listOf(0,9,12,20),
            listOf(5,18,22,30),
        )

        val expectation = intArrayOf(20, 24)

        assertContentEquals(expectation, solution.smallestRange(input))
    }
}