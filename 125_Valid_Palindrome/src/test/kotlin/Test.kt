import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution:Sol

    @BeforeEach
    fun setup(){
        solution = Solution01()
    }

    @Test
    fun test01(){
        assertEquals(
            expected = true,
            solution.isPalindrome("A man, a plan, a canal: Panama")
        )
    }

    @Test
    fun test02(){
        assertEquals(
            expected = false,
            solution.isPalindrome("race a car")
        )
    }

    @Test
    fun test03(){
        assertEquals(
            expected = true,
            solution.isPalindrome(" ")
        )
    }

    @Test
    fun test04(){
        assertEquals(
            expected = true,
            solution.isPalindrome("abba")
        )
    }


}