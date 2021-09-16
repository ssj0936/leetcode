import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Test {
    lateinit var solution:Sol
    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        assertTrue { solution.sol(intArrayOf(2,3,1,1,4)) }
    }

    @Test
    fun test02(){
        assertFalse { solution.sol(intArrayOf(3,2,1,0,4)) }
    }

    @Test
    fun test03(){
        assertTrue { solution.sol(intArrayOf(2,0,0)) }
    }

    @Test
    fun test04(){
        assertFalse { solution.sol(intArrayOf(0,1)) }
    }

    @Test
    fun test05(){
        assertTrue { solution.sol(intArrayOf(3,0,8,2,0,0,1)) }
    }
}