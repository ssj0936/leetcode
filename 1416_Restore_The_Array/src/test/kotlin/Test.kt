import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Sol

    @BeforeTest
    fun setup(){
        solution = SolutionBetter()
    }

    @Test
    fun test01(){
        val s = "1000"
        val k = 10000
        val expectation = 1

        assertEquals(expectation, solution.numberOfArrays(s, k))
    }

    @Test
    fun test02(){
        val s = "1000"
        val k = 10
        val expectation = 0

        assertEquals(expectation, solution.numberOfArrays(s, k))
    }

    @Test
    fun test03(){
        val s = "1317"
        val k = 2000
        val expectation = 8

        assertEquals(expectation, solution.numberOfArrays(s, k))
    }

    @Test
    fun test04(){
        val s = "1234567890"
        val k = 90
        val expectation = 34

        assertEquals(expectation, solution.numberOfArrays(s, k))
    }

    @Test
    fun test05(){
        val s = "1111111111111"
        val k = 1000000000
        val expectation = 4076

        assertEquals(expectation, solution.numberOfArrays(s, k))
    }
}