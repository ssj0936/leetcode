import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = arrayOf(
            intArrayOf(2,1,3),
            intArrayOf(6,1,4)
        )

        val expectation = 2

        assertEquals(expectation, solution.maximumDetonation(input))
    }

    @Test
    fun test02(){
        val input = arrayOf(
            intArrayOf(1,1,5),
            intArrayOf(10,10,5)
        )

        val expectation = 1

        assertEquals(expectation, solution.maximumDetonation(input))
    }

    @Test
    fun test03(){
        val input = arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(2,3,1),
            intArrayOf(3,4,2),
            intArrayOf(4,5,3),
            intArrayOf(5,6,4),
        )

        val expectation = 5

        assertEquals(expectation, solution.maximumDetonation(input))
    }

    @Test
    fun test04(){
        val input = arrayOf(
            intArrayOf(1,1,100000),
            intArrayOf(100000,100000,1),
        )

        val expectation = 1

        assertEquals(expectation, solution.maximumDetonation(input))
    }

    @Test
    fun test05(){
        val input = arrayOf(
            intArrayOf(37207,2653,5261),
            intArrayOf(40784,59523,20635),
            intArrayOf(16390,1426,39102),
            intArrayOf(42236,12,96855),
            intArrayOf(72839,62027,61667),
            intArrayOf(60691,58191,48447),
            intArrayOf(42932,46579,41248),
            intArrayOf(35868,43119,6870),
            intArrayOf(41693,98905,17374),
            intArrayOf(43441,1266,41621)
        )

        val expectation = 10

        assertEquals(expectation, solution.maximumDetonation(input))
    }


}