class Solution {
    fun kthFactor(n: Int, k: Int): Int {
        val record = mutableListOf<Int>()

        var count = 0
        var i = 1
        while (i*i<=n){
            if(n%i == 0) {
                record.add(i)
                ++count
            }

            if(count==k)
                return i

            ++i
        }
        var pointer = if(record.last()*record.last() == n) (record.size-1) else record.size
        val diff = k - count
        pointer -=diff

        return if(pointer<0) -1 else (n/record[pointer])
    }
}