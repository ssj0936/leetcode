import kotlin.test.Test
import kotlin.test.assertContentEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(1,1,1,2,2,3)
        val k = 2
        val expectation = intArrayOf(1,2)

        assertContentEquals(expectation, solution.topKFrequent(input, k))
    }

    @Test
    fun test02(){
        val input = intArrayOf(1)
        val k = 1
        val expectation = intArrayOf(1)

        assertContentEquals(expectation, solution.topKFrequent(input, k))
    }
}