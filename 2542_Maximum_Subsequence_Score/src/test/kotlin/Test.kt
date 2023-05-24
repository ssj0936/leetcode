import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Test {
    private val solution = Solution2()

    @Test
    fun test01(){
        val nums1 = intArrayOf(1,3,3,2)
        val nums2 = intArrayOf(2,1,3,4)
        val k = 3
        val expectation:Long = 12
        assertEquals(expectation, solution.maxScore(nums1, nums2, k))
    }

    @Test
    fun test02(){
        val nums1 = intArrayOf(4,2,3,1,1)
        val nums2 = intArrayOf(7,5,10,9,6)
        val k = 1
        val expectation:Long = 30
        assertEquals(expectation, solution.maxScore(nums1, nums2, k))
    }
}