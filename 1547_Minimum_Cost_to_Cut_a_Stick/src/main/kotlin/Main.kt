fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}
interface Sol{
    fun minCost(n: Int, cuts: IntArray): Int
}

class SolutionDP:Sol {
    override fun minCost(n: Int, cuts: IntArray): Int {
        val size = cuts.size
        val cutArr = IntArray(size+2).apply {
            cuts.sort()

            this[0] = 0
            for(i in 0 .. cuts.lastIndex){
                this[i+1] = cuts[i]
            }
            this[size+2-1] = n
        }

        val dp = Array(size+2){IntArray(size+2)}


        for(j in 1 until size+2){
            for(i in j-1 downTo 0){
                if(i+1==j) {
                    println("dp[$i][$j] = 0")
                    dp[i][j] = 0
                }else{
                    var min = Int.MAX_VALUE
                    for(k in i+1 .. j-1){
                        min = minOf(min, dp[i][k]+dp[k][j])
                    }
                    val cost = cutArr[j]-cutArr[i]
                    dp[i][j] = min + cost
                }
            }
        }
        return dp[0][size+2-1]
    }
}


class Solution:Sol {
    override fun minCost(n: Int, cuts: IntArray): Int {
        val cutsList = cuts.sorted().toMutableList().apply {
            add(0,0)
            add(n)
        }
        println(cutsList)
        val result = divideAndConquer(cutsList,0,cutsList.lastIndex)
        return result
    }

    private fun divideAndConquer(cuts: List<Int>, start:Int, end:Int):Int{

        if(start == end-1) return 0
        if(start == end-2) return cuts[end] - cuts[start]

        //2 cut points at least
        val mid = cuts[start] + (cuts[end] - cuts[start])/2
        val cost = cuts[end] - cuts[start]

        val midIndex = cuts.binarySearch(mid, start, end+1)
        println("start:$start, end:$end, find($mid),isFound:${midIndex>=0},actualIndex:${if(midIndex>=0) midIndex else (-(midIndex + 1))}")
        //not found
        if(midIndex<0){
            val actualInsertIndex = -(midIndex + 1)
            if((cuts[actualInsertIndex] - mid) > (mid - cuts[actualInsertIndex-1])){
                return cost + divideAndConquer(cuts, start, actualInsertIndex-1) + divideAndConquer(cuts, actualInsertIndex-1, end)
            }else if((cuts[actualInsertIndex] - mid) < (mid - cuts[actualInsertIndex-1])){
                return cost + divideAndConquer(cuts, start , actualInsertIndex) + divideAndConquer(cuts, actualInsertIndex, end)
            }else{
                return cost + minOf(
                    divideAndConquer(cuts, start, actualInsertIndex-1) + divideAndConquer(cuts, actualInsertIndex-1, end) ,
                    divideAndConquer(cuts, start , actualInsertIndex) + divideAndConquer(cuts, actualInsertIndex, end)
                )
            }
        }
        //found
        else{
            val actualInsertIndex = midIndex
            if((cuts[actualInsertIndex+1] - mid) > (mid - cuts[actualInsertIndex])){
                return cost + divideAndConquer(cuts, start, actualInsertIndex) + divideAndConquer(cuts, actualInsertIndex, end)
            }else if((cuts[actualInsertIndex+1] - mid) < (mid - cuts[actualInsertIndex])){
                return cost + divideAndConquer(cuts, start, actualInsertIndex+1) + divideAndConquer(cuts, actualInsertIndex+1, end)
            }else{
                return cost + minOf(
                    divideAndConquer(cuts, start, actualInsertIndex) + divideAndConquer(cuts, actualInsertIndex, end) ,
                    divideAndConquer(cuts, start, actualInsertIndex+1) + divideAndConquer(cuts, actualInsertIndex+1, end)
                )
            }
        }
    }
}