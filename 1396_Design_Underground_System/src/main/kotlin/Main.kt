fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class UndergroundSystem() {

    //HashMap<ID:Int, Pair<Time:Int, name:String>>
    val table = HashMap<Int, Pair<Int, String>>()
    //HashMap<start - end:String, IntArray>
    //IntArray[0]:count IntArray[1]:sum
    var avgTimeTable = HashMap<String, IntArray>()
    fun checkIn(id: Int, stationName: String, t: Int) {
        table.put(id, t to stationName)
    }

    fun checkOut(id: Int, stationName: String, t: Int) {
        val start = table.get(id)!!
        val startTime = start.first
        val startStation = start.second
        avgTimeTable.getOrPut("$startStation+$stationName"){ intArrayOf(0,0)}.apply {
            ++this[0]
            this[1]+= (t-startTime)
        }
    }

    fun getAverageTime(startStation: String, endStation: String): Double {
        return avgTimeTable.get("$startStation+$endStation")?.run {
            (this[1].toDouble()/this[0].toDouble())
        }?:0.0
    }

}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * var obj = UndergroundSystem()
 * obj.checkIn(id,stationName,t)
 * obj.checkOut(id,stationName,t)
 * var param_3 = obj.getAverageTime(startStation,endStation)
 */