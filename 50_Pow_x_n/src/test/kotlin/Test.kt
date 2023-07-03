import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        println("-3%2 = ${-3%2}")

        val x= 2.00000
        val n = -2147483648
        val output = 0.0

        assertEquals(output, solution.myPow(x,n))
    }
}