import java.util.*

/*
22. Generate Parentheses(Medium)
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

Example 2:
Input: n = 1
Output: ["()"]

Constraints:

1 <= n <= 8

 */

class Solution :Solu{
    private val result = mutableListOf<String>()
    private val stack = Stack<Char>()
    fun generateParenthesis(n: Int): List<String> {
        dfsV2(n,n)
        return result
    }

    private fun dfs(leftAmount:Int, rightAmount:Int){
        if(leftAmount==0 && rightAmount==0) result.add(stack.joinToString(""))
        else{
            if(stack.isEmpty()){
                stack.push('(')
                dfs(leftAmount-1,rightAmount)
                stack.pop()
            }else if(leftAmount==0){
                stack.push(')')
                dfs(leftAmount, rightAmount - 1)
                stack.pop()

            }else if(rightAmount == 0){
                stack.push('(')
                dfs(leftAmount-1,rightAmount)
                stack.pop()
            }else{
                if(leftAmount == rightAmount) {
                    stack.push('(')
                    dfs(leftAmount - 1, rightAmount)
                    stack.pop()
                }else {
                    stack.push('(')
                    dfs(leftAmount - 1, rightAmount)
                    stack.pop()

                    stack.push(')')
                    dfs(leftAmount, rightAmount - 1)
                    stack.pop()
                }
            }
        }
    }

    private fun dfsV2(leftAmount:Int, rightAmount:Int){
        //合法與否都坐在這一層，如果剩餘的左括號>剩餘的右括號，代表目前字串裡面右括號比左括號多，
        if(leftAmount<0 || rightAmount<0 || leftAmount>rightAmount) return
        else{
            //扣打用完 產生結果
            if(leftAmount == 0 && rightAmount==0) {
                result.add(stack.joinToString(""))
                return
            }

            stack.push('(')
            dfsV2(leftAmount-1,rightAmount)
            stack.pop()

            stack.push(')')
            dfsV2(leftAmount,rightAmount-1)
            stack.pop()
        }
    }

    override fun result(n: Int): List<String> = generateParenthesis(n)

}

interface Solu{
    fun result(n: Int): List<String>
}
fun main(args: Array<String>) {
    println("Hello World!")
}