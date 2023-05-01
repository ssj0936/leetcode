import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution: Sol

    @BeforeTest
    fun setup(){
        solution = SolutionDFS()
    }

    @Test
    fun test01(){
        val strs = arrayOf("tars","rats","arts","star")
        val expectation = 2

        assertEquals(expectation, solution.numSimilarGroups(strs))
    }

    @Test
    fun test02(){
        val strs = arrayOf("omv","ovm")
        val expectation = 1

        assertEquals(expectation, solution.numSimilarGroups(strs))
    }

    @Test
    fun test03(){
        val strs = arrayOf("kccomwcgcs","socgcmcwkc","sgckwcmcoc","coswcmcgkc","cowkccmsgc","cosgmccwkc","sgmkwcccoc","coswmccgkc","kowcccmsgc","kgcomwcccs")
        val expectation = 5

        assertEquals(expectation, solution.numSimilarGroups(strs))
    }


}