import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Solution

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = arrayOf(
            intArrayOf(1,3),
            intArrayOf(2,6),
            intArrayOf(8,10),
            intArrayOf(15,18)
        )
        val result = solution.mergeV2(input)
        assertEquals(
            arrayOf(intArrayOf(1,6),intArrayOf(8,10),intArrayOf(15,18)).contentDeepToString(),
            result.contentDeepToString()
        )
    }

    @Test
    fun test02(){
        val input = arrayOf(
            intArrayOf(1,4),
            intArrayOf(4,5)
        )
        val result = solution.mergeV2(input)
        assertEquals(
            arrayOf(intArrayOf(1,5)).contentDeepToString(),
            result.contentDeepToString()
        )
    }

    @Test
    fun test03(){
        val input = arrayOf(
            intArrayOf(1,4),
            intArrayOf(1,5)
        )
        val result = solution.mergeV2(input)
        assertEquals(
            arrayOf(intArrayOf(1,5)).contentDeepToString(),
            result.contentDeepToString()
        )
    }

    @Test
    fun test04(){
        val input = arrayOf(
            intArrayOf(1,4),
            intArrayOf(0,0)
        )
        val result = solution.mergeV2(input)
        assertEquals(
            arrayOf(intArrayOf(0,0),intArrayOf(1,4)).contentDeepToString(),
            result.contentDeepToString()
        )
    }
}