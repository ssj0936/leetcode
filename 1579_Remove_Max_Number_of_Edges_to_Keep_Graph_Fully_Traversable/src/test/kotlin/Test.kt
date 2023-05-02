import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = SolutionBetter()
    }

    @Test
    fun test01(){
        val edges = arrayOf(
            intArrayOf(3,1,2),
            intArrayOf(3,2,3),
            intArrayOf(1,1,3),
            intArrayOf(1,2,4),
            intArrayOf(1,1,2),
            intArrayOf(2,3,4),
        )

        val n = 4
        val expectation = 2
        assertEquals(expectation, solution.maxNumEdgesToRemove(n, edges))
    }

    @Test
    fun test01_5(){
        val edges = arrayOf(
            intArrayOf(3,1,2),
            intArrayOf(3,2,3),
            intArrayOf(3,1,3),
            intArrayOf(1,1,3),
            intArrayOf(1,2,4),
            intArrayOf(1,1,2),
            intArrayOf(2,3,4),
        )

        val n = 4
        val expectation = 3
        assertEquals(expectation, solution.maxNumEdgesToRemove(n, edges))
    }

    @Test
    fun test02(){
        val edges = arrayOf(
            intArrayOf(3,1,2),
            intArrayOf(3,2,3),
            intArrayOf(1,1,4),
            intArrayOf(2,1,4)
        )

        val n = 4
        val expectation = 0
        assertEquals(expectation, solution.maxNumEdgesToRemove(n, edges))
    }

    @Test
    fun test03(){
        val edges = arrayOf(
            intArrayOf(3,1,2),
            intArrayOf(1,1,2),
            intArrayOf(2,3,4),
        )

        val n = 4
        val expectation = -1
        assertEquals(expectation, solution.maxNumEdgesToRemove(n, edges))
    }
}