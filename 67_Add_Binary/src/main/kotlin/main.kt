/*
67. Add Binary(Easy)

Given two binary strings a and b, return their sum as a binary string.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"

Constraints:
1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.

 */
class Solution {
    fun addBinary(a: String, b: String): String {
        var a = a
        var b = b
        var acc = 0

        //補0
        if(a.length>b.length){
            val dif = a.length-b.length
            val sb = StringBuilder()
            repeat(dif){
                sb.append("0")
            }
            b= sb.append(b).toString()
        }else if(b.length>a.length){
            val dif = b.length-a.length
            val sb = StringBuilder()
            repeat(dif){
                sb.append("0")
            }
            a= sb.append(a).toString()
        }

//        println("a:$a")
//        println("b:$b")
        //從最小位回算
        val sb = StringBuilder()
        for(i in a.length-1 downTo 0){
//            println("i:$i")
//            println("a:${a[i]-'0'}")
//            println("b:${b[i]-'0'}")

            var digitSum = (a[i]-'0' ) + (b[i]-'0') + acc
            acc = 0
//            println("digitSum:$digitSum")

            if(digitSum>1){
                acc = 1
                digitSum %= 2
            }
//            println("------")

            sb.append(digitSum)
        }
        if(acc>0)
            sb.append(acc)

        return sb.reverse().toString()

    }
}
fun main(args: Array<String>) {
    val a = "1010"
    val b = "1011"
    val result = Solution().addBinary(a,b)
    println(result)
}