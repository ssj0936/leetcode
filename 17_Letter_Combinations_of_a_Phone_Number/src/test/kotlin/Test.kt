import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @Before
    fun setup(){
        solution = SolutionRecursive()
    }

    @Test
    fun test01(){
        val input = "23"
        val expectation = listOf<String>("ad","ae","af","bd","be","bf","cd","ce","cf")
        assertEquals(expectation, solution.letterCombinations(input))
    }
}