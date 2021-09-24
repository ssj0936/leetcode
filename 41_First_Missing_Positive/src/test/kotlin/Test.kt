import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: SolutionInterface

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = intArrayOf(1,2,0)
        assertEquals(solution.solution(input),3)
    }

    @Test
    fun test02(){
        val input = intArrayOf(3,4,-1,1)
        assertEquals(solution.solution(input),2)
    }

    @Test
    fun test03(){
        val input = intArrayOf(7,8,9,11,12)
        assertEquals(solution.solution(input),1)
    }

    @Test
    fun test04(){
        val input = intArrayOf(2,0)
        assertEquals(solution.solution(input),1)
    }

    @Test
    fun test05(){
        val input = intArrayOf(1,0)
        assertEquals(solution.solution(input),2)
    }

    @Test
    fun test06(){
        val input = intArrayOf(1,2)
        assertEquals(solution.solution(input),3)
    }

    @Test
    fun test07(){
        val input = intArrayOf(2)
        assertEquals(solution.solution(input),1)
    }

    @Test
    fun test08(){
        val input = intArrayOf(0)
        assertEquals(solution.solution(input),1)
    }

    @Test
    fun test09(){
        val input = intArrayOf(1)
        assertEquals(solution.solution(input),2)
    }

    @Test
    fun test10(){
        val input = intArrayOf(1,1)
        assertEquals(solution.solution(input),2)
    }
}