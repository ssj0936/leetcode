import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val question = arrayOf(
            intArrayOf(3,2),
            intArrayOf(4,3),
            intArrayOf(4,4),
            intArrayOf(2,5)
        )
        val expectation = 5L

        assertEquals(expectation, solution.mostPoints(question))
    }

    @Test
    fun test02(){
        val question = arrayOf(
            intArrayOf(1,1),
            intArrayOf(2,2),
            intArrayOf(3,3),
            intArrayOf(4,4),
            intArrayOf(5,5)
        )
        val expectation = 7L

        assertEquals(expectation, solution.mostPoints(question))
    }

}