/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} head
 * @return {boolean}
 */


function ListNode(val) {
    this.val = val;
    this.next = null;
}

var a = new ListNode('a'),
    b = new ListNode('b'),
    c = new ListNode('b'),
    d = new ListNode('d'),
    e = new ListNode('e'),
    f = new ListNode('f'),
    g = new ListNode('g'),
    h = new ListNode('h'),
    i = new ListNode('i');

a.next = b;
b.next = c;
c.next = d;
d.next = e;
e.next = f;
f.next = d;

var head = a;

//solve 1
var hasCycle1 = function (head) {
    while (head != null && head.val != null) {
        console.log(head);
        if('isPass' in head) return true;

        head['isPass'] = true;
        head = head.next;
    }
    return false;
};

//solve 2
//奇技淫巧
var hasCycle2 = function (head) {
    if (head == null) return false;

    //fastPivot one time move forward 2 elements
    //slowPivot one time move forward 1 elements
    //2 pivot will meet each other when this link lit has a cycle in it
    if (head.next == null) return false;
    if (head.next.next == null) return false;

    var fastPivot = head.next.next,
        slowPivot = head.next;

    if (fastPivot == null || slowPivot == null) return false;

    while (fastPivot != slowPivot) {
        if (fastPivot.next != null && fastPivot.next.next != null && slowPivot.next != null) {
            fastPivot = fastPivot.next.next;
            slowPivot = slowPivot.next;
        } else {
            return false;
        }
    }
    return true;
};

var result = hasCycle(head);
console.log(result);