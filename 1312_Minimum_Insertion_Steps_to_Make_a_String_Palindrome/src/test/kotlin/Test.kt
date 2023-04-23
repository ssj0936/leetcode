import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = Solution03()
    }

    @Test
    fun test01(){
        val s = "zzazz"
        val expectation = 0
        assertEquals(expectation, solution.minInsertions(s))
    }

    @Test
    fun test02(){
        val s = "mbadm"
        val expectation = 2
        assertEquals(expectation, solution.minInsertions(s))
    }

    @Test
    fun test03(){
        val s = "leetcode"
        val expectation = 5
        assertEquals(expectation, solution.minInsertions(s))
    }

    @Test
    fun test04(){
        val s = "zjveiiwvc"
        val expectation = 5
        assertEquals(expectation, solution.minInsertions(s))
    }
}