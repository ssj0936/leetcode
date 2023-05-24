import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = Solution2()
    }

    @Test
    fun test01(){
        val equations = listOf(
            listOf("a","b"),
            listOf("b","c"),
        )

        val values = doubleArrayOf(2.0, 3.0)

        val queries = listOf(
            listOf("a","c"),
            listOf("b","a"),
            listOf("a","e"),
            listOf("a","a"),
            listOf("x","x")
        )

        val expectation = doubleArrayOf(6.00000,0.50000,-1.00000,1.00000,-1.00000)

        assertContentEquals(expectation, solution.calcEquation(equations, values, queries))
    }
}