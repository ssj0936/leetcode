import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Sol

    @BeforeTest
    fun setup(){
        solution = Solution2Pointer()
    }

    @Test
    fun test01(){
        val nums = intArrayOf(3,5,6,7)
        val target = 9
        val expectation = 4
        assertEquals(expectation, solution.numSubseq(nums, target))
    }

    @Test
    fun test02(){
        val nums = intArrayOf(3,3,6)
        val target = 10
        val expectation = 6
        assertEquals(expectation, solution.numSubseq(nums, target))
    }

    @Test
    fun test03(){
        val nums = intArrayOf(2,3,3,4,6,7)
        val target = 12
        val expectation = 61
        assertEquals(expectation, solution.numSubseq(nums, target))
    }
}