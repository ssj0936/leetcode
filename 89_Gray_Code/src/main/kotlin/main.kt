/*
89. Gray Code(Medium)

An n-bit gray code sequence is a sequence of 2n integers where:
Every integer is in the inclusive range [0, 2n - 1],
The first integer is 0,
An integer appears no more than once in the sequence,
The binary representation of every pair of adjacent integers differs by exactly one bit, and
The binary representation of the first and last integers differs by exactly one bit.
Given an integer n, return any valid n-bit gray code sequence.

Example 1:
Input: n = 2
Output: [0,1,3,2]
Explanation:
The binary representation of [0,1,3,2] is [00,01,11,10].
- 00 and 01 differ by one bit
- 01 and 11 differ by one bit
- 11 and 10 differ by one bit
- 10 and 00 differ by one bit
[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
- 00 and 10 differ by one bit
- 10 and 11 differ by one bit
- 11 and 01 differ by one bit
- 01 and 00 differ by one bit

Example 2:
Input: n = 1
Output: [0,1]

Constraints:

1 <= n <= 16
 */

class Solution {
    /*直觀想法：
    n=1 是[0, 1]
    n=2 是[0, 1, 11, 10]
    n=3 是[0, 1, 11, 10, 110, 111, 101, 100]
    可以觀察到其中一半 是上一回合的output，而另一半 則是上一回合的output倒序輸出 並在前面補上1

    "在前面補上1"這個動作，binary的行為裡相當於是加上2^n，所以解法二可以直接用decimal來直接做看看
     */
    fun grayCode(n: Int): List<Int> {

        if(n == 1) return listOf(0,1)
        if(n == 2) return listOf(0,1,3,2)
        val resultSize:Int = Math.pow(2.0,n.toDouble()).toInt()-1
        val result = mutableListOf("0","1")
        for(i in 2 .. n){
            val currentResultSize = result.size
            for(reverseIndex in currentResultSize-1 downTo 0){
//                println(result[reverseIndex])
                val zeroDiff = i - (result[reverseIndex].length +1)

                result.add("1${"0".repeat(zeroDiff)}${result[reverseIndex]}")
            }
        }
//        println(result)
        return result.map {binaryStringToInt(it) }
    }

    fun grayCode2(n: Int): List<Int> {
        val result = mutableListOf(0,1)

        if(n == 1) return result
        var powOf2 = 2
        for(i in 2 .. n){
            val currentResultSize = result.size
            for(reverseIndex in currentResultSize-1 downTo 0){
//                println(result[reverseIndex])

                result.add(result[reverseIndex] + powOf2)
            }
            powOf2 *=2
        }
//        println(result)
        return result
    }

    private fun binaryStringToInt(binary:String):Int{
        var time = 0.0
        var sum = 0.0
        for(digit in binary.length-1 downTo 0){
            val d = binary[digit]
            if(d != '0') sum+=Math.pow(2.0,time)

            time +=1
        }
        return sum.toInt()
    }
}

fun main(args: Array<String>) {
    val result = Solution().grayCode2(3)
    println(result)
}