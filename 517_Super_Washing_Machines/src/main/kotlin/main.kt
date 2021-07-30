/*
517. Super Washing Machines(Hard)
You have n super washing machines on a line. Initially, each washing machine has some dresses or is empty.
For each move, you could choose any m (1 <= m <= n) washing machines, and pass one dress of each washing machine to one of its adjacent washing machines at the same time.
Given an integer array machines representing the number of dresses in each washing machine from left to right on the line, return the minimum number of moves to make all the washing machines have the same number of dresses. If it is not possible to do it, return -1.

Example 1:
Input: machines = [1,0,5]
Output: 3
Explanation:
1st move:    1     0 <-- 5    =>    1     1     4
2nd move:    1 <-- 1 <-- 4    =>    2     1     3
3rd move:    2     1 <-- 3    =>    2     2     2

Example 2:
Input: machines = [0,3,0]
Output: 2
Explanation:
1st move:    0 <-- 3     0    =>    1     2     0
2nd move:    1     2 --> 0    =>    1     1     1

Example 3:
Input: machines = [0,2,0]
Output: -1
Explanation:
It's impossible to make all three washing machines have the same number of dresses.

Constraints:

n == machines.length
1 <= n <= 104
0 <= machines[i] <= 105

 */
class Solution {
    fun findMinMoves(machines: IntArray): Int {
        val sum = machines.sum()
        if(sum % machines.size!=0) return -1
        else{
            var debt = 0
            var prevDebt = 0
            var record = Int.MIN_VALUE
            var c = sum / machines.size

            for(dressCount in machines){
                prevDebt = debt
                debt += c - dressCount

                var tmpDebt = debt
                if(prevDebt >0 && debt <=0){
                    tmpDebt -= prevDebt
                }

                if(Math.abs(tmpDebt) > record) record = Math.abs(tmpDebt)
            }
            return record
        }
    }
}
fun main(args: Array<String>) {
    val input = intArrayOf(1,0,5,0,9,0,0,1,1,3,2)
    val result = Solution().findMinMoves(input)
    println(result)
}