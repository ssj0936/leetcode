
class Solution {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val checked = HashSet<String>()
        for(i in 0 until 9){
            for(j in 0 until 9){
                if(board[i][j]==DOT) continue

                val num = board[i][j]-'0'
                val keyRow = "row_${i}_${num}"
                val keyColumn = "column_${j}_${num}"
                val keyBlock = "block_${(j/3)+(3*(i/3))}_${num}"
                if(checked.contains(keyRow) || checked.contains(keyColumn) || checked.contains(keyBlock))
                    return false
                checked.apply {
                    add(keyRow)
                    add(keyColumn)
                    add(keyBlock)
                }
            }
        }
        return true
    }
}
const val DOT = '.'
class Solution{
    val ROW = 0
    val COLUMN = 1
    val BLOCK = 2
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val checked = Array(3){IntArray(9)}
        for(i in 0 until 9){
            for(j in 0 until 9){
                if(board[i][j]==DOT) continue

                val num = board[i][j]-'0'
                val pos = 1 shl (num-1)
                //row
                if(checked[ROW][i] and pos != 0)
                    return false
                checked[ROW][i] = checked[ROW][i] or pos

                //column
                if(checked[COLUMN][j] and pos != 0)
                    return false
                checked[COLUMN][j] = checked[COLUMN][j] or pos

                //block
                val blockNum = (j/3)+(3*(i/3))
                if(checked[BLOCK][blockNum] and pos != 0)
                    return false
                checked[BLOCK][blockNum] = checked[BLOCK][blockNum] or pos
            }
        }
        return true
    }
}

class SolutionOri {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        for(i in 0 until 9){
            val rowCount = IntArray(9)
            val columnCount = IntArray(9)
            for(j in 0 until 9){
                if(board[i][j]!=DOT && ++rowCount[board[i][j]-'1']>1)
                    return false
                if(board[j][i]!=DOT && ++columnCount[board[j][i]-'1']>1)
                    return false
            }
        }

        for(i in 0 until 9 step 3){
            for(j in 0 until 9 step 3){
                val count = IntArray(9)
                for(k in 0 until 3){
                    for(l in 0 until 3){
                        if(board[i+k][j+l]!=DOT && ++count[board[i+k][j+l]-'1']>1)
                            return false
                    }
                }
            }
        }

        return true
    }
}