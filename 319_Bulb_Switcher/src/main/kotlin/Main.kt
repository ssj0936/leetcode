fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun bulbSwitch(n: Int): Int {
        //亮著的個數
        var count = 0
        //平方根，因為數字越來越大 平方根不會變小 所以這個變數可以放在loop外
        var tmp = 1
        for(i in 1..n){
            while (tmp*tmp<=i){
                ++tmp
            }
            --tmp
            //如果這是完全平方數 最後就會亮著
            if(tmp*tmp == i)
                ++count
        }

        return count
    }
}

class SolutionBest {
    fun bulbSwitch(n: Int): Int {
        return Math.sqrt(n.toDouble()).toInt()
    }
}