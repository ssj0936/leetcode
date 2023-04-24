import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = intArrayOf(-1,0,3,5,9,12)
        val target = 9
        val output = 4
        assertEquals(output, solution.search(input,target))
    }

    @Test
    fun test02(){
        val input = intArrayOf(-1,0,3,5,9,12)
        val target = 2
        val output = -1
        assertEquals(output, solution.search(input,target))
    }

    @Test
    fun test03(){
        val input = intArrayOf(-1,0,3,5,9,12)
        val target = 13
        val output = -1
        assertEquals(output, solution.search(input,target))
    }
}