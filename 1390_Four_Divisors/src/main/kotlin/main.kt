/*
1390. Four Divisors(Medium)

Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.
If there is no such integer in the array, return 0.

Example 1:
Input: nums = [21,4,7]
Output: 32

Explanation:
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.


Constraints:

1 <= nums.length <= 10^4
1 <= nums[i] <= 10^5

 */

class Solution {
    fun sumFourDivisors(nums: IntArray): Int {
        var result = 0
//        var hashSet = HashSet<Int>()
        for (num in nums) {
            if (num == 1 || num == 2 || num == 3) continue
            else {
                var squareRoot = Math.sqrt(num.toDouble()).toInt()
                //至少3個 絕對不會是四個
                if (squareRoot * squareRoot == num) continue
                var divisorsCount = 2
                var accumulator = 1 + num

                for (i in 2..squareRoot) {
                    if (num % i == 0) {
                        divisorsCount += 2
                        if (divisorsCount > 4) {
                            break
                        }
                        accumulator += i
                        accumulator += num / i
                    }
                }

                if (divisorsCount == 4) result += accumulator
            }

        }
        return result
    }

    //寫法稍微厲害一點的解
    fun sumFourDivisorsV2(nums: IntArray): Int {
        var result = 0
        for (num in nums) {

            //不用sqrt
            var squareRoot = 2
            //紀錄最後找到的因數，順便拿來當作flag
            var lastDivisor = 0
            while (true) {
                if (squareRoot * squareRoot > num)break
                else if (num % squareRoot == 0) {
                    if (lastDivisor != 0) {
                        lastDivisor = 0
                        break
                    } else {
                        lastDivisor = squareRoot
                    }
                }
                ++squareRoot
            }

            if (lastDivisor != 0 && lastDivisor != (num / lastDivisor)) result += 1 + num + lastDivisor + (num / lastDivisor)
        }
        return result
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(21,4,7)
    val result = Solution().sumFourDivisorsV2(input)
    println(result)
}