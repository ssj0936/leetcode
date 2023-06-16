class Solution(w: IntArray) {
    var sum:Int = 0
    var arr:IntArray = IntArray(w.size)
    init {
        //arr[i] means: flatten w, index i in w should start at where
        for(i in 0 .. w.lastIndex-1){
            sum += w[i]
            arr[i+1] = sum
        }
        sum +=w.last()
    }

    fun pickIndex(): Int {
        val rand = (0 until sum).random()
        val binarySearchIndex = arr.binarySearch(rand)

        return if(binarySearchIndex>=0){
            binarySearchIndex
        }else{
            -(binarySearchIndex + 1) -1
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = Solution(w)
 * var param_1 = obj.pickIndex()
 */