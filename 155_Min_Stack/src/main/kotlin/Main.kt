import java.util.*

/*
155. Min Stack
Medium
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.


Example 1:
Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2

Constraints:

-2^31 <= val <= 2^31 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 10^4 calls will be made to push, pop, top, and getMin.

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}


/*
一開始的想法，題目規定說要O(1)
就想到用一個變數來存min，push的時候去更新Min，但pop之後就卡住了 想不到要怎麼去拿次小的值更新min

後來O(1)，想到priority queue，但直接用kotlin內建的其實意義不大

想到額外maintain一個heap，一個stack 一個heap，同時進值退值，但heap要從中間扣掉一個值之後 後面的child要全部heapify，少說也是O(nlogn)

最後看到個奇技淫巧：除了額外用一個min來存最小值以外，stack裡面一個node同時包含了自身的value以外，也多一個欄位 表示"把這一位pop掉之後 正確的min值"
所以也就是在push時，如果push了比min小的值的時候(其實是小於等於)，要把當前的min值跟著這點一起存起來
 */
class MinStack() {

    var minValue:Int = Int.MAX_VALUE
    //first: val, second: 次小
    var mStack: Stack<Pair<Int, Int?>> = Stack()
    var count = 0

    fun push(`val`: Int) {
        //第一個 把minvalue洗成自己，免得second存到怪東西(Int.MAX_VALUE)
        if(count == 0)
            minValue = `val`

        //edge case注意，用小於等於，是為了push多個同樣的值 還能存到正確的值
        val node = if(`val`<=minValue){
            Pair(`val`, minValue)
        }else{
            //除了要更新min值的狀況外，second要存啥都沒差
            Pair(`val`, null)
        }

        //更新min值
        if(`val`<minValue)
            minValue = `val`

        mStack.push(node)
        ++count
    }

    fun pop() {
        val node = mStack.pop()

        //如果吐出來的值就是min值，那就把當時push進來的次小值restore回去
        if(node.first==minValue)
            minValue = node.second!!
        --count
    }

    fun top(): Int {
        return mStack.peek().first
    }

    fun getMin(): Int {
        return minValue
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(`val`)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */