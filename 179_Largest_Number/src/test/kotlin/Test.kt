import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(10,2)
        val output = "210"
        assertEquals(output, solution.largestNumber(input))
    }

    @Test
    fun test02(){
        val input = intArrayOf(3,30,34,5,9)
        val output = "9534330"
        assertEquals(output, solution.largestNumber(input))
    }

    @Test
    fun test03(){
        val input = intArrayOf(7787,778)
        val output = "7787787"
        assertEquals(output, solution.largestNumber(input))
    }

    @Test
    fun test04(){
        val input = intArrayOf(111311,1113)
        val output = "1113111311"
        assertEquals(output, solution.largestNumber(input))
    }
}