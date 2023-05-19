import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
//        [[1,2,3],[0,2],[0,1,3],[0,2]]
        val graph = arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(0,2),
            intArrayOf(0,1,3),
            intArrayOf(0,2)
        )

        val expectation = false
        assertEquals(expectation, solution.isBipartite(graph))
    }

    @Test
    fun test02(){
//[[1,3],[0,2],[1,3],[0,2]]
        val graph = arrayOf(
            intArrayOf(1,3),
            intArrayOf(0,2),
            intArrayOf(1,3),
            intArrayOf(0,2)
        )

        val expectation = true
        assertEquals(expectation, solution.isBipartite(graph))
    }

    @Test
    fun test03(){
//[[1,3],[0,2],[1,3],[0,2]]
        val graph = arrayOf(
            intArrayOf(1),
            intArrayOf(0,2,3),
            intArrayOf(3),
            intArrayOf(1)
        )

        val expectation = false
        assertEquals(expectation, solution.isBipartite(graph))
    }
}