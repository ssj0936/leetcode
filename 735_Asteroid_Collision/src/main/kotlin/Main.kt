import java.util.*

class Solution {
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val stack = LinkedList<Int>()

        for(asteroid in asteroids){
            if(asteroid>0)
                stack.push(asteroid)
            else{
                val size = asteroid*-1
                //撞爛那些1.逆向 2.尺寸比自己小的
                while (stack.isNotEmpty() && stack.peek()>0 && stack.peek()<size){
                    stack.pop()
                }
                //撞完之後，如果還有東西，可能會是：
                //1.同樣方向的
                //2.跟自己一樣大的
                //3.比自己還大的
                if(stack.isNotEmpty() && stack.peek() == size){
                    stack.pop()
                }else if(stack.isEmpty() || stack.peek()<0){
                    stack.push(asteroid)
                }
            }
        }

        val result = IntArray(stack.size).apply {
            val k = stack.size
            for(i in 0 until k){
                this[k-1-i] = stack.pop()
            }
        }

        return result
    }
}