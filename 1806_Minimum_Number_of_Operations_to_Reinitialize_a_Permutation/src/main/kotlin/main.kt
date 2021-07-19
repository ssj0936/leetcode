/*
1806. Minimum Number of Operations to Reinitialize a Permutation
Medium

131

85

Add to List

Share
You are given an even integer n. You initially have a permutation perm of size n where perm[i] == i (0-indexed).

In one operation, you will create a new array arr, and for each i:

If i % 2 == 0, then arr[i] = perm[i / 2].
If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
You will then assign arr to perm.

Return the minimum non-zero number of operations you need to perform on perm to return the permutation to its initial value.



Example 1:

Input: n = 2
Output: 1
Explanation: perm = [0,1] initially.
After the 1st operation, perm = [0,1]
So it takes only 1 operation.
Example 2:

Input: n = 4
Output: 2
Explanation: perm = [0,1,2,3] initially.
After the 1st operation, perm = [0,2,1,3]
After the 2nd operation, perm = [0,1,2,3]
So it takes only 2 operations.
Example 3:

Input: n = 6
Output: 4


Constraints:

2 <= n <= 1000
n is even.

 */
class Solution {

    //先硬做
    fun reinitializePermutation(n: Int): Int {
        var arr = IntArray(n){i -> i}
        var count = 0
        println(arr.contentToString())

        while (true){
            ++count
            var isDone = true

            val tmp = IntArray(n)
            for(i in tmp.indices){
                if(i%2==0) tmp[i] = arr[i/2]
                else tmp[i] = arr[n/2 + (i-1)/2]

                if(i!=0 && tmp[i]<tmp[i-1])
                    isDone = false
            }

            if(isDone) break

            arr = tmp
            println(arr.contentToString())
        }
        println(arr.contentToString())

        val a = arr.toList().filter { it!=0 && it<3 }

        println(a)
        return count
    }

    /*
    前幾名的解
    切入的角度不是值，而是index位置
    去掉頭尾 每一回合都會一樣之外，每一個位子都會物換星移，但位子只會變化 而不會被抽掉 減少或增加
    所以只需要針對其中一個元素去模擬位子的變換，看要換幾次才會回到原本的index

     */

    fun reinitializePermutationV2(n: Int): Int {

        //target = 1
        var indexNextRound = indexShift(1,n)
        var count = 1

        while (indexNextRound!=1){
            indexNextRound = indexShift(indexNextRound,n)
            ++count
        }

        return count
    }

    private fun indexShift(index:Int, n:Int):Int
        = if(index%2==0) index/2 else n/2 + (index - 1)/2

}

fun main(args: Array<String>) {
    val result = Solution().reinitializePermutation(6)

    println(result)
}