import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(1,0,4,0,4,1,4,3,1,1,1,2,1,4,0,3,0,3,0,3,0,5,3,0,0,1,2,1,2,4,3,0,1,0,5,2)
        val output = solution.minTaps(35, input)
        assertEquals(output, 6)
    }

    @Test
    fun test02(){
        val input = intArrayOf(3,0,1,1,0,0)
        val n = 5
        val output = solution.minTaps(n, input)
        assertEquals(output, -1)
    }
    
    @Test
    fun test03(){
        val input = intArrayOf(0,1,0,0,0,1)
        val n = input.size-1
        val output = solution.minTaps(n, input)
        assertEquals(output, -1)
    }
//    [1,0,1,0,0,1,1,1,0,0,1,0,1,1,0,1,0,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,0,0,1,1,1,1,1,0,0,1,0,0,0,1,1,0,0,0,1,1,1,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,0,1,0,0,1,0,0,0,0,1,1,0,0,0,1,0,1,1,0,1,0,0,1,1,1,0,0,0,0,1,1,0,0,1,1,1,0,0,0,0,0,0,0,1,1,0,0,0,1,1,1,1,1,1,0,0,1,0,0,1,1,0,0,1,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,0,0,1,1,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,1,1,0,1,1,1,0,0,0,1,1,0,0,1,0,1,0,0,1,1,1,0,1,1,1,0,1,1,0,0,1,1,0,0,1,1,1,0,0,0,0,0,1,0,0,0,1,0,1,1,1,1,0,1,0,0,0,1,0,1,0,0,0,1,1,1,0,0,1,0,0,1,0,1,0,1,1,0,1,1,1,1,0,1,0,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,0,0,0,0,1,1,0,0,1,1,0,1,0,1,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,0,1,1,1,0,0,1,1,0,0,1,1,0,0,0,1,0,1,0,1,0,1,0,1,0,0,0,1,1,1,0,0,0,1,0,1,1,1,1,0,0,1,1,1,0,0,1,0,0,1,0,0,0,1,1,0,1,1,1,1,0,1,0,1,0,0,0,1,1,1,0,0,1,1,1,0,0,0,0,1,1,1,0,0,1,1,1,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,1,1,0,1,1,1,0,0,0,1,1,0,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,1,1,1,1,0,0,0,1,1,1,0,1,1,0,1,0,1,1,1,0,0,1,1,0,1,0,0,0,0,0,1,1,0,0,0,1,0,1,1,1,1,1,1,0,1,0,1,1,0,0,0,1,0,0,1,1,1,0,0,1,0,1,1,1,0,1,1,0,0,1,1,1,1,1,1,1,0,0,0,1,0,0,0,1,0,0,0,1,1,0,1,1,1,1,0,1,0,1,1,1,1,0,0,1,0,1,1,0,0,0,0,1,0,1,0,1,0,0,1,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,1,1,1,0,1,0,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,0,1,1,1,1,1,0,0,1,1,0,0,1,1,1,0,0,0,1,1,0,1,0,0,1,1,1,1,0,1,1,1,0,1,0,0,1,0,0,1,1,1,1,1,1,1,0,1,0,0,1,0,0,0,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,1,0,1,1,0,0,1,1,1,1,0,0,1,0,1,0,1,0,0,1,1,1,1,0,0,0,1,1,0,1,1,1,1,0,0,1,1,0,1,0,0,1,0,0,1,1,0,1,1,1,0,0,0,0,1,0,1,0,1,1,1,0,1,0,0,0,0,1,0,0,1,1,0,1,1,1,1,0,1,1,0,1,0,0,1,0,1,1,1,0,0,0,0,0,1,1,1,0,1,1,1,1,1,1,1,0,0,1,0,0,0,1,0,1,1,1,1,1,0,0,0,1,1,1,0,1,0,1,1,0,1,1,1,1,0,0,1,0,1,1,0,1,1,0,0,1,0,1,0,0,0,1,0,0,0,1,1,0,0,1,1,1,0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,0,1,1,0,0,1,1,0,1,1,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,1,0,1,0,1,1,1,0,1,1,0,1,1,0,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,0,0,1,1,1,1,1,0,0,0,0,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,1,1,1,1,1,0,0,0,1,0,1,1,0,1,0,1,1,0,1,1,1,0,1,0,1,1,0,1,0,0,0,0,1,1,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,1,0,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,0,0,0,1,0,0,1,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1,0,1,1,1,1,0,1,0,1,0,0,1,0,1,1,0,1,0,0,1,0,0,1,0,1,0,0,0,0,0,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,0,1,0,0,0,0,0,0,0,1,1,1,0,1,0,0,1,1,0,0,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,0,0,0,1,1,1,0,0,1,1,0,1,0,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,1,0,0,1,1,1,0,1,1,1,1,0,0,0,1,1,1,1,0,1,1,0,1,0,1,0,1,0,0,1,0,1,1,1,1,1,1,0,0,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,0,1,0,1,1,0,0,1,0,0,1,0,1,0,0,0,1,1,1,0,0,0,0,0,1,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,0,0,1,0,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,0,0,0,1,0,1,0,0,1,1,1,0,1,0,0,1,1,0,0,1,0,0,1,0,1,0,0,1,1,0,1,1,1,1,1,1,0,1,0,0,0,1,1,1,1,0,0,0,1,1,1,1,0,0,1,0,0,1,0,0,0,0,1,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,0,0,1,0,0,1,0,1,0,1,0,1,1,1,1,1,0,0,0,0,0,1,0,1,0,1,1,0,1,1,1,1,1,1,1,0,1,1,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,1,0,1,1,0,0,1,0,0,1,0,0,0,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,0,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,1,1,1,0,1,0,1,1,0,0,1,0,1,1,1,1,0,1,0,0,1,0,1,1,0,0,1,1,1,1,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,1,1,1,0,0,0,1,0,0,1,1,1,0,1,1,1,1,1,0,1,1,0,0,0,0,1,0,0,0,1,1,0,1,1,1,0,0,0,0,1,1,0,1,1,0,0,1,0,1,0,0,1,0,0,0,0,0,0,1,1,1,0,0,1,0,1,0,0,0,0,1,1,0,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,0,0,1,0,0,1,1,0,1,1,1,1,0,0,0,1,1,0,0,0,0,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,1,0,1,0,0,1,1,0,0,1,1,1,0,0,0,1,0,0,1,0,1,1,0,1,0,1,0,0,1,0,1,0,0,0,0,0,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,0,0,0,0,1,1,1,1,1,1,0,0,1,1,0,1,0,0,1,0,1,1,0,1,1,0,1,0,0,1,0,1,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,0,1,0,0,1,0,0,1,0,1,0,1,1,0,1,0,1,0,1,1,0,1,1,1,0,0,0,1,0,1,1,0,0,1,0,1,1,0,0,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,0,0,1,1,0,0,0,1,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,1,1,0,1,0,1,1,0,0,0,0,0,1,0,0,1,1,0,1,0,0,1,1,0,0,1,1,1,1,0,1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,1,0,1,0,1,1,0,0,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,1,0,0,1,0,0,0,0,0,0,1,0,1,1,0,0,1,0,1,0,1,1,0,1,1,1,1,0,1,0,0,0,1,1,1,1,0,1,1,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,1,1,1,1,0,0,0,1,0,0,1,1,1,0,1,1,0,0,1,1,0,0,0,0,1,0,1,1,0,0,0,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0,0,0,1,1,0,0,1,1,1,1,0,1,1,1,0,0,1,1,1,0,0,1,1,1,0,1,1,0,0,1,1,1,1,0,0,0,0,0,1,0,1,1,0,1,0,0,1,0,1,1,0,0,0,1,0,0,0,1,0,1,0,0,1,1,1,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,1,1,0,1,1,1,1,1,0,1,0,1,0,0,0,1,0,0,1,1,0,1,1,1,0,1,1,0,0,1,1,1,0,1,1,1,1,0,0,1,0,0,0,0,1,1,1,1,0,1,1,1,0,1,0,0,0,0,0,0,1,1,1,0,0,1,1,1,0,1,0,0,0,0,0,1,1,0,1,0,0,1,0,0,1,0,1,1,0,1,0,1,1,1,0,0,1,1,1,0,0,1,1,1,0,0,1,1,1,1,0,1,0,1,1,0,0,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,1,0,1,0,1,0,1,0,1,1,0,1,0,1,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,0,0,1,0,1,0,1,1,1,1,0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0,1,0,0,1,0,0,1,1,1,1,0,1,0,0,1,1,0,1,1,1,1,1,0,0,1,0,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,0,1,1,1,1,1,1,1,1,0,0,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,0,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,1,1,1,0,0,1,0,0,1,1,1,0,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,0,0,1,1,0,1,1,1,1,1,0,1,0,1,0,0,0,1,0,0,1,0,1,1,1,0,0,0,1,1,0,1,0,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,0,0,1,1,1,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,1,0,0,1,1,0,0,1,1,0,0,0,1,0,1,1,1,0,1,1,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,1,1,1,0,1,0,1,0,0,1,1,0,1,1,0,0,1,0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,1,1,1,0,0,1,1,0,0,1,1,1,1,1,1,0,1,0,0,1,0,1,1,1,0,1,0,1,1,0,0,0,0,0,0,1,0,0,1,1,1,1,0,0,0,1,0,0,1,1,0,1,0,0,0,1,0,1,0,1,1,1,1,0,0,1,0,1,0,0,0,1,0,1,1,1,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,1,1,1,0,1,1,0,1,0,0,1,0,1,1,1,0,0,1,1,0,1,1,1,0,0,0,1,1,0,1,1,0,1,1,1,1,0,0,1,1,1,0,1,1,0,0,0,0,0,0,0,1,1,0,0,1,1,0,1,0,0,0,0,0,0,0,1,1,1,0,1,0,1,1,1,0,0,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,0,1,1,0,1,0,0,0,0,0,0,1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,1,1,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,1,0,0,1,0,0,0,1,0,1,1,1,0,0,1,1,0,1,0,1,1,1,1,0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,1,1,1,0,0,1,1,0,1,0,1,1,0,1,1,1,0,0,1,1,0,1,1,1,0,1,0,1,1,1,0,0,1,0,1,0,0,1,0,1,1,1,1,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,1,0,0,0,1,1,0,1,0,0,0,0,1,0,0,1,1,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,1,1,1,0,1,1,0,1,0,0,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,1,1,0,1,1,0,1,0,1,1,0,0,0,0,1,1,0,1,0,0,1,1,1,0,0,0,0,1,0,1,1,0,1,0,0,0,1,1,1,1,1,0,1,0,0,1,1,0,0,1,0,0,0,1,0,0,0,0,1,1,1,0,1,1,1,0,0,0,0,1,1,1,1,0,1,1,1,0,0,1,0,1,1,1,1,1,1,1,0,0,0,0,1,0,0,1,1,1,1,0,1,0,1,1,0,1,1,1,1,1,0,0,1,1,0,1,1,0,1,1,1,1,0,1,0,1,1,1,0,0,1,0,0,0,1,0,0,1,1,1,1,0,1,1,0,0,1,0,0,0,1,0,0,1,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,1,1,0,0,1,0,1,0,1,0,1,1,1,1,0,1,0,0,1,1,1,0,0,0,1,1,1,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,1,0,1,1,0,1,0,0,0,1,0,0,0,0,1,0,1,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,1,0,1,0,1,0,0,1,1,1,1,1,0,1,0,0,1,0,1,1,0,0,0,1,0,0,0,0,0,1,0,0,1,0,1,0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,0,0,1,1,1,0,0,0,1,0,1,1,1,1,1,1,0,1,1,0,0,1,1,1,1,0,0,1,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,0,0,1,1,1,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,0,0,0,1,0,1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,0,1,0,0,1,0,1,0,1,0,1,0,0,0,0,1,0,1,0,0,1,1,0,1,0,0,0,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,1,0,0,0,1,0,0,0,1,1,0,1,1,0,0,1,0,0,1,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,0,1,0,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,0,1,0,0,0,1,1,1,0,0,1,1,1,0,1,0,0,1,0,0,0,1,0,0,0,1,1,0,0,1,0,1,1,0,1,1,0,0,1,1,1,0,0,1,1,1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,1,1,1,0,0,1,0,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,1,1,1,1,1,0,0,0,1,1,0,0,0,0,0,0,1,1,1,0,1,1,0,0,1,0,0,0,0,1,0,0,1,0,1,0,1,1,0,0,0,1,0,0,0,1,0,0,1,0,1,0,0,0,0,0,0,0,1,0,1,0,1,1,1,1,0,1,1,1,0,1,1,1,1,0,0,1,1,0,1,0,1,0,0,1,1,1,0,1,1,1,0,1,1,1,1,0,0,1,1,0,1,1,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,1,1,0,0,0,1,1,1,0,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,1,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,0,0,1,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,1,0,1,0,1,1,1,0,1,0,0,0,1,1,0,1,0,1,1,0,0,0,1,0,0,1,1,1,1,0,1,0,0,0,0,1,1,1,0,1,1,0,1,1,1,1,0,0,0,0,0,1,1,0,0,0,1,0,1,1,1,1,0,1,1,0,1,1,1,0,0,1,1,1,1,0,1,1,1,0,1,0,1,1,0,0,1,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,0,0,1,0,0,1,1,0,0,1,0,0,1,0,0,1,1,1,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,1,1,1,0,1,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,0,0,1,0,1,1,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,1,1,1,0,1,1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,0,1,1,0,0,0,0,0,1,1,1,1,0,0,1,0,1,1,0,1,1,1,1,1,0,0,0,1,1,0,1,0,0,0,1,0,1,1,0,0,1,0,0,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,0,0,0,0,1,1,1,0,0,1,1,1,1,0,0,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,0,1,1,1,1,1,1,0,1,0,1,0,1,0,0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,1,0,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,1,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,0,0,0,1,0,1,1,0,1,0,1,0,0,1,1,0,0,1,0,1,0,1,1,1,0,1,0,0,1,0,1,1,0,0,1,0,0,0,0,0,1,1,0,0,0,1,0,1,1,0,0,0,1,0,0,1,1,0,0,1,0,0,1,1,0,1,1,0,0,1,1,1,1,1,0,1,1,0,0,1,0,0,0,0,1,0,0,1,1,0,1,0,1,0,0,0,1,1,1,0,0,1,1,0,1,1,0,0,1,1,1,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,1,1,1,0,0,1,0,1,0,1,0,0,1,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,1,0,1,0,1,1,1,0,0,0,0,1,0,1,1,0,1,0,0,1,1,0,0,1,1,1,1,0,0,1,0,1,0,0,1,0,1,0,1,0,0,0,1,1,1,1,0,0,0,0,1,0,1,0,1,0,0,1,0,0,1,1,1,1,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,0,0,1,0,0,1,0,0,1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,1,1,1,0,0,0,0,1,1,0,1,1,1,1,1,1,0,1,1,1,0,1,0,0,1,0,0,1,0,1,1,0,1,0,1,0,1,0,0,0,1,1,1,1,0,0,0,0,0,1,1,1,0,1,0,0,1,0,1,0,0,0,1,1,0,0,0,0,1,1,1,0,1,1,1,0,1,0,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1,1,1,0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,1,0,1,0,1,0,0,1,0,0,1,1,1,1,1,1,0,0,0,0,0,1,1,0,1,0,0,0,1,1,0,0,1,1,1,1,1,0,1,0,1,1,1,1,0,0,0,1,1,0,1,1,0,0,0,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,1,0,1,1,0,1,0,1,1,1,0,0,0,1,1,1,0,0,0,1,0,1,1,0,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,0,1,1,1,0,0,0,1,1,1,1,1,0,0,1,0,1,0,0,1,0,0,0,1,1,0,0,0,1,1,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,1,1,1,0,0,1,1,0,0,1,1,0,1,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1,0,0,0,1,1,1,1,1,0,0,1,0,1,0,0,0,1,0,1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,1,0,1,0,0,0,0,1,1,1,1,0,0,1,0,1,1,0,1,1,0,1,0,0,0,1,1,1,1,1,1,1,0,1,0,0,1,0,0,1,1,0,1,1,0,1,1,0,1,1,0,0,1,1,1,0,1,0,0,1,0,1,1,1,1,1,1,0,0,1,0,1,1,1,0,0,1,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,1,0,1,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,1,0,1,0,1,1,0,0,0,0,1,1,0,1,0,1,1,1,0,0,0,1,0,0,1,0,1,0,0,1,1,1,0,0,1,0,0,1,1,0,1,0,0,0,0,1,0,1,1,1,0,1,0,0,1,1,0,1,0,0,1,1,1,1,0,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,0,1,1,1,0,0,0,1,1,0,1,1,1,0,1,1,1,0,0,0,0,0,0,0,1,1,0,0,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0,1,0,1,1,0,1,0,0,1,0,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,0,1,1,1,0,0,1,1,0,0,0,0,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,0,0,1,1,0,1,1,1,0,1,1,0,0,0,0,0,1,1,1,0,1,1,0,0,0,1,0,1,0,1,0,0,0,1,1,1,1,1,0,0,0,1,0,0,0,0,0,1,0,1,0,1,1,1,1,1,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,0,1,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0,1,0,0,1,1,1,1,1,1,0,1,1,1,0,1,0,0,0,0,1,0,1,1,1,0,0,1,0,1,1,1,1,1,1,1,0,1,0,0,0,0,1,0,1,0,0,1,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,1,0,1,0,1,0,1,0,1,1,0,0,1,1,0,1,0,1,1,0,1,0,0,0,0,1,1,0,0,1,0,0,1,1,1,0,1,1,1,1,1,0,0,1,0,0,0,0,1,0,1,0,1,0,1,0,0,1,1,0,0,0,0,1,1,0,0,0,1,0,1,0,0,1,0,1,0,1,1,1,1,0,0,1,0,0,1,0,1,0,1,0,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,0,0,0,0,0,1,0,0,0,1,0,1,1,1,1,0,0,1,0,0,0,1,1,0,0,1,0,0,0,0,1,0,0,0,1,0,1,1,0,1,1,1,0,1,0,0,0,1,1,0,0,1,1,0,1,1,0,0,0,0,1,0,0,1,1,1,1,0,0,0,0,1,1,0,1,1,1,0,1,1,1,0,1,0,0,1,0,0,0,0,0,0,1,1,1,0,0,1,0,1,1,0,1,1,0,1,1,1,0,0,1,0,0,1,0,0,1,0,0,1,0,0,1,1,1,1,0,1,1,0,0,1,1,0,0,1,0,1,1,0,0,1,0,1,1,0,0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,0,0,1,1,0,1,1,0,0,1,1,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,0,1,0,0,0,1,1,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,1,1,0,1,1,0,1,1,0,0,1,0,0,0,1,1,1,0,1,0,0,1,1,1,1,1,0,1,0,0,0,1,0,0,1,0,1,0,1,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,0,0,1,1,1,1,0,1,1,0,1,0,0,0,1,1,0,0,0,1,0,1,1,1,0,0,1,0,1,1,0,1,1,0,0,1,1,1,0,0,1,0,0,0,1,0,1,1,0,0,0,1,0,1,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,0,0,1,1,0,0,0,0,1,1,0,1,0,1,1,0,1,0,0,1,1,1,1,0,0,0,0,1,1,1,1,1,0,0,1,1,0,0,0,1,1,0,1,0,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,0,1,1,0,1,1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,1,1,0,1,1,0,1,1,1,1,0,1,1,0,0,0,1,0,0,0,0,1,0,0,1,1,1,0,0,1,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,0,0,1,0,0,1,1,0,1,0,1,0,0,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,1,1,0,1,0,1,1,1,1,0,1,1,1,0,1,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,0,0,1,1,0,0,0,1,1,1,1,0,1,1,1,0,0,1,1,1,1,0,1,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0,1,1,1,1,1,1,0,1,1,0,1,0,0,1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,0,1,0,1,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,1,1,1,1,1,0,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0,1,1,0,1,1,1,1,0,1,1,0,0,0,0,1,0,0,1,1,0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,1,1,0,0,0,0,1,1,0,0,0,0,0,1,0,1,1,0,1,1,1,0,1,0,1,0,1,0,1,0,0,1,0,1,1,0,0,1,1,1,1,1,0,0,0,0,1,0,1,0,0,1,1,0,0,0,1,0,0,0,1,1,1,0,0,1,0,1,1,0,0,1,1,1,1,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,0,1,0,1,0,0,0,1,1,0,0,0,1,1,1,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,1,0,0,0,0,0,0,1,1,1,1,0,1,1,0,0,1,1,0,1,0,0,1,1,1,0,1,0,0,1,0,0,0,1,1,0,0,1,0,1,0,0,1,1,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,0,1,0,1,0,1,0,0,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,0,0,0,1,1,1,0,0,1,0,0,1,0,1,1,1,0,1,0,0,1,1,1,0,1,1,1,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,1,1,1,1,0,0,1,1,0,1,1,1,0,1,0,0,0,1,0,1,0,0,1,0,0,0,1,1,0,1,0,1,0,0,1,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,1,0,0,1,0,1,0,0,0,1,0,0,1,1,1,0,0,0,1,1,1,1,1,1,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,0,0,1,1,0,0,0,1,1,0,0,1,0,0,1,1,1,0,1,0,0,0,1,1,1,0,0,0,0,0,1,0,1,0,0,1,1,0,0,0,1,0,0,1,1,1,1,0,1,1,0,1,1,1,0,0,0,0,0,1,1,1,1,0,1,1,0,1,1,0,0,1,1,0,0,0,0,1,0,0,0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,1,1,1,0,0,1,0,0,1,1,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0,1,0,0,1,1,1,0,0,0,0,1,1,0,0,0,1,1,0,0,1,0,1,0,1,1,0,0,0,1,1,0,1,0,1,0,0,1,0,1,0,0,1,0,0,1,0,0,0,1,1,1,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1,1,0,0,1,1,0,0,0,1,0,0,1,0,0,1,0,1,0,0,1,0,1,1,0,1,0,1,0,1,0,0,0,1,1,0,0,1,1,1,1,0,0,0,0,1,0,1,0,0,0,0,1,0,0,1,1,0,0,0,0,0,1,1,1,0,0,1,1,0,0,0,1,1,0,1,0,1,1,0,1,1,0,1,1,0,1,0,0,0,0,0,1,1,1,0,0,1,0,0,0,1,1,0,1,1,1,0,0,0,1,0,1,1,0,1,0,0,0,1,1,1,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,0,1,0,1,0,0,0,0,0,1,1,0,0,0,0,0,0,1,1,0,1,0,0,1,1,0,1,1,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,1,0,0,0,1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,0,1,0,0,1,1,0,0,1,1,0,1,1,0,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,0,1,0,0,1,0,1,1,0,1,0,0,1,1,1,0,1,0,0,1,0,0,0,1,1,1,0,0,0,1,1,0,1,0,1,0,1,1,1,0,0,0,0,1,0,0,0,1,0,1,0,0,1,1,1,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,1,1,0,1,1,0,0,1,0,1,1,1,0,1,0,1,1,0,0,0,0,1,0,1,0,0,1,1,1,0,0,0,1,1,0,1,1,1,0,0,1,0,0,0,1,1,1,0,1,1,1,1,0,0,0,0,0,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,0,1,0,0,1,0,0,1,0,1,0,1,0,0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,1,0,0,1,1,0,1,1,1,0,0,1,0,1,1,0,0,1,0,0,0,1,1,0,1,0,1,0,1,1,1,1,0,0,0,0,1,0,0,1,1,0,0,1,1,0,0,0,0,1,1,0,0,0,1,0,0,1,0,1,0,1,1,0,1,1,1,1,1,0,0,0,0,1,1,0,0,1,1,0,1,1,0,1,1,1,0,0,0,1,1,1,0,1,1,0,0,0,0,1,1,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,1,0,1,1,0,0,1,0,1,0,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,1,0,1,1,0,0,0,1,0,1,0,0,1,1,1,0,1,0,1,0,0,0,1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0,1,0,0,1,0,1,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,1,0,0,1,0,0,1,1,0,1,0,1,0,0,1,0,1,1,0,1,1,0,0,0,1,1,0,0,1,0,0,1,1,1,0,1,1,0,1,0,1,0,0,0,0,1,0,1,1,0,1,1,0,1,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,0,1,0,1,1,0,1,1,0,0,1,0,1,1,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1,1,0,1,1,1,0,1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,1,0,0,0,1,1,0,0,1,0,1,0,0,0,1,1,1,1,0,0,0,1,1,0,1,0,1,1,0,1,0,1,0,0,1,0,1,0,0,0,1,1,1,1,1,1,0,0,1,1,0,0,1,0,1,0,1,1,1,1,0,0,1,0,0,1,0,1,1,1,0,1,1,1,0,0,1,1,1,1,0,1,1,1,0,0,1,0,1,0,0,0,1,0,1,1,0,0,0,0,0,0,1,1,1,0,0,1,1,0,0,1,0,0,0,1,0,1,1,0,0,0,0,1,1,0,1,1,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,0,0,0,1,0,1,1,0,1,1,1,1,0,1,0,1,0,1,1,1,0,0,1,1,0,0,1,1,0,1,1,0,1,1,1,0,1,0,0,0,0,0,0,0,1,0,0,1,1,1,0,0,0,0,1,0,1,1,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,1,1,1,1,0,0,0,0,0,1,1,0,1,0,1,1,1,0,0,0,0,1,0,1,1,0,1,1,1,1,0,0,1,1,0,1,1,1,1,0,0,1,1,1,0,0,1,0,0,0,0,1,1,1,0,0,0,1,0,1,0,0,1,1,0,1,0,0,0,1,0,0,0,0,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,0,1,1,0,0,1,0,0,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,0,0,0,1,1,1,0,0,0,1,0,1,1,0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,1,1,1,0,1,0,1,1,1,1,0,1,0,1,0,1,1,1,0,0,1,0,1,1,0,0,0,0,1,0,1,0,1,0,1,0,0,0,0,1,1,0,1,0,1,1,1,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,0,1,0,0,1,1,1,0,0,1,0,1,1,0,0,1,1,1,1,1,1,0,0,1,1,0,0,0,1,0,1,1,0,0,0,1,0,1,1,1,0,0,1,1,0,1,0,0,0,0,1,1,0,1,1,0,1,0,0,0,0,1,1,1,1,1,0,1,0,1,0,1,1,0,1,1,1,1,1,0,1,0,0,0,1,0,0,0,1,0,1,1,0,1,1,0,1,1,0,0,0,1,1,0,1,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,1,0,0,1,0,1,1,1,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,1,0,0,0,1,0,0,0,1,1,0,1,0,1,0,1,1,1,1,1,1,0,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,1,0,1,0,1,1,1,1,0,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,1,1,0,1,1,0,1,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,0,0,1,1,1,0,1,1,1,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,0,1,1,0,1,1,0,0,0,0,1,1,1,0,1,1,0,0,0,1,1,1,0,0,1,0,1,0,1,1,0,1,0,0,1,1,1,0,0,1,0,1,1,1,0,0,1,1,1,0,1,1,0,1,1,0,0,1,1,1,0,0,0,0,1,1,1,1,0,0,1,0,0,1,1,1,1,0,0,1,1,1,0,0,1,0,0,0,0,0,1,0,1,1,0,1,0,1,0,1,1,1,1,0,0,1,1,1,1,1,0,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,1,1,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0,0,0,0,1,1,1,0,0,1,1,1,0,1,1,0,1,1,0,1,0,0,1,1,1,0,1,1,0,0,1,0,1,1,0,1,0,0,1,0,0,0,1,1,1,1,1,0,1,1,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1,1,0,0,1,0,0,0,0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0,0,1,0,0,0,1,0,1,1,0,1,0,0,0,0,1,1,1,0,0,1,1,0,1,0,1,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,0,0,1,0,1,0,1,0,1,1,1,1,1,0,1,1,0,1,0,0,1,1,0,1,1,1,1,1,0,1,1,0,1,0,0,1,0,0,0,1,0,1,0,1,1,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,0,1,1,0,0,0,0,1,1,0,1,1,1,0,1,0,0,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,1,1,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,1,0,0,1,0,0,1,1,0,0,0,0,1,0,0,1,1,1,0,0,1,0,1,1,1,1,1,1,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,0,1,0,0,0,0,0,1,1,1,0,1,0,1,1,0,0,1,0,1,0,0,1,1,0,0,1,0,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,1,1,0,0,0,1,1,0,0,1,0,0,1,0,1,1,1,0,1,0,1,0,0,0,0,0,1,1,0,1,1,1,1,1,0,0,1,0,0,0,1,0,1,0,1,1,0,1,1,0,0,1,0,1,1,1,0,0,0,1,0,1,1,0,0,1,0,0,1,1,0,1,0,1,1,1,1,0,0,0,1,0,0,1,0,0,1,0,1,1,1,0,1,0,1,0,0,0,1,1,1,0,0,1,1,0,1,0,1,1,1,0,0,1,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,0,0,1,1,1,0,1,1,0,0,1,0,1,1,1,1,1,0,1,0,0,0,1,0,0,0,1,0,0,1,1,1,0,1,1,1,1,1,1,0,0,1,1,0,1,1,0,1,0,0,0,1,0,0,0,1,0,1,1,0,1,1,0,0,0,0,1,0,0,1,0,1,1,0,1,1,0,0,1,1,1,1,1,0,1,1,0,1,0,1,0,0,1,1,0,1,0,0,1,1,0,1,0,1,0,0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,1,0,0,1,1,0,0,1,1,1,1,0,0,1,1,0,0,1,1,1,0,1,1,0,1,0,1,0,1,1,1,1,0,0,1,1,0,1,1,0,1,0,0,0,1,1,0,1,1,0,1,1,0,0,1,0,1,1,0,0,1,0,0,1,1,0,0,1,1,0,0,1,0,1,0,1,0,0,1,1,0,0,0,1,1,0,0,1,1,0,1,0,0,0,1,0,0,0,0,1,1,1,0,0,1,1,1,0,0,0,0,1,0,0,1,0,1,1,0,0,0,0,1,1,0,1,0,1,0,1,1,0,0,0,0,1,0,0,1,0,1,0,0,0,0,0,0,1,0,1,1,0,0,1,1,0,1,1,1,1,1,0,0,0,1,1,0,1,1,0,0,0,1,0,1,1,1,0,0,1,0,0,1,1,1,0,1,1,1,0,1,1,0,0,1,0,1,1,1,1,1,1,0,1,1,0,1,0,1,1,0,0,0,1,1,0,0,1,0,1,1,0,0,0,1,1,1,0,1,0,0,0,1,0,1,1,1,1,0,0,0,1,0,1,1,0,0,1,0,0,0,1,1,0,0,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,1,0,0,1,1,1,1,1,1,0,1,1,0,0,1,0,0,1,0,1,1,0,0,1,0,1,0,0,0,1,0,1,1,1,0,0,1,1,1,0,0,1,1,0,0,1,1,1,1,1,0,0,0,1,0,1,1,0,0,1,1,0,1,1,0,1,0,0,1,1,1,1,1,0,1,0,1,0,1,1,1,0,0,0,1,0,0,0,1,1,0,0,1,1,0,1,1,0,0,1,0,0,0,1,0,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,0,1,1,0,1,0,0,0,1,1,0,0,1,1,1,1,0,1,1,0,0,1,0,0,1,0,0,0,1,0,0,0,1,0,0,1,0,0,1,1,1,0,1]
}