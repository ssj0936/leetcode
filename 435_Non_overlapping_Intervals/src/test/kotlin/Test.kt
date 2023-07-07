import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = arrayOf(
            intArrayOf(1,2),
            intArrayOf(2,3),
            intArrayOf(3,4),
            intArrayOf(1,3)
        )
        val expectation = 1
        assertEquals(expectation, solution.eraseOverlapIntervals(input))
    }

    @Test
    fun test02(){
        val input = arrayOf(
            intArrayOf(1,2),
            intArrayOf(1,2),
            intArrayOf(1,2),
        )
        val expectation = 2
        assertEquals(expectation, solution.eraseOverlapIntervals(input))
    }

    @Test
    fun test03(){
        val input = arrayOf(
            intArrayOf(1,2),
            intArrayOf(2,3)
        )
        val expectation = 0
        assertEquals(expectation, solution.eraseOverlapIntervals(input))
    }

    @Test
    fun test04(){
        val input = arrayOf(
            intArrayOf(1,100),
            intArrayOf(11,22),
            intArrayOf(1,11),
            intArrayOf(2,12),
        )
        val expectation = 2
        assertEquals(expectation, solution.eraseOverlapIntervals(input))
    }

    @Test
    fun test05(){
        val input = arrayOf(
            intArrayOf(-1,1),
            intArrayOf(10, 11),
            intArrayOf(12,14),
            intArrayOf(3,4),
        )
        val expectation = 0
        assertEquals(expectation, solution.eraseOverlapIntervals(input))
    }
}