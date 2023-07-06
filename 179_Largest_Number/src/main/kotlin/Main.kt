//fun main(args: Array<String>) {
//    println("Hello World!")
//
//    // Try adding program arguments at Run/Debug configuration
//    println('0'.toInt())
//    println('1'.toInt())
//    println('9'.toInt())
//}

class Solution {
    fun largestNumber(nums: IntArray): String {
        if(nums.all { it==0 }) return "0"
        val arr = Array(nums.size){nums[it].toString()}
        arr.sortWith { o1, o2 ->
            compare(o1, o2)
        }

        return arr.joinToString("")
    }

    //o1 is longer one
    fun compare(o1:String, o2:String):Int{
        if(o1 == o2) return 0

        var index = 0
        val len1 = o1.length
        val len2 = o2.length
        val maxLen = minOf(len1,len2)
        while (index < maxLen){
            if(o1[index].toNumber() > o2[index].toNumber()) {
                return -1
            }
            else if(o1[index].toNumber() < o2[index].toNumber()) {
                return 1
            }

            ++index
        }

        if(len1<len2){
            val splitFront = o1
            val splitTail = o2.substring(index, len2)
            return compare(splitFront, splitTail)
        }else{
            val splitFront = o2
            val splitTail = o1.substring(index, len1)
            return -compare(splitFront, splitTail)
        }
    }

    private fun Char.toNumber():Int = this - '0'
}

class SolutionSmart {
    fun largestNumber(nums: IntArray): String {
        val arr = Array<String>(nums.size){nums[it].toString()}
        arr.sortWith(Comparator{o1,o2->
            if(o1+o2>o2+o1) return@Comparator -1
            else 1
        })

        val result = arr.joinToString("")
         return if(result[0]=='0') "0" else result
    }
}