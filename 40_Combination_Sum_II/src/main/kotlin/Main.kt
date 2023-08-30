import java.util.*

class Solution {
    val result = mutableListOf<List<Int>>()
    val visited = hashSetOf<String>()
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        candidates.sort()
        val stack = LinkedList<Int>()

        dfs(candidates, target, 0, stack, 0)
        return result
    }

    private fun dfs(candidates: IntArray, target: Int, index:Int, stack:LinkedList<Int>, sum:Int){
        if(sum == target && !visited.contains(stack.toString())){
            visited.add(stack.toString())
            result.add(stack.toList())
            return
        }

        if(index>candidates.lastIndex || sum>target)
            return

        for(i in index .. candidates.lastIndex){
            if(sum + candidates[i]>target)
                break
            if(i!=index && candidates[i]==candidates[i-1]) continue
            stack.push(candidates[i])
            dfs(candidates, target, i+1, stack, sum + candidates[i])
            stack.pop()
        }
    }
}

class Solution {
    val result = mutableListOf<List<Int>>()
    val visited = hashSetOf<String>()
    fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
        val freq = IntArray(51).apply {
            candidates.forEach { ++this[it] }
        }
        val stack = LinkedList<IntArray>()
        dfs(freq, target, 1, stack, 0)
        return result
    }

    private fun dfs(freq:IntArray, target: Int, index:Int, stack: LinkedList<IntArray>, sum:Int){
        if(index >= freq.size) return
        if(sum == target){
            val subResult = mutableListOf<Int>()
            stack.forEach {arr->
                repeat(arr[1]){
                    subResult.add(arr[0])
                }
            }
            result.add(subResult)
            return
        }

        //數字
        for(i in index .. freq.lastIndex){
            if(freq[i]==0) continue
            //個數
            for(j in 1 .. freq[i]){
                val subSum = sum + i*j
                if(subSum>target) break

                stack.push(intArrayOf(i, j))
                dfs(freq, target, i+1, stack, subSum)
                stack.pop()
            }
        }
    }
}