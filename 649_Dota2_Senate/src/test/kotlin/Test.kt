import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val senate = "RD"
        val expectation = solution.R
        assertEquals(expectation, solution.predictPartyVictory(senate))
    }

    @Test
    fun test02(){
        val senate = "RDD"
        val expectation = solution.D
        assertEquals(expectation, solution.predictPartyVictory(senate))
    }

    @Test
    fun test03(){
        val senate = "D"
        val expectation = solution.D
        assertEquals(expectation, solution.predictPartyVictory(senate))
    }

    @Test
    fun test04(){
        val senate = "DDRRR"
        val expectation = solution.D
        assertEquals(expectation, solution.predictPartyVictory(senate))
    }

    @Test
    fun test05(){
        val senate = "DRDRR"
        val expectation = solution.D
        assertEquals(expectation, solution.predictPartyVictory(senate))
    }
}