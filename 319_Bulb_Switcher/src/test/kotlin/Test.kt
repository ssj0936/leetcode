import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = SolutionBest()

    @Test
    fun test01(){
        val n = 3
        val expectation = 1
        assertEquals(expectation, solution.bulbSwitch(n))
    }

    @Test
    fun test02(){
        val n = 0
        val expectation = 0
        assertEquals(expectation, solution.bulbSwitch(n))
    }

    @Test
    fun test03(){
        val n = 1
        val expectation = 1
        assertEquals(expectation, solution.bulbSwitch(n))
    }

    @Test
    fun test04(){
        val n = 4
        val expectation = 2
        assertEquals(expectation, solution.bulbSwitch(n))
    }
}