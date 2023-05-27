import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val n = 10
        val k = 1
        val maxPts = 10
        val expectation = 1.00000
        assertEquals(expectation, solution.new21Game(n, k, maxPts))
    }

    @Test
    fun test02(){
        val n = 6
        val k = 1
        val maxPts = 10
        val expectation = 0.60000
        assertEquals(expectation, solution.new21Game(n, k, maxPts))
    }

    @Test
    fun test03(){
        val n = 21
        val k = 17
        val maxPts = 10
        val expectation = 0.73278
        assertEquals(expectation, solution.new21Game(n, k, maxPts))
    }

    @Test
    fun test04(){
        val n = 1
        val k = 0
        val maxPts = 2
        val expectation = 0.73278
        assertEquals(expectation, solution.new21Game(n, k, maxPts))
    }
}