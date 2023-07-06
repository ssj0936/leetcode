fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    val solution = Solution()

    val n = 4
    println(solution.solveNQueens(n))
}

class Solution {
    private val finalResult = mutableListOf<List<String>>()
    val colorQ = Int.MAX_VALUE
    fun solveNQueens(n: Int): List<List<String>> {

        val matrix = Array(n){ Array(n){-1} }
        helper(matrix, n, 0)
        return finalResult
    }

    private fun helper(matrix:Array<Array<Int>>, n:Int, path:Int){
        if(path == n){
            converter(matrix)
        }else{
            for(i in 0 until n){
                if(matrix[path][i] == -1){
                    draw(matrix, n,path, path, i)
                    helper(matrix, n, path+1)
                    undraw(matrix, n,path, path, i)
                }
            }
        }
    }

    private fun draw(matrix:Array<Array<Int>>, size:Int, color:Int, i:Int, j:Int){
        for(k in matrix.indices){
            if(k == i){
                for(l in matrix.indices){
                    if(matrix[k][j]==-1) matrix[k][j] = color
                }
            }else{
                if(matrix[k][j]==-1) matrix[k][j] = color

                val diff = k-i
                if(j+diff in 0 until size && matrix[k][j+diff]==-1) matrix[k][j+diff] = color
                if(j-diff in 0 until size && matrix[k][j-diff]==-1) matrix[k][j-diff] = color
            }
        }

        matrix[i][j] = colorQ
    }

    private fun undraw(matrix:Array<Array<Int>>, size:Int, color:Int, i:Int, j:Int){
        for(k in matrix.indices){
            if(k == i){
                for(l in matrix.indices){
                    if(matrix[k][j]==color) matrix[k][j] = -1
                }
            }else{
                if(matrix[k][j]==color) matrix[k][j] = -1

                val diff = k-i
                if(j+diff in 0 until size && matrix[k][j+diff]==color) matrix[k][j+diff] = -1
                if(j-diff in 0 until size && matrix[k][j-diff]==color) matrix[k][j-diff] = -1
            }
        }

        matrix[i][j] = -1
    }

    private fun converter(matrix:Array<Array<Int>>){
        val result = mutableListOf<String>()
        for(row in matrix){
            val stringBuilder = StringBuilder()
            for(el in row){
                stringBuilder.append(if(el==colorQ)'Q' else '.')
            }
            result.add(stringBuilder.toString())
        }
        finalResult.add(result)
    }
}