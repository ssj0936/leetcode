fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    var max = 0
    var n = 0
    fun maxScore(nums: IntArray): Int {
        n = nums.size/2
        foo(nums, 0, 0, 0)
        return max
    }

    private fun foo(nums: IntArray, count:Int, prevSubCount:Int, round:Int){
        if(round>=n)
            max = maxOf(max, count)

        var num1Ptr = -1
        var num2Ptr = -1
        for(i in nums.indices){
            if(nums[i] == -1) continue
            num1Ptr = i

            for(j in i .. nums.lastIndex){
                if(nums[j] == -1 || j==i) continue
                num2Ptr = j
                val gcd = getGCD(nums[num1Ptr], nums[num2Ptr])
                val subCount = (round+1) * gcd
                if(prevSubCount>subCount) continue

                val newNums = nums.clone().apply {
                    this[num1Ptr] = -1
                    this[num2Ptr] = -1
                }
                foo(newNums, count + (round+1)*gcd, (round+1)*gcd, round+1)
            }
        }
    }

    //num1>=num2
    fun getGCD(num1:Int, num2:Int):Int{
        if(num1 < num2)
            return getGCD(num2, num1)

        if(num1==1 || num2==1) return 1
        if(num1 % num2==0) return num2

        var gcd = 1
        var i = 2
        var n1 = num1
        var n2 = num2

        while (i*i <= n2){
            while (n1%i==0 && n2%i==0){
                n1 /= i
                n2 /= i
                gcd *=i
            }

            i += if(i%2==0) 1 else 2
        }
        return gcd
    }
}

class Solution2 {
    var max = 0
    var n = 0
    fun maxScore(nums: IntArray): Int {
        n = nums.size/2
        val gcdTable = Array(nums.size){IntArray(nums.size)}.apply {
            for(i in nums.size-1 downTo 0){
                for(j in i ..nums.size-1){
                    this[i][j] = getGCD(nums[i], nums[j])
                }
            }
        }

        foo(nums, 0,  0, 0, gcdTable)
        return max
    }

    private fun foo(nums: IntArray, count:Int, prevSubCount:Int, round:Int, gcdTable:Array<IntArray>){
        if(round>=n)
            max = maxOf(max, count)

        for(i in nums.indices){
            if(nums[i] == -1) continue

            for(j in i .. nums.lastIndex){
                if(nums[j] == -1 || j==i) continue
                val gcd = gcdTable[i][j]
                val subCount = (round+1) * gcd
                if(prevSubCount>subCount) continue

                val newNums = nums.clone().apply {
                    this[i] = -1
                    this[j] = -1
                }
                foo(newNums, count + (round+1)*gcd, (round+1)*gcd, round+1, gcdTable)
            }
        }
    }


    //num1>=num2
    private fun getGCD(num1:Int, num2:Int):Int{
        if(num1 < num2)
            return getGCD(num2, num1)

        if(num1==1 || num2==1) return 1
        if(num1 % num2==0) return num2

        var gcd = 1
        var i = 2
        var n1 = num1
        var n2 = num2

        while (i*i <= n2){
            while (n1%i==0 && n2%i==0){
                n1 /= i
                n2 /= i
                gcd *=i
            }

            i += if(i%2==0) 1 else 2
        }
        return gcd
    }
}

class Solution3 {
    fun maxScore(nums: IntArray): Int {
        val n = nums.size
        val gcdTable = Array(n){IntArray(n)}.apply {
            for(i in 0 until n){
                for(j in i+1 until n){
                    this[i][j] = getGCD(nums[i], nums[j])
                }
            }
        }
        val range = 1 shl n
        val dp = IntArray(range)
        for(i in 0 until range){
            val takenCnt = countTakenState(i)
            if((takenCnt and 1) == 1) continue

//            val round = takenCnt/2
            for(j in 0 ..nums.lastIndex){
                for(k in j+1 .. nums.lastIndex){
                    val state = (1 shl j) or (1 shl k)
                    if((state and i) != state) continue

                    dp[i] = maxOf(dp[i], (takenCnt shr 1) * gcdTable[j][k] + dp[i - state])
                }
            }

        }

        return dp[range-1]
    }

    private fun countTakenState(num:Int):Int{
        var n = num
        var cnt = 0
        while (n>0){
            n = n and(n-1)
            ++cnt
        }
        return cnt
    }

    //num1>=num2
    private fun getGCD(a:Int, b:Int):Int{
        return if(b == 0) a else getGCD(b, a%b)

//        if(num1 < num2)
//            return getGCD(num2, num1)

//        if(num1==1 || num2==1) return 1
//        if(num1 % num2==0) return num2
//
//        var gcd = 1
//        var i = 2
//        var n1 = num1
//        var n2 = num2
//
//        while (i*i <= n2){
//            while (n1%i==0 && n2%i==0){
//                n1 /= i
//                n2 /= i
//                gcd *=i
//            }
//
//            i += if(i%2==0) 1 else 2
//        }
//        return gcd
    }
}