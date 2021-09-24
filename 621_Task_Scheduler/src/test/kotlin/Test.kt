import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Solu
    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = charArrayOf('A','A','A','B','B','B')
        val n = 2
        assert(solution.solu(input,n)==8)
    }

    @Test
    fun test02(){
        val input = charArrayOf('A','A','A','A','A','A','B','C','D','E','F','G')
        val n = 2
        assertEquals(solution.solu(input,n) , 16)
    }
}