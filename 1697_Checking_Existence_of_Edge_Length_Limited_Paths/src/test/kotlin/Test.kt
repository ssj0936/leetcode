import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = SolutionUnion()
    }

    @Test
    fun test01(){
        val n = 3
        val edgeList = arrayOf(
            intArrayOf(0,1,2),
            intArrayOf(1,2,4),
            intArrayOf(2,0,8),
            intArrayOf(1,0,16),
        )

        val queries = arrayOf(
            intArrayOf(0,1,2),
            intArrayOf(0,2,5)
        )

        val expectation = booleanArrayOf(false, true)

        assertContentEquals(expectation, solution.distanceLimitedPathsExist(n, edgeList, queries))
    }

    @Test
    fun test02(){
        val n = 5
        val edgeList = arrayOf(
            intArrayOf(0,1,10),
            intArrayOf(1,2,5),
            intArrayOf(2,3,9),
            intArrayOf(3,4,13),
        )

        val queries = arrayOf(
            intArrayOf(0,4,14),
            intArrayOf(1,4,13)
        )

        val expectation = booleanArrayOf(true,false)

        assertContentEquals(expectation, solution.distanceLimitedPathsExist(n, edgeList, queries))
    }
}