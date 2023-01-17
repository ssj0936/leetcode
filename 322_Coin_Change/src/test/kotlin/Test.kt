import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Test {
    private lateinit var solution: Sol

    @BeforeEach
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val coins = intArrayOf(1,2,5)
        val amount = 11
        val actual = solution.coinChange(coins, amount)
        val expectation = 3
        assertEquals(expectation, actual)
    }

    @Test
    fun test02(){
        val coins = intArrayOf(2)
        val amount = 3
        val actual = solution.coinChange(coins, amount)
        val expectation = -1
        assertEquals(expectation, actual)
    }

    @Test
    fun test03(){
        val coins = intArrayOf(1)
        val amount = 0
        val actual = solution.coinChange(coins, amount)
        val expectation = 0
        assertEquals(expectation, actual)
    }
}