import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Test {
    lateinit var solution:Solu

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        assertEquals( 4,solution.solu(intArrayOf(4,5,6,7,0,1,2),0))
    }

    @Test
    fun test02(){
        assertEquals( -1,solution.solu(intArrayOf(4,5,6,7,0,1,2),3))
    }

    @Test
    fun test03(){
        assertEquals( -1,solution.solu(intArrayOf(1),0))
    }

    @Test
    fun test04(){
        assertEquals( 0,solution.solu(intArrayOf(1),1))
    }

    @Test
    fun test05(){
        assertEquals( 0,solution.solu(intArrayOf(1,99),1))
    }

    @Test
    fun test06(){
        assertEquals( -1,solution.solu(intArrayOf(99,1),0))
    }

    @Test
    fun test07(){
        assertEquals( -1,solution.solu(intArrayOf(1,3),0))
    }
}