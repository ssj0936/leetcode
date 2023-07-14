class Solution {
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

    private fun helper(board: Array<CharArray>, blanks:List<IntArray>, index:Int){
        var candidate = getCandidate(board, blanks, index)
    }

    private fun getCandidate(board: Array<CharArray>, blanks:List<IntArray>, index:Int):List<Int>{
        val x = blanks[index][0]
        val y = blanks[index][1]

        var record = BooleanArray(9)

        //row check
        for(i in 0 until 9) {
            if(board[x][i]!='.')
            record[board[x][i]-'1']
        }
    }
}