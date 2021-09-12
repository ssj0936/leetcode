import java.util.*

/*
32. Longest Valid Parentheses(Hard)
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.



Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0


Constraints:

0 <= s.length <= 3 * 10^4
s[i] is '(', or ')'.

 */
class Solution :Sol{
    /*
    本來以為很簡單 結果被打個狗吃屎，這個作法是行不通的 特此紀錄

    本來有試過counter紀錄法，從頭開始，(+1,)-1，但(()這種狀況不行
    再來是這個，把第一個(和最後一個)找出來substring，從裡面找
    如果substring不是偶數 代表不可能成解，然後就是想盡辦法往內縮 但對於部分狀況還是解不出來
     */
    fun longestValidParentheses(s: String): Int {
        var startPointer = s.indexOfFirst { it=='(' }
        var endPointer = s.indexOfLast { it==')' }
        var max = 0

        while (startPointer<endPointer){
            if((endPointer - startPointer+1)%2!=0){
                if(startPointer+1 <= s.lastIndex && s[startPointer+1]=='(')
                    ++startPointer
                else if(endPointer-1 >= 0 && s[endPointer-1]==')')
                    --endPointer
                else{
                    do{++startPointer}
                    while (startPointer+1 <= s.lastIndex && s[startPointer+1]==')')

                    do{--endPointer}
                    while (endPointer-1 >= 0 && s[endPointer-1]=='(')
                }
            }else {
                //表示配對不起來
                val leftCount = s.substring(startPointer .. endPointer).count{it=='('}
                if(leftCount*2 != (endPointer-startPointer+1)){
                    do{++startPointer}
                    while (startPointer+1 <= s.lastIndex && s[startPointer+1]==')')

                    do{--endPointer}
                    while (endPointer-1 >= 0 && s[endPointer-1]=='(')
                }
                else{
                    return endPointer-startPointer+1
                }
            }
        }
        return max
    }

    /*
    stack法
    簡單的說 就是遇到(把它塞進stack
    遇到)就pop出來並且作條件檢查
    並使用一個start來作為substring的起點在各種狀況時需要做對應的檢查
     */
    private fun longestValidParenthesesV2(s: String): Int {
        val stack = Stack<Int>()

        var startIndex = 0
        var max = 0
        for(i in s.indices){
            val thisOne = s[i]
            //最單純的狀況，如果是( 就push進來
            //這裡push是push index，方便計算長度
            if(thisOne == '('){
                stack.push(i)
            }
            //遇到)的時候
            else{
                //如果buffer沒東西，代表這個char是雜訊沒有用，直接把起點往前移一位
                if(stack.isEmpty()){
                    startIndex = i+1
                }
                //有東西 那就要找一個(來配對，所以要pop一個出來
                else{
                    val top = stack.pop()

                    //最後一個配對 所以直接把起點拿來做運算
                    if(stack.isEmpty()){
                        max = Math.max(max,i-startIndex+1)
                    }
                    //前面還有一些(還沒配對完，但因為他們不一定能有效配對，所以先記錄
                    else{
                        max = Math.max(max,i-stack.peek())
                    }
                }
            }
        }
        return max
    }

    /*
    DP法

    求極值會先想到DP
    dp[i]代表以s[i]做結尾的substring長度
    如果s[i]是")"那可以檢查dp[i](也就是s[i]做結尾的長度)是否>0
    https://www.youtube.com/watch?v=7sLZoD05uxw
    這邊講解的詳細點
     */
    fun longestValidParenthesesV3(s: String): Int {
        val dp = Array(s.length){0}
        var max = 0

        for(i in 1 .. s.lastIndex){
            if(s[i]==')'){
                if(s[i-1]=='(') {
                    if (i - 2>=0/*表示沒有超出範圍*/ && dp[i - 2] > 0) dp[i] = 2 + dp[i - 2]
                    else dp[i] = 2
                }else{
                    val checkIndex = i-dp[i-1]-1
                    if(checkIndex >= 0/*表示沒有超出範圍*/&& s[checkIndex]=='('){
                        dp[i] = 2+dp[i-1]
                        if(checkIndex -1>=0 /*表示沒有超出範圍*/&& dp[checkIndex -1]>0)
                            dp[i] += dp[checkIndex -1]
                    }
                }
                max = Math.max(max,dp[i])
            }
        }
        return max
    }


    override fun sol(s: String): Int  = longestValidParenthesesV3(s)
}

interface Sol{
    fun sol(s: String): Int
}

fun main(args: Array<String>) {
    println("Hello World!")
}