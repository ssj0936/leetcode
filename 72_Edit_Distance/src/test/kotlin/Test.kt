import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = Solution2()
    }

    @Test
    fun test01(){
        val word1 = "horse"
        val word2 = "ros"
        val expectation = 3

        val output = solution.minDistance(word1, word2)
        assertEquals(expectation, output)
    }

    @Test
    fun test02(){
        val word1 = "intention"
        val word2 = "execution"
        val expectation = 5

        val output = solution.minDistance(word1, word2)
        assertEquals(expectation, output)
    }

    @Test
    fun test03(){
        val word1 = ""
        val word2 = "a"
        val expectation = 1

        val output = solution.minDistance(word1, word2)
        assertEquals(expectation, output)
    }

    @Test
    fun test04(){
        val word1 = "a"
        val word2 = "ab"
        val expectation = 1

        val output = solution.minDistance(word1, word2)
        assertEquals(expectation, output)
    }
}