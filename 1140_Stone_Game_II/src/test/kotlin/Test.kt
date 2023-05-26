import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val piles = intArrayOf(2,7,9,4,4)
        val expectation = 10

        assertEquals(expectation, solution.stoneGameII(piles))
    }

    @Test
    fun test02(){
        val piles = intArrayOf(1,2,3,4,5,100)
        val expectation = 104

        assertEquals(expectation, solution.stoneGameII(piles))
    }
}