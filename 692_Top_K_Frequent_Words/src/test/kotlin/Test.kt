import kotlin.test.Test
import kotlin.test.assertContentEquals

class Test {
    val solution = Solution()

    @Test
    fun test01(){
        val input = arrayOf("i","love","leetcode","i","love","coding")
        val k = 2

        val expectation = listOf("i","love")
        assertContentEquals(expectation, solution.topKFrequent(input, k))
    }

    @Test
    fun test02(){
        val input = arrayOf("the","day","is","sunny","the","the","the","sunny","is","is")
        val k = 4

        val expectation = listOf("the","is","sunny","day")
        assertContentEquals(expectation, solution.topKFrequent(input, k))
    }

    @Test
    fun test03(){
        val input = arrayOf("i","love","leetcode","i","love","coding")
        val k = 1

        val expectation = listOf("i")
        assertContentEquals(expectation, solution.topKFrequent(input, k))
    }


}