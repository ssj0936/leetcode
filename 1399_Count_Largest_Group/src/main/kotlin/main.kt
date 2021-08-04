/*
1399. Count Largest Group(Easy)

Given an integer n. Each number from 1 to n is grouped according to the sum of its digits.
Return how many groups have the largest size.



Example 1:
Input: n = 13
Output: 4
Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9]. There are 4 groups with largest size.

Example 2:
Input: n = 2
Output: 2
Explanation: There are 2 groups [1], [2] of size 1.

Example 3:
Input: n = 15
Output: 6

Example 4:
Input: n = 24
Output: 5

Constraints:

1 <= n <= 10^4

 */

/*
題目有點拗口，大意就是說，給定一個n，把從1-n的所有數字分組，依照個別digit的數字加總，像是1,10,100會在一組，6,15,24,33會在一組
return擁有最多個數的組別數
 */
class Solution {
    fun countLargestGroup(n: Int): Int {
        val arr = IntArray(37){0}
        var max = Int.MIN_VALUE
        for(i in 1 .. n){
            arr[getDigitSum(i)] += 1
            max = if(arr[getDigitSum(i)] > max) arr[getDigitSum(i)] else max
        }

        var count = 0
        for(i in arr){
            if(i == max) ++count
        }
        return count
    }

    private fun getDigitSum(n:Int):Int{
        var tmpN = n
        var sum = 0
        while (tmpN/10 != 0){
            sum += (tmpN % 10)
            tmpN /= 10
        }
        sum += tmpN

        return sum
    }
}
fun main(args: Array<String>) {
    val input = 2
    val result = Solution().countLargestGroup(input)
    println(result)
}