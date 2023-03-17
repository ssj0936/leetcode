import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val startTime = intArrayOf(1,2,3,3)
        val endTime = intArrayOf(3,4,5,6)
        val profit = intArrayOf(50,10,40,70)
        val expectation = 120
        assertEquals(expectation, solution.jobScheduling(startTime, endTime, profit))
    }

    @Test
    fun test02(){
        val startTime = intArrayOf(1,2,3,4,6)
        val endTime = intArrayOf(3,5,10,6,9)
        val profit = intArrayOf(20,20,100,70,60)
        val expectation = 150
        assertEquals(expectation, solution.jobScheduling(startTime, endTime, profit))
    }

    @Test
    fun test03(){
        val startTime = intArrayOf(1,1,1)
        val endTime = intArrayOf(2,3,4)
        val profit = intArrayOf(5,6,4)
        val expectation = 6
        assertEquals(expectation, solution.jobScheduling(startTime, endTime, profit))
    }
}