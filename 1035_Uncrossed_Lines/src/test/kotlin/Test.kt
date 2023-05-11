import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val num1 = intArrayOf(1,4,2)
        val num2 = intArrayOf(1,2,4)
        val expectation = 2

        assertEquals(expectation, solution.maxUncrossedLines(num1, num2))
    }

    @Test
    fun test02(){
        val num1 = intArrayOf(2,5,1,2,5)
        val num2 = intArrayOf(10,5,2,1,5,2)
        val expectation = 3

        assertEquals(expectation, solution.maxUncrossedLines(num1, num2))
    }

    @Test
    fun test03(){
        val num1 = intArrayOf(1,3,7,1,7,5)
        val num2 = intArrayOf(1,9,2,5,1)
        val expectation = 2

        assertEquals(expectation, solution.maxUncrossedLines(num1, num2))
    }
}