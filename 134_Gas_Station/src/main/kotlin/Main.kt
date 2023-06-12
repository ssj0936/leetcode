
class Solution{
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int{
        var totalSum = 0
        var currentSum = 0

        var start = 0
        for(i in 0 .. gas.lastIndex){
            val diff = gas[i] - cost[i]
            totalSum += diff
            currentSum += diff

            if(currentSum<0){
                currentSum = 0
                start = i+1
            }
        }

        return if(totalSum<0)
            return -1
        else
            start
    }
}

class SolutionOri {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        val n = gas.size

        var sum = 0
        var start = 0
        var end = 0
        while (start<n){
            while (sum>=0){
                sum = sum + gas[end] - cost[end]
                end = (end+1)%n
                if(end == start && sum>=0){
                        return end
                }
            }

            while (sum<0 && (start<n || start<end)){
                sum = sum - gas[start] + cost[start]
                ++start
            }
        }
        return -1
    }
}