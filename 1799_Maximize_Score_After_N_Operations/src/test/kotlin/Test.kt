import kotlin.test.Test

class Test {
    val solution = Solution()
//    171651,546244,880754,412358
    @Test
    fun test01(){
        val num1 = 546244
        val num2 = 412358
        println("num1:$num1, num2:$num2, gcd:${solution.getGCD(num1, num2)}")
    }
}