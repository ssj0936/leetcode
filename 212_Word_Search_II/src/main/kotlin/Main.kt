import kotlin.collections.HashMap
class SolutionOri {
    private val dirs = arrayOf(
        intArrayOf(-1,0),
        intArrayOf(0,1),
        intArrayOf(1,0),
        intArrayOf(0,-1)
    )

    var m:Int = 0
    var n:Int = 0
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        m = board.size
        n = board[0].size
        val startingMap = HashMap<Char, MutableList<IntArray>>()
        for(i in 0 until m){
            for (j in 0 until n){
                startingMap.getOrPut(board[i][j]){ mutableListOf()}.add(intArrayOf(i,j))
            }
        }

        val result = mutableListOf<String>()
        val visited = Array(m){BooleanArray(n)}
        words.forEach { word ->
            if(foo(board, startingMap, word, visited))
                result.add(word)
        }

        return result
    }

    private fun foo(board: Array<CharArray>, startingMap:HashMap<Char, MutableList<IntArray>>, word:String, visited:Array<BooleanArray>):Boolean{
        val candidateStartPoints = startingMap.get(word[0])
        if(candidateStartPoints==null || candidateStartPoints.isEmpty()) return false

        for (candidateStartPoint in candidateStartPoints){
            val i = candidateStartPoint[0]
            val j = candidateStartPoint[1]
            visited[i][j] = true
            val result = dfs(board, i, j, word, visited, 1)
            visited[i][j] = false
            if(result)
                return true
        }
        return false
    }

    private fun dfs(board: Array<CharArray>, x:Int, y:Int, word:String, visited:Array<BooleanArray>, count:Int):Boolean{
        if(count == word.length) return true

        for(dir in dirs){
            val i = x + dir[0]
            val j = y + dir[1]
            if(i in 0 until m && j in 0 until n && !visited[i][j] && board[i][j] == word[count]){
                visited[i][j] = true
                val result = dfs(board, i, j, word, visited, count+1)
                visited[i][j] = false
                if(result)
                    return true
            }
        }
        return false
    }
}


class Solution {
    private val dirs = arrayOf(
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1)
    )

    data class TrieNode(
        val next:HashMap<Char, TrieNode> = hashMapOf(),
        var word: String? = null
    )

    private fun trieInsert(root:TrieNode, word:String){
        var pointer = root
        for(char in word){
            pointer = pointer.next.getOrPut(char){TrieNode()}
        }
        pointer.word = word
    }

    var m: Int = 0
    var n: Int = 0
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
        m = board.size
        n = board[0].size

        val root = TrieNode()
        for(word in words)
            trieInsert(root, word)

        val visited = Array(m){BooleanArray(n)}
        val result = mutableListOf<String>()
        for(i in 0 until m){
            for(j in 0 until n){
                dfs(board, visited, root, i, j, result)
            }
        }
        return result.toList()
    }

    fun dfs(board: Array<CharArray>, visited:Array<BooleanArray>, parent:TrieNode, i:Int, j:Int, result:MutableList<String>){
        val current = parent.next.get(board[i][j]) ?: return

        if(current.word!=null){
            result.add(current.word!!)
            current.word = null
        }

        visited[i][j] = true
        for(dir in dirs){
            val x = i + dir[0]
            val y = j + dir[1]
            if(x in 0 until m && y in 0 until n && !visited[x][y]){
                if(current.next.get(board[x][y])!=null)
                    dfs(board, visited, current, x, y, result)
            }
        }

        if(current.next.isEmpty())
            parent.next.remove(board[i][j])

        visited[i][j] = false
    }
}