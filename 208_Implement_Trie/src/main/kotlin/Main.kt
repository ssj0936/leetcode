fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface BaseTrie{
    fun insert(word:String)
    fun search(word:String):Boolean
    fun startsWith(prefix:String):Boolean
}


class Trie():BaseTrie{
    data class Node(val char: Char =' ', var nextMap:HashMap<Char, Node>? = null, var isLeaf:Boolean = false)

    private val root = Node()

    override fun insert(word: String) {
        var pointer = root

        for(char in word){
            if(pointer.nextMap == null){
                pointer.nextMap = HashMap()
            }

            pointer = pointer.nextMap!!.getOrPut(char){ Node(char) }
        }

        pointer.isLeaf = true
    }

    override fun search(word: String): Boolean {
        var pointer:Node? = root

        for (char in word) {
            pointer = pointer?.nextMap?.getOrElse(char){null}
            if(pointer == null) return false
        }

        return pointer!!.isLeaf
    }

    override fun startsWith(prefix: String): Boolean {
        var pointer:Node? = root

        for (char in prefix) {
            pointer = pointer?.nextMap?.getOrElse(char){null}
            if(pointer == null) return false
        }

        return true
    }

}
