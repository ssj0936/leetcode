import kotlin.test.Test
import kotlin.test.assertContentEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(1,2,3)
        val expectation = intArrayOf(1,3,2)
        assertContentEquals(expectation, input.apply { solution.nextPermutation(this) })
    }

    @Test
    fun test02(){
        val input = intArrayOf(3,2,1)
        val expectation = intArrayOf(1,2,3)
        assertContentEquals(expectation, input.apply { solution.nextPermutation(this) })
    }

    @Test
    fun test03(){
        val input = intArrayOf(1,1,5)
        val expectation = intArrayOf(1,5,1)
        assertContentEquals(expectation, input.apply { solution.nextPermutation(this) })
    }

    @Test
    fun test04(){
        val input = intArrayOf(2,2,7,5,4,3,2,2,1)
        val expectation = intArrayOf(2,3,1,2,2,2,4,5,7)
        assertContentEquals(expectation, input.apply { solution.nextPermutation(this) })
    }
}