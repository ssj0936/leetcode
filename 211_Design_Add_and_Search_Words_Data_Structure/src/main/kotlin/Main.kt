fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class WordDictionary(){
    data class Node(val char: Char=' ', var nextMap:HashMap<Char, Node> = HashMap(), var isLeaf:Boolean = false)
    private val root = Node()

    fun addWord(word:String){
        var pointer = root
        for(c in word){
            pointer = pointer.nextMap.getOrPut(c){ Node(c) }
        }
        pointer.isLeaf = true
    }

    fun search(word:String):Boolean{
        println("===========$word=========================")
        var pointer = root

//        for((_, nextNode) in pointer.nextMap){
//            println("1:${nextNode.char}")
//            if(bfs(word,0,nextNode)) return true
//        }
//        return false
        return bfs(word,0,pointer)
    }

    private fun bfs(word:String, wordCurrIndex:Int, p:Node):Boolean{
        if(wordCurrIndex > word.lastIndex && p.isLeaf) return true

        println("2:${word[wordCurrIndex]}")
        when(word[wordCurrIndex]){
            '.'->{
                println("21:")
                for((_, nextNode) in p.nextMap){
                    if(bfs(word, wordCurrIndex+1, nextNode)) return true
                }
                return false
            }
            else->{
                println("22:")
                if(word[wordCurrIndex] != p.char) {
                    println("221:")
                    return false
                }else{
                    println("222:")
                    for((_, nextNode) in p.nextMap){
                        if(bfs(word, wordCurrIndex+1, nextNode)) return true
                    }
                    return false
                }
            }
        }
    }

    private fun bfss(word:String, wordCurrIndex:Int, p:Node):Boolean{
        if(wordCurrIndex == word.lastIndex && p.isLeaf && (p.char=='.'||p.char==word[wordCurrIndex])) return true

        for((_, nextNode) in p.nextMap){
            if(word[wordCurrIndex] != nextNode.char && word[wordCurrIndex]!='.'){
                return false
            }else{
                if(bfss(word, wordCurrIndex+1, nextNode)) return true
            }
        }
        return false
    }
}