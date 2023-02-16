import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Sol

    @Before
    fun setup(){
        solution = Solution3()
    }

    @Test
    fun test01(){
        val n = 4
        val edges = arrayOf(intArrayOf(1,0), intArrayOf(1,2), intArrayOf(1,3))
        val output = listOf(1)
        assertEquals(output, solution.findMinHeightTrees(n,edges))
    }

    @Test
    fun test02(){
        val n = 6
        val edges = arrayOf(intArrayOf(3,0), intArrayOf(3,1), intArrayOf(3,2), intArrayOf(3,4), intArrayOf(5,4))
        val output = listOf(3,4)
        assertEquals(output, solution.findMinHeightTrees(n,edges))
    }

    @Test
    fun test03(){
        val n = 1
        val edges = Array<IntArray>(0) {IntArray(2)}
        val output = listOf<Int>(0)
        assertEquals(output, solution.findMinHeightTrees(n,edges))
    }
}