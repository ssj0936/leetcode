/*
39. Combination Sum(Medium)
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
Input: candidates = [2], target = 1
Output: []

Example 4:
Input: candidates = [1], target = 1
Output: [[1]]

Example 5:
Input: candidates = [1], target = 2
Output: [[1,1]]

Constraints:

1 <= candidates.length <= 30
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500
 */

class Solution {

    /*用迴圈寫不下去*/
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val sortedCandidate:List<Int> = candidates.sorted()
        println(sortedCandidate)
        for(i in sortedCandidate.lastIndex downTo 0){
            if(target<sortedCandidate[i]) continue
            var tmpTarget = target
            var index = i
            while (tmpTarget>=0 || index<0){
                if(tmpTarget - sortedCandidate[index]<0) --index
                else if(tmpTarget - sortedCandidate[index]>0){
                    tmpTarget -= sortedCandidate[index]
                }else{

                }
            }
        }
        return listOf()
    }

    private val result:MutableList<List<Int>> = mutableListOf()
    private val current:MutableList<Int> = mutableListOf()
    fun combinationSumV2(candidates: IntArray, target: Int): List<List<Int>> {
        dfs(candidates.sortedArray(),target,0)
        return result
    }

    /*
    據說是蠻基本的解法，但我不會 我真可恥
    我從來沒有想過 可以把一個空間帶著進遞迴 然後重複對這個空間作操作

    再來這就是一個比較有規模的暴力法，有秩序地走過每一個組合，但配合遞迴
    add/remove就是在進行嘗試的動作，加進去 是看看成不成，不成就退回來remove
     */
    private fun dfs(candidates: IntArray, target: Int, start:Int){
        if(target == 0){
            result.add(current.toList())
            return
        }
        if(target<0) return

        for(i in start .. candidates.lastIndex){
            if(candidates[start]>target) break
            current.add(candidates[i])
            dfs(candidates,target - candidates[i], i)
            current.removeAt(current.lastIndex)
        }
    }
}
fun main(args: Array<String>) {
    val candidates = intArrayOf(5,2,3)
    val target = 8
    val result = Solution().combinationSumV2(candidates, target)
    println(result)
}