import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Sol

    @BeforeTest
    fun setup(){
        solution = SolutionBest()
    }

    @Test
    fun test01(){
        val input = listOf(
            listOf(1,100,3),
            listOf(7,8,9),
        )

        val k = 2

        val expectation = 101
        assertEquals(expectation, solution.maxValueOfCoins(input,k))
    }

    @Test
    fun test02(){
        val input = listOf(
            listOf(100),
            listOf(100),
            listOf(100),
            listOf(100),
            listOf(100),
            listOf(100),
            listOf(1,1,1,1,1,1,700)
        )

        val k = 7

        val expectation = 706
        assertEquals(expectation, solution.maxValueOfCoins(input,k))
    }

    @Test
    fun test03(){
        val input = listOf(
            listOf(37,88),
            listOf(51,64,65,20,95,30,26),
            listOf(9,62,20),
            listOf(44)
        )

        val k = 9

        val expectation = 494
        assertEquals(expectation, solution.maxValueOfCoins(input,k))
    }
}