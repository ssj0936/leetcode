import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(5,2,6,1)
        val expectation = listOf(2,1,1,0)
        assertContentEquals(expectation, solution.countSmaller(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(-1)
        val expectation = listOf(0)
        assertContentEquals(expectation, solution.countSmaller(input))
    }

    @Test
    fun test03(){
        val input = intArrayOf(-1,-1)
        val expectation = listOf(0,0)
        assertContentEquals(expectation, solution.countSmaller(input))
    }
}