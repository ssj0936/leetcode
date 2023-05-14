fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun smallestRepunitDivByK(k: Int): Int {
        if(k%2==0 || k%5==0) return -1

        var remainder = 0
        var count = 0
        do{
            val n = remainder*10+1
            remainder = n%k
            if(remainder==1 && count!=0) return -1
            ++count
        }while (remainder!=0)

        return count
    }
}