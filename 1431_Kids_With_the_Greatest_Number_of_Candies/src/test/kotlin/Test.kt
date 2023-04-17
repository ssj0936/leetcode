import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Solution

    @BeforeTest
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val candies = intArrayOf(2,3,5,1,3)
        val extra = 3
        val expectation = listOf(true,true,true,false,true)
        assertContentEquals(expectation, solution.kidsWithCandies(candies, extra))
    }

    @Test
    fun test02(){
        val candies = intArrayOf(4,2,1,1,2)
        val extra = 1
        val expectation = listOf(true,false,false,false,false)
        assertContentEquals(expectation, solution.kidsWithCandies(candies, extra))
    }

    @Test
    fun test03(){
        val candies = intArrayOf(12,1,12)
        val extra = 10
        val expectation = listOf(true,false,true)
        assertContentEquals(expectation, solution.kidsWithCandies(candies, extra))
    }
}