import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @Before
    fun setup(){
        solution = SolutionAmazing()
    }

    @Test
    fun test01(){
        val input = solution.updateMatrix(arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(0,0,0))).contentDeepToString()
        val output = arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(0,0,0)).contentDeepToString()
        assertEquals(output, input)
    }

    @Test
    fun test02(){
        val input = solution.updateMatrix(arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(1,1,1))).contentDeepToString()
        val output = arrayOf(intArrayOf(0,0,0), intArrayOf(0,1,0), intArrayOf(1,2,1)).contentDeepToString()
        assertEquals(output, input)
    }
}