/**
 * 79. Word Search
Medium
Given an m x n grid of characters board and a string word, return true if word exists in the grid.
The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true

Example 2:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true

Example 3:
Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.

Follow up: Could you use search pruning to make your solution faster with a larger board?
 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun exist(board: Array<CharArray>, word: String): Boolean
}

class Solution:Sol {
    val DEBUG_MODE = false

    /*
    本來的想法：
    遞迴去做DFS，並用一個set來記錄走過的點
     */
    override fun exist(board: Array<CharArray>, word: String): Boolean {
        val m = board.size
        val n = board[0].size
        var result = false
        if(word.length > m*n)
            return result

        val firstChar = word[0]
        for(i in board.indices){
            for(j in board[i].indices){
                if(board[i][j] == firstChar) {
                    result = result || foo(board, word, i, j, 1, hashSetOf("${i},${j}"))
                    if(DEBUG_MODE)
                        println("=======================")
                }
            }
        }
        return result
    }

    /*
    boardProgressI, boardProgressJ = 目前版上位置
    wordProgressIndex = 這一階段要找的char的index
    iterated = 記錄走過的點
     */
    /*
    這樣的寫法其實有點醜，等於是把下一步的點位先做判斷，判斷是不是在board裡面，判斷有沒有走過，判斷是不是要找的字
    其實可以把這個判斷往下移一階，到終端再去判斷

    這邊也用一個set去記錄走過的點
     */
    private fun foo(board: Array<CharArray>, word: String, boardProgressI:Int, boardProgressJ:Int, wordProgressIndex:Int, iterated:MutableSet<String>):Boolean{
        if(DEBUG_MODE)
            println("board[$boardProgressI][$boardProgressJ]:${board[boardProgressI][boardProgressJ]}")
        if(wordProgressIndex >= word.length)
            return true

        if(isValidIndex(boardProgressI-1,boardProgressJ,board) // 合法的index
            && !iterated.contains("${boardProgressI-1},${boardProgressJ}") // 沒有走過
            && word[wordProgressIndex]==board[boardProgressI-1][boardProgressJ]  // 字有對到
            && foo(board, word, boardProgressI-1,boardProgressJ,
                wordProgressIndex+1,
                HashSet(iterated).apply { add("${boardProgressI-1},${boardProgressJ}") })
        ){
                return true
        }

        if(isValidIndex(boardProgressI+1,boardProgressJ,board) // 合法的index
            && !iterated.contains("${boardProgressI+1},${boardProgressJ}") // 沒有走過
            && word[wordProgressIndex]==board[boardProgressI+1][boardProgressJ]  // 字有對到
            && foo(board, word, boardProgressI+1,boardProgressJ,
                wordProgressIndex+1,
                HashSet(iterated).apply { add("${boardProgressI+1},${boardProgressJ}") })
        ){
            return true
        }

        if(isValidIndex(boardProgressI,boardProgressJ-1,board) // 合法的index
            && !iterated.contains("${boardProgressI},${boardProgressJ-1}") // 沒有走過
            && word[wordProgressIndex]==board[boardProgressI][boardProgressJ-1]  // 字有對到
            && foo(board, word, boardProgressI,boardProgressJ-1,
                wordProgressIndex+1,
                HashSet(iterated).apply { add("${boardProgressI},${boardProgressJ-1}") })
        ){
            return true
        }

        if(isValidIndex(boardProgressI,boardProgressJ+1,board) // 合法的index
            && !iterated.contains("${boardProgressI},${boardProgressJ+1}") // 沒有走過
            && word[wordProgressIndex]==board[boardProgressI][boardProgressJ+1]  // 字有對到
            && foo(board, word, boardProgressI,boardProgressJ+1,
                wordProgressIndex+1,
                HashSet(iterated).apply { add("${boardProgressI},${boardProgressJ+1}") })
        ){
            return true
        }

        return false
    }

    private fun isValidIndex(i:Int, j:Int, board:Array<CharArray>):Boolean{
        return i>=0 && i<board.size && j>=0 && j<board[0].size
    }
}

class Solution01:Sol {
    private val DEBUG_MODE = false
    override fun exist(board: Array<CharArray>, word: String): Boolean {
        val m = board.size
        val n = board[0].size
        var result = false
        if(word.length > m*n)
            return result

        val firstChar = word[0]
        for(i in board.indices){
            for(j in board[i].indices){
                if(board[i][j] == firstChar) {
                    result = result || foo(board, word, i, j, 0)
                    if(DEBUG_MODE)
                        println("=======================")
                }
            }
        }
        return result
    }

    /*
   改良過後的寫法，不再去提前判斷下一點，而是在每一階段把自己階段的管好，下一點的判斷留給下一層去做
   走過的點也可以利用表全部都是英文字母的特性，先暫存起來，設為一個非英文字元，四方位走完之後在設回來
    */
    private fun foo(board: Array<CharArray>, word: String, boardProgressI:Int, boardProgressJ:Int, wordProgressIndex:Int):Boolean {
        if (DEBUG_MODE)
            println("board[$boardProgressI][$boardProgressJ]:${board[boardProgressI][boardProgressJ]}")

        if (wordProgressIndex >= word.length)
            return true
        if (isValidIndex(boardProgressI, boardProgressJ,board)
            && word[wordProgressIndex]==board[boardProgressI][boardProgressJ]){
            val tmp = board[boardProgressI][boardProgressJ]
            board[boardProgressI][boardProgressJ] = '*'
            if(foo(board, word, boardProgressI+1, boardProgressJ, wordProgressIndex+1)
                ||foo(board, word, boardProgressI, boardProgressJ+1, wordProgressIndex+1)
                ||foo(board, word, boardProgressI-1, boardProgressJ, wordProgressIndex+1)
                ||foo(board, word, boardProgressI, boardProgressJ-1, wordProgressIndex+1))
                return true

            board[boardProgressI][boardProgressJ] = tmp
            return false
        }

        return false
    }

    private fun isValidIndex(i:Int, j:Int, board:Array<CharArray>):Boolean{
        return i>=0 && i<board.size && j>=0 && j<board[0].size
    }
}