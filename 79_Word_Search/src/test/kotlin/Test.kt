import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol
    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = arrayOf(
            charArrayOf('A','B','C','E'),
            charArrayOf('S','F','C','S'),
            charArrayOf('A','D','E','E'),
        )

        assertEquals(solution.exist(input,"ABCCED"), true)
    }

    @Test
    fun test02(){
        val input = arrayOf(
            charArrayOf('A','B','C','E'),
            charArrayOf('S','F','C','S'),
            charArrayOf('A','D','E','E'),
        )

        assertEquals(solution.exist(input,"SEE"), true)
    }

    @Test
    fun test03(){
        val input = arrayOf(
            charArrayOf('A','B','C','E'),
            charArrayOf('S','F','C','S'),
            charArrayOf('A','D','E','E'),
        )

        assertEquals(solution.exist(input,"ABCB"), false)
    }
}