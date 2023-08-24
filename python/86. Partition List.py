# Definition for singly-linked list.
from typing import Optional

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution:
    def partition(self, head: Optional[ListNode], x: int) -> Optional[ListNode]:
        # if head == None or head.next==None:
        #     return head

        dummy = ListNode()
        smallerPointer = dummy

        dummyBigger = ListNode()
        biggerPointer = dummyBigger

        pointer = head
        while pointer != None:
            if pointer.val < x:
                smallerPointer.next = pointer
                smallerPointer = smallerPointer.next
                pointer = pointer.next
            else:
                biggerPointer.next = pointer
                biggerPointer = biggerPointer.next
                pointer = pointer.next
        
        smallerPointer.next = dummyBigger.next
        biggerPointer.next = None

        return dummy.next


dummy = ListNode()
dummy2 = ListNode

print(type(dummy))
print(type(dummy2))