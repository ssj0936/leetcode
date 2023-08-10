import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(1,0,2)
        val expectation = 5
        assertEquals(expectation, solution.candy(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(1,2,2)
        val expectation = 4
        assertEquals(expectation, solution.candy(input))
    }

    @Test
    fun test03(){
        val input = intArrayOf(0,1,2,0)
        val expectation = 7
        assertEquals(expectation, solution.candy(input))
    }

    @Test
    fun test04(){
        val input = intArrayOf(29,51,87,87,72,12)
        val expectation = 12
        assertEquals(expectation, solution.candy(input))
    }

    @Test
    fun test05(){
        val input = intArrayOf(1,3,4,5,2)
        val expectation = 11
        assertEquals(expectation, solution.candy(input))
    }
}