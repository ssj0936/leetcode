import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @Before
    fun setup(){
        solution = SolutionDP()
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

    @Test
    fun test04(){
        val startTime = intArrayOf(4,2,4,8,2)
        val endTime = intArrayOf(5,5,5,10,8)
        val profit = intArrayOf(1,2,8,10,4)
        val expectation = 18
        assertEquals(expectation, solution.jobScheduling(startTime, endTime, profit))
    }

    @Test
    fun test05(){
        val startTime = intArrayOf(6,15,7,11,1,3,16,2)
        val endTime = intArrayOf(19,18,19,16,10,8,19,8)
        val profit = intArrayOf(2,9,1,19,5,7,3,19)
        val expectation = 41
        assertEquals(expectation, solution.jobScheduling(startTime, endTime, profit))
    }

    @Test
    fun test06(){
        val startTime = intArrayOf(4,3,1,2,4,8,3,3,3,9)
        val endTime = intArrayOf(5,6,3,5,9,9,8,5,7,10)
        val profit = intArrayOf(9,9,5,12,10,11,10,4,14,7)
        val expectation = 37
        assertEquals(expectation, solution.jobScheduling(startTime, endTime, profit))
    }

}