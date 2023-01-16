fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}
interface Sol{
    fun canFinish(numCourses:Int, prerequisites:Array<IntArray>):Boolean
}

class Solution:Sol{
    override fun canFinish(numCourses:Int, prerequisites:Array<IntArray>):Boolean{
        return true
    }
}