import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val low = 3
        val high = 3
        val zero = 1
        val one = 1

        val expectation = 8
        assertEquals(expectation, solution.countGoodStrings(low, high, zero, one))
    }

    @Test
    fun test02(){
        val low = 2
        val high = 3
        val zero = 1
        val one = 2

        val expectation = 5
        assertEquals(expectation, solution.countGoodStrings(low, high, zero, one))
    }
    @Test
    fun test03(){
        val low = 10
        val high = 10
        val zero = 10
        val one = 2

        val expectation = 2
        assertEquals(expectation, solution.countGoodStrings(low, high, zero, one))
    }

    @Test
    fun test04(){
        val low = 1
        val high = 100000
        val zero = 1
        val one = 1

        val expectation = 215447031
        assertEquals(expectation, solution.countGoodStrings(low, high, zero, one))
    }

}