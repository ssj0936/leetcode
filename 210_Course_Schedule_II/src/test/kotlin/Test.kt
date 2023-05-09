import kotlin.test.Test

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val numCourses = 2
        val prerequisites = arrayOf(intArrayOf(1,0))
        val result = solution.findOrder(numCourses, prerequisites)
        println(result.contentToString())
    }

    @Test
    fun test02(){
        val numCourses = 4
        val prerequisites = arrayOf(
            intArrayOf(1,0),
            intArrayOf(2,0),
            intArrayOf(3,1),
            intArrayOf(3,2),
        )
        val result = solution.findOrder(numCourses, prerequisites)
        println(result.contentToString())
    }

    @Test
    fun test03(){
        val numCourses = 1
        val prerequisites = arrayOf<IntArray>()
        val result = solution.findOrder(numCourses, prerequisites)
        println(result.contentToString())
    }
}