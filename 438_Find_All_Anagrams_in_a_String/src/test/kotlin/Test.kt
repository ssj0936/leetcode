import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Sol

    @Before
    fun setup(){
        solution = SolutionSlidingWindow()
    }

    @Test
    fun test01(){
        val s = "cbaebabacd"
        val p = "abc"
        val output = listOf<Int>(0,6)
        assertEquals(output, solution.findAnagrams(s, p))
    }

    @Test
    fun test02(){
        val s = "abab"
        val p = "ab"
        val output = listOf<Int>(0,1,2)
        assertEquals(output, solution.findAnagrams(s, p))
    }
}