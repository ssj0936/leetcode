import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val values = intArrayOf(1,2,3,7)
        val expectation = "Bob"

        assertEquals(expectation, solution.stoneGameIII(values))
    }

    @Test
    fun test02(){
        val values = intArrayOf(1,2,3,-9)
        val expectation = "Alice"

        assertEquals(expectation, solution.stoneGameIII(values))
    }

    @Test
    fun test03(){
        val values = intArrayOf(1,2,3,6)
        val expectation = "Tie"

        assertEquals(expectation, solution.stoneGameIII(values))
    }
}