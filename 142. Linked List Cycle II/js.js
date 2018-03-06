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
f.next = f;

var head = a;

//solve1
var detectCycle = function (head) {
    var set = new Set();
    while (head != null && head.val != null) {
        if(set.has(head)) return head;
        set.add(head);
        head = head.next;
    }
    return null;
};

//solve2 
//奇技淫巧
/**
 * fastPivot one time move forward 2 elements
   slowPivot one time move forward 1 elements
   we assume that 
   t = how many times that pivot move
   x = distance from head to first point of cycle
   y = length of cycle
   k = distance from first point of cycle to the point that 2 pivot meet each other

   so 
   fastPivot movement distance=> 2t = x + my + K
   slowPivot movement distance=> t = x + ny + K
   => 2x+2ny+2k = x + my + K
   => x+k = my-2ny = (m-2n)y
   
   it means that if moving more x step from k, pivot will meet the first point of circle
**/

var detectCycle = function (head) {
    if (head == null) return null;

    //fastPivot one time move forward 2 elements
    //slowPivot one time move forward 1 elements
    //2 pivot will meet each other when this link lit has a cycle in it
    if (head.next == null) return null;
    if (head.next.next == null) return null;

    var fastPivot = head.next.next,
        slowPivot = head.next;

    if (fastPivot == null || slowPivot == null) return null;

    while (fastPivot != slowPivot) {
        if (fastPivot.next != null && fastPivot.next.next != null && slowPivot.next != null) {
            fastPivot = fastPivot.next.next;
            slowPivot = slowPivot.next;
        } else {
            return null;
        }
    }
    
    //move one of pivot to head
    //and 2 pivot move tha same distance, that is 1.
    slowPivot = head;
    while(slowPivot != fastPivot){
        slowPivot = slowPivot.next;
        fastPivot = fastPivot.next;
    }
    return slowPivot;
};

var result = detectCycle(head);
console.log(result);