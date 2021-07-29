/*
201. Bitwise AND of Numbers Range(Medium)
Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.

Example 1:
Input: left = 5, right = 7
Output: 4

Example 2:
Input: left = 0, right = 0
Output: 0

Example 3:
Input: left = 1, right = 2147483647
Output: 0

Constraints:

0 <= left <= right <= 231 - 1

 */
class Solution {

    /*
    首先，左右若是位數不同 一定是0，從左走到右，所有的bit一定會變過一遍，做AND一定是0
    再來紀錄那些bit出現過0，先做一個與左右等長的zeroCheckList，塞入對應的index

    之後從左loop到右，檢查zeroCheckList中的index，若該數字的這個index位數為0，則將之從zeroCheckList刪掉
    zeroCheckList.size=0就停下

    最後再依照zeroCheckList剩下的部位，加總起來

    後記：卡在把某一位的bit取出來太久了
     */
    fun rangeBitwiseAnd(left: Int, right: Int): Int {
        //先找right有幾位
        var tmpRight = right
        var rightCount = 0
        while (tmpRight>=1){
            tmpRight /=2
            ++rightCount
        }

        //先找left有幾位
        var tmpLeft = left
        var leftCount = 0
        while (tmpLeft>=1){
            tmpLeft /=2
            ++leftCount
        }
        if(leftCount!=rightCount) return 0


        var zeroCheckList = mutableListOf<Int>()
        var minCount = Math.min(leftCount,rightCount)
        for(i in 0 ..minCount) zeroCheckList.add(i)
        for(i in left .. right){
            if(zeroCheckList.size == 0) break

            for(j in zeroCheckList.reversed()){
                if((i / pow(2,j))%2 == 0)
                    zeroCheckList.remove(j)
            }
//            println(zeroCheckList)
        }

        println(zeroCheckList)
        var result = 0
        for(i in zeroCheckList){
            result += pow(2,i)
        }

        return result
    }

    fun pow(a:Int,b:Int):Int{
        var sum = 1
        repeat(b) {
            sum *= a
        }
        return sum
    }
}
fun main(args: Array<String>) {
    val left = 1
    val right = 1
    val result = Solution().rangeBitwiseAnd(left,right)
    println(result)
}