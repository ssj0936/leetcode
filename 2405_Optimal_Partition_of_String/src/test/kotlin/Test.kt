import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = SolutionGreedy()

    @Test
    fun test01(){
        val input = "abacaba"
        val expectation = 4
        assertEquals(expectation, solution.partitionString(input))
    }

    @Test
    fun test02(){
        val input = "ssssss"
        val expectation = 6
        assertEquals(expectation, solution.partitionString(input))
    }
}