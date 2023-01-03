import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class Test {

    private lateinit var solution:Sol
    @Before
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val input = arrayOf("eat","tea","tan","ate","nat","bat")
        assertEquals(solution.groupAnagrams(input),
            listOf(
                listOf("bat"),listOf("nat","tan"),listOf("ate","eat","tea")
            )
        )
    }

    @Test
    fun test02(){
        val input = arrayOf("")
        assertEquals(solution.groupAnagrams(input),
            listOf(listOf(""))
        )
    }

    @Test
    fun test03(){
        val input = arrayOf("a")
        assertEquals(solution.groupAnagrams(input),
            listOf(listOf("a"))
        )
    }
}