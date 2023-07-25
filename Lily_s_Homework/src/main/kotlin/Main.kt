import kotlin.collections.*
import kotlin.io.*
import kotlin.text.*

/*
 * Complete the 'lilysHomework' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

fun lilysHomework(arr: Array<Int>): Int {

    if(arr.size<=2) return 0
    val sortedArr = arr.sorted()
    //value, index
    val oriMappingTable = HashMap<Int,Int>().apply {
        arr.forEachIndexed { index, v ->  this[v] = index}
    }

    var s1 = foo(oriMappingTable.clone() as HashMap<Int, Int>,arr.clone(), sortedArr, true)
    var s2 = foo(oriMappingTable.clone() as HashMap<Int, Int>,arr.clone(), sortedArr, false)

    return minOf(s1, s2)
}

private fun foo(table:HashMap<Int,Int>, arr:Array<Int>, sortedArr:List<Int>, acc: Boolean):Int{
    var count = 0


    for(i in sortedArr.indices){
        val v = if (acc) sortedArr[i] else sortedArr[sortedArr.lastIndex-i]

        if(arr[i]==v) continue
        else{
            val from = arr[i]
            val to = v
            arr.swap(table[from]!!, table[to]!!)
            //swap
            val tmp = table[from]!!
            table[from] = table[to]!!
            table[to] = tmp

            ++count
        }
    }
    return count
}

private fun selectionSort(arr: Array<Int>, start:Int, acc:Boolean):Int{
    if(start==arr.size)
        return 0

    var swapI = start
    for(i in start .. arr.lastIndex){
        if((acc && arr[i]<arr[swapI]) || (!acc && arr[i]>arr[swapI]))
            swapI = i
    }

    arr.swap(swapI, start)
    return selectionSort(arr, start+1, acc) + (if(swapI != start) 1 else 0)

}

private fun Array<Int>.swap(a:Int, b:Int){
    var tmp = this[a]
    this[a] = this[b]
    this[b] = tmp
}


fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()

    val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val result = lilysHomework(arr)

    println(result)
}
