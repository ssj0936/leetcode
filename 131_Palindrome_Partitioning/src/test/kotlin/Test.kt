import kotlin.test.BeforeTest
import kotlin.test.Test

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = SolutionBest()
    }

    @Test
    fun test01(){
        val s = "aab"
        val result = solution.partition(s)
        println(result)
    }
}