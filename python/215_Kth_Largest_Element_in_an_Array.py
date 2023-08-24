import heapq

class Solution:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        minHeap = []
        for num in nums:
            if len(minHeap)<k:
                heapq.heappush(minHeap, num)
            elif num>minHeap[0]:
                heapq.heappop(minHeap)
                heapq.heappush(minHeap, num)

        return minHeap[0]