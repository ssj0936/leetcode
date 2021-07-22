/*
1849. Splitting a String Into Descending Consecutive Values(Medium)

You are given a string s that consists of only digits.
Check if we can split s into two or more non-empty substrings such that the numerical values of the substrings are in descending order and the difference between numerical values of every two adjacent substrings is equal to 1.

For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89]. The values are in descending order and adjacent values differ by 1, so this way is valid.
Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"]. However all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1] respectively, all of which are not in descending order.
Return true if it is possible to split sas described above, or false otherwise.
A substring is a contiguous sequence of characters in a string.



Example 1:

Input: s = "1234"
Output: false
Explanation: There is no valid way to split s.

Example 2:

Input: s = "050043"
Output: true
Explanation: s can be split into ["05", "004", "3"] with numerical values [5,4,3].
The values are in descending order with adjacent values differing by 1.

Example 3:

Input: s = "9080701"
Output: false
Explanation: There is no valid way to split s.

Example 4:

Input: s = "10009998"
Output: true
Explanation: s can be split into ["100", "099", "98"] with numerical values [100,99,98].
The values are in descending order with adjacent values differing by 1.

Constraints:

1 <= s.length <= 20
s only consists of digits.
 */
class Solution {

    /*
    看來看去大家都是用dfs

    思路是這樣：
    從前面往後做substring找出第一位數字num，接著以這個數字做為基準，在往後剩下的字串找num-1
    如果都能夠成功找到也把剩下的字串耗完了，return true

    如果剩下的字串都找不到就再重新調整第一位數字，並且重複，直到窮舉第一位數字完結，都沒有就return false

    有些小細節：
    1.因為1 <= s.length <= 20，所以第一位數字一定不會超過字串長度的一半
    2.9999999999 超過 int上限了，所以需要考慮用double或是long
    3.這邊使用regex把開頭數字夾出來以及去掉開頭的0，但後來想一想實在沒必要，直接substring把字串轉換成long就好了
     */
    fun splitString(s: String): Boolean {

        val ns:String = s.replace("^0*".toRegex(),"")
        if(ns.isEmpty()||ns.length == 1) return false

        //瓊舉法，從前面一位一位當作第一位起始數字
        //只需要檢查一半就好，因為至少要割成兩個
        for(i in 0 until ns.length/2 + 1 ){
            val num = ns.substring(0,i+1).toLong()
            println(num)
            if(loopThrough(ns.substring(i+1,ns.length),num)) return true
            println("---------------------")
        }
        return false
    }

    private fun loopThrough(s:String, startNum:Long):Boolean{
        if(s.isEmpty()) return false

        var string = s
        var target = startNum - 1
        while (string.isNotEmpty()){
            println("string:$string")
            println("REGEX:^0*$target")
            val ns = string.replace("^0*$target".toRegex(),"")
            if(ns.length == string.length) return false
            else{
                string = ns
                --target
            }
        }
        return true
    }


    //第二版，不用REGEX的方法，但大致上是一樣的
    fun splitStringV2(s: String): Boolean {

        if(s.isEmpty()||s.length == 1) return false

        for(i in s.indices){
            val num = s.substring(0,i+1).toDouble()
            //s length的限制，只要超過10位數 就代表false
            if(num>=1e10) break

            val restString = s.substring(i+1,s.length)
            //第一次截完剩下空字串 代表沒有第二個element -> false
            if(restString.isEmpty())
                return false
            //把第一位數字 和剩下的字串 去做處理
            else if(loopThroughV2(restString,num))
                return true
        }
        return false
    }

    //幾本上是做一樣的事 但邊際條件有變
    private fun loopThroughV2(s:String, startNum:Double):Boolean{
        if(s.isEmpty()) return true

        var string = s
        var target = startNum - 1

        //一樣再切出數字 然後看有沒有比上一位少一，有個話繼續做下去，直到剩餘字串被切光
        for(i in string.indices){
            val num = s.substring(0 until i+1).toDouble()
            if(num == target && loopThroughV2(s.substring(i+1, s.length),num)){
                return true
            }
        }
        return false
    }
}

fun main(args: Array<String>) {
    val input = "050043"
    val result = Solution().splitStringV2(input)
    println(result)
}