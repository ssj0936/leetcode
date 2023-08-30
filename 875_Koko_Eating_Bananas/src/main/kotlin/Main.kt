class Solution {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var head = 1
        var tail = piles.max()!!

        while (head<=tail){
            val mid = head + (tail-head)/2
            if(isSatisfy(piles, h, mid)){
                tail = mid -1
            }else
                head = mid +1
        }

        return tail
    }

    fun isSatisfy(piles: IntArray, h: Int, k:Int):Boolean{
        var counter = 0
        for(pile in piles){
            counter += pile/k + if(pile%k==0) 0 else 1
            if(counter>h) return false
        }
        return true
    }
}