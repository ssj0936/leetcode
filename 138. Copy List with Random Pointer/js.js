/*
138. Copy List with Random Pointer
A linked list is given such that 
each node contains an additional 
random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Example 1:

Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
*/

/**
 * // Definition for a Node.
 * function Node(val,next,random) {
 *    this.val = val;
 *    this.next = next;
 *    this.random = random;
 * };
 */

//parameter "mark" is just for debugging
function Node(val,next,random,mark) {
  this.val = val;
  this.next = next;
  this.random = random;
  this.mark = mark;
};

/**
* @param {Node} head
* @return {Node}
*/

//solution1, naive one
var copyRandomList = function(head) {
  if(!head) return head;

  let headPointer = head,
  copyPointer = null, 
  copyPointerAnchor = null;

  //val of each node cannot ba major key
  //because it's not guarentee to be unique
  //use for mapping
  let listArray = [],copyListArray = [];

  //create a next node if current pointer is not null 
  //or currenter node is not looped
  while(headPointer != null){
    let newNode = new Node(headPointer.val,null,null);
    
    if(!copyPointer){
      copyPointer = newNode;
      copyPointerAnchor = copyPointer;
    }else{
      copyPointer.next = newNode;
      copyPointer = copyPointer.next;
    }
    
    copyListArray.push(newNode);
    listArray.push(headPointer);

    headPointer = headPointer.next;
  }

  for(let i in listArray){
    let randomPointerIndex = (!listArray[i].random) ? null : listArray.indexOf(listArray[i].random);
    if(randomPointerIndex == null)
      copyListArray[i].random = null;
    else
      copyListArray[i].random = copyListArray[randomPointerIndex];
  }
  return copyPointerAnchor;
};


//solution2, tricky one
var copyRandomList = function(head) {
if(head == null) return head;

//O for original
//C for copy
let o_HeadPointer = head;
while(o_HeadPointer != null){
  //insert a clone node after the original node
  let newPoint = new Node(o_HeadPointer.val,o_HeadPointer.next,null);
  // newPoint.clone = true;
  o_HeadPointer.next = newPoint;
  o_HeadPointer = newPoint.next;
}

//reset o_HeadPointer for setting random
o_HeadPointer = head;
while(o_HeadPointer != null){
  // if(o_HeadPointer.random == null)
  //   o_HeadPointer.next.random =
  o_HeadPointer.next.random = (o_HeadPointer.random == null) ? null : o_HeadPointer.random.next;
  o_HeadPointer = o_HeadPointer.next.next;
}

//reset o_HeadPointer for setting random
//loop through list to create a clone list head pointer to link with it
//and seperate it to original one and clone one
o_HeadPointer = head;
//create a 
let c_HeadPointer = new Node(),
  c_anchor = c_HeadPointer;
while(o_HeadPointer != null){
  //link clone list element
  c_HeadPointer.next = o_HeadPointer.next;
  c_HeadPointer = c_HeadPointer.next;

  //and break the link to seperate into 2 list
  o_HeadPointer.next = o_HeadPointer.next.next;
  o_HeadPointer = o_HeadPointer.next;
}
return c_anchor.next;
};


let a = new Node(1,null,null,"a"),
b = new Node(2,null,null,"b"),
c = new Node(2,null,null,"c"),
d = new Node(2,null,null,"d"),
e = new Node(2,null,null,"e"),
f = new Node(2,null,null,"f"),
g = new Node(1,null,null,"g");

a.next = b;
b.next = c;
c.next = d;
d.next = e;
e.next = f;
f.next = g;

a.random = a;
b.random = b;
c.random = b;
d.random = b;
e.random = c;
f.random = c;
g.random = a;
let copyList = copyRandomList(a)
console.log("--------------------------------")

console.log(copyList);

