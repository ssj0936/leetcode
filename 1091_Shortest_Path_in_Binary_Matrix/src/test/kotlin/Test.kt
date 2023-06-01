import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = SolutionDFS()

    @Test
    fun test01(){
        val input = arrayOf(
            intArrayOf(0,1),
            intArrayOf(1,0),
        )

        val expectation = 2

        assertEquals(expectation, solution.shortestPathBinaryMatrix(input))
    }

    @Test
    fun test02(){
        val input = arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(1,1,0),
            intArrayOf(1,1,0)
        )

        val expectation = 4

        assertEquals(expectation, solution.shortestPathBinaryMatrix(input))
    }

    @Test
    fun test03(){
        val input = arrayOf(
            intArrayOf(1,0,0),
            intArrayOf(1,1,0),
            intArrayOf(1,1,0)
        )

        val expectation = -1

        assertEquals(expectation, solution.shortestPathBinaryMatrix(input))
    }

    @Test
    fun test04(){
        val input = arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(1,0,0),
            intArrayOf(1,1,0)
        )

        val expectation = 3

        assertEquals(expectation, solution.shortestPathBinaryMatrix(input))
    }

    @Test
    fun test05(){
        val input = arrayOf(
            intArrayOf(0,1,0,0,0,0),
            intArrayOf(0,1,0,1,1,0),
            intArrayOf(0,1,1,0,1,0),
            intArrayOf(0,0,0,0,1,0),
            intArrayOf(1,1,1,1,1,0),
            intArrayOf(1,1,1,1,1,0),
        )

        val expectation = 14

        assertEquals(expectation, solution.shortestPathBinaryMatrix(input))
    }

    @Test
    fun test06(){
        val input = arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(0,1,0),
            intArrayOf(0,0,0),
        )

        val expectation = 4

        assertEquals(expectation, solution.shortestPathBinaryMatrix(input))
    }

    [[0,0,0,0,1],[1,0,0,0,0],[0,1,0,1,0],[0,0,0,1,1],[0,0,0,1,0]]
}

