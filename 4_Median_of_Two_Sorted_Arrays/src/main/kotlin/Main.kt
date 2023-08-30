class Solution {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val l1 = nums1.size
        val l2 = nums2.size
        val isTwo = (l1+l2)%2 ==0
        val space = (l1+l2)/2
        for(i in nums1.indices){
            var indexInNum2 = nums2.binarySearch(nums1[1])
            if(indexInNum2<0)
                indexInNum2 = -(indexInNum2+1)

            i + indexInNum2
        }
    }
}