import kotlin.test.Test

class Test {
    val solution = SolutionOri()

    @Test
    fun test01(){
        val input = arrayOf(
            intArrayOf(1,2,2,3,5),
            intArrayOf(3,2,3,4,4),
            intArrayOf(2,4,5,3,1),
            intArrayOf(6,7,1,4,5),
            intArrayOf(5,1,1,2,4),
        )

        val result = solution.pacificAtlantic(input)
        println(result)
    }

    @Test
    fun test02(){
        val input = arrayOf(
            intArrayOf(1)
        )
        val result = solution.pacificAtlantic(input)
        println(result)

    }
}