/*Sort a linked list in O(n log n) time using 
constant space complexity.

Example 1:
Input: 4->2->1->3
Output: 1->2->3->4

Example 2:
Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/

/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} head
 * @return {ListNode}
 */

function ListNode(val) {
  this.val = val;
  this.next = null;
}


//divide and conquer
//divide into 2 list and sort individually
//then merge 2 sorted list
var sortList = function (head) {

  const merge2SortedList = (l1, l2) => {
    if (!l1) return l2;
    if (!l2) return l1;

    if (l1.val < l2.val) {
      l1.next = merge2SortedList(l1.next, l2);
      return l1;
    } else {
      l2.next = merge2SortedList(l1, l2.next);
      return l2;
    }
  }

  if (head == null) return head;

  //get list length
  let length = 0,
    pivot = head;
  while (pivot != null) {
    pivot = pivot.next;
    length++;
  }

  //return head if only 1 node
  //preventing causing cycle, make sure set next of tail to NULL
  if (length == 1) {
    head.next = null;
    return head
  } 
  //else seperate list into 2 list at right middle
  //preventing causing cycle, make sure set next of tail to NULL
  else {
    let half = Math.floor(length / 2);
    let l1 = head,
      l2 = head,
      tmp;
    while (half != 0) {
      tmp = l2
      l2 = l2.next;
      half--;
    }
    tmp.next = null;
    //sort individually
    l1 = sortList(l1);
    l2 = sortList(l2);
    //merge 2 sorted list
    return merge2SortedList(l1, l2);
  }
};

let listArray = [1, -1, 99, 3, 9, 6, -2, 12, 10, 128, 5, 2];
let head, tmp;
for (let i = 0; i < listArray.length; ++i) {
  if (i == 0) {
    tmp = new ListNode(Number(listArray[i]));
    head = tmp;
  } else {
    tmp.next = new ListNode(Number(listArray[i]));
    tmp = tmp.next;
  }
}


let result = sortList(head);
console.log("listArray");
console.log(listArray);
console.log("result");
console.log(result);