import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val gas = intArrayOf(1,2,3,4,5)
        val cost = intArrayOf(3,4,5,1,2)
        val expectation = 3
        assertEquals(expectation, solution.canCompleteCircuit(gas, cost))
    }

    @Test
    fun test02(){
        val gas = intArrayOf(2,3,4)
        val cost = intArrayOf(3,4,3)
        val expectation = -1
        assertEquals(expectation, solution.canCompleteCircuit(gas, cost))
    }

    @Test
    fun test03(){
        val gas = intArrayOf(3,3,4)
        val cost = intArrayOf(3,4,4)
        val expectation = -1
        assertEquals(expectation, solution.canCompleteCircuit(gas, cost))
    }

    @Test
    fun test04(){
        val gas = intArrayOf(5,1,2,3,4)
        val cost = intArrayOf(4,4,1,5,1)
        val expectation = 4
        assertEquals(expectation, solution.canCompleteCircuit(gas, cost))
    }

    @Test
    fun test05(){
        val gas = intArrayOf(4,5,2,6,5,3)
        val cost = intArrayOf(3,2,7,3,2,9)
        val expectation = -1
        assertEquals(expectation, solution.canCompleteCircuit(gas, cost))
    }
}