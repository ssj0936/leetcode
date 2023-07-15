class SolutionOri {
    fun solveSudoku(board: Array<CharArray>): Unit {
        val blanks = mutableListOf<IntArray>().apply {
            for(i in 0 until 9) {
                for (j in 0 until 9) {
                    if(board[i][j]=='.') this.add(intArrayOf(i,j))
                }
            }
        }

        helper(board, blanks, 0)
    }

    private fun helper(board: Array<CharArray>, blanks:List<IntArray>, index:Int):Boolean{
        if(index == blanks.size)
            return true

        val candidates = getCandidate(board, blanks, index)
        val x = blanks[index][0]
        val y = blanks[index][1]

        for(candidate in candidates){
            board[x][y] = '0' + candidate

            if(helper(board, blanks, index+1))
                return true

            board[x][y] = '.'
        }
        return false
    }

    private fun getCandidate(board: Array<CharArray>, blanks:List<IntArray>, index:Int):List<Int>{
        val x = blanks[index][0]
        val y = blanks[index][1]
        val record = BooleanArray(9)

        for(i in 0 until 9) {
            //row check
            if(board[x][i]!='.')
                record[board[x][i]-'1'] = true

            //column check
            if(board[i][y]!='.')
                record[board[i][y]-'1'] = true
        }


        for(i in 0 until 3) {
            for (j in 0 until 3) {
                if(board[3*(x/3)+i][3*(y/3)+j]!='.')
                    record[board[3*(x/3)+i][3*(y/3)+j]-'1'] = true
            }
        }

        return mutableListOf<Int>().apply {
            record.forEachIndexed { index, numbered ->
                if(!numbered) add(index+1)
            }
        }
    }
}

class Solution {
    private val row = IntArray(9)
    private val column = IntArray(9)
    private val block = IntArray(9)

    fun solveSudoku(board: Array<CharArray>): Unit {
        val blanks = mutableListOf<IntArray>()

        for (i in 0 until 9) {
            for (j in 0 until 9) {
                when(board[i][j]){
                    '.'->{
                        blanks.add(intArrayOf(i, j))
                    }
                    else->{
                        val n = board[i][j]-'0'
                        val blockIndex = getBlockIndex(i, j)

                        with(1 shl n) {
                            row[i] = row[i] or this
                            column[j] = column[j] or this
                            block[blockIndex] = block[blockIndex] or this
                        }
                    }
                }
            }
        }

        helper(board, blanks, 0)
    }

    private fun helper(board: Array<CharArray>, blanks:List<IntArray>, index:Int):Boolean{
        if(index == blanks.size)
            return true
        val x = blanks[index][0]
        val y = blanks[index][1]
        val blockIndex = getBlockIndex(x, y)

        val candidates = getCandidate(x,y)
        for(candidate in candidates){
            val originRow = row[x]
            val originColumn = column[y]
            val originBlock = block[blockIndex]
            with(1 shl candidate){
                row[x] = row[x] or this
                column[y] = column[y] or this
                block[blockIndex] = block[blockIndex] or this
            }

            board[x][y] = '0' + candidate
            if(helper(board, blanks, index+1))
                return true


            row[x] = originRow
            column[y] = originColumn
            block[blockIndex] = originBlock
        }
        board[x][y] = '.'

        return false
    }

    private fun getBlockIndex(i:Int, j:Int):Int = 3*(i/3)+(j/3)

    private fun getCandidate(i:Int, j:Int):List<Int>{
        return mutableListOf<Int>().apply {
            for(index in 1 .. 9){
                if(((row[i] shr index) and 1 == 1)
                    || ((column[j] shr index) and 1 == 1)
                    || ((block[getBlockIndex(i, j)] shr index) and 1 == 1)
                ) continue

                this.add(index)
            }
        }
    }
}