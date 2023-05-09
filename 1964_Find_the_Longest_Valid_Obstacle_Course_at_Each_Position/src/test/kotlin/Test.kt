import kotlin.test.Test
import kotlin.test.assertContentEquals

class Test {
    private val solution = Solution()

    @Test
    fun test01(){
        val obstacles = intArrayOf(1,2,3,2)
        val expectation = intArrayOf(1,2,3,3)
        assertContentEquals(expectation, solution.longestObstacleCourseAtEachPosition(obstacles))
    }

    @Test
    fun test02(){
        val obstacles = intArrayOf(2,2,1)
        val expectation = intArrayOf(1,2,1)
        assertContentEquals(expectation, solution.longestObstacleCourseAtEachPosition(obstacles))
    }

    @Test
    fun test03(){
        val obstacles = intArrayOf(3,1,5,6,4,2)
        val expectation = intArrayOf(1,1,2,3,2,2)
        assertContentEquals(expectation, solution.longestObstacleCourseAtEachPosition(obstacles))
    }

    @Test
    fun test04(){
        val obstacles = intArrayOf(5,1,5,5,1,3,4,5,1,4)
        val expectation = intArrayOf(1,1,2,3,2,3,4,5,3,5)
        assertContentEquals(expectation, solution.longestObstacleCourseAtEachPosition(obstacles))
    }
}