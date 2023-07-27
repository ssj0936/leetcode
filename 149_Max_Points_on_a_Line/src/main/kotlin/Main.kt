fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun maxPoints(points: Array<IntArray>): Int {
        var max = 0

        for(i in points.indices){
            //slope, count
            val table = hashMapOf<Float, Int>()
            for(j in i+1 .. points.lastIndex){
                val slope = getSlope(points[i], points[j])
                table.put(slope, table.getOrDefault(slope,0)+1)
                max = maxOf(max, table.get(slope)!!)
                if(max>=points.lastIndex-i)
                    return max+1
            }
        }

        return max+1
    }

    private fun getSlope(p1:IntArray, p2:IntArray):Float{
        if(p1[0]==p2[0]) return Float.MAX_VALUE
        if(p1[1]==p2[1]) return 0f
        return ((p1[1]-p2[1]).toFloat()/(p1[0]-p2[0]))
    }
}
