import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol

    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = arrayOf(
            intArrayOf(1,2),intArrayOf(3,5),intArrayOf(6,7),intArrayOf(8,10),intArrayOf(12,16)
        )

        assertContentEquals(
            solution.insert(input, intArrayOf(4,8)),
            arrayOf(
                intArrayOf(1,2),intArrayOf(3,10),intArrayOf(12,16)
            )
        )
//        assertEquals(solution.insert(input, intArrayOf(4,8)),
//            arrayOf(
//                intArrayOf(1,2),intArrayOf(3,10),intArrayOf(12,16)
//            )
//        )
    }
}