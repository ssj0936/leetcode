/*
59. Spiral Matrix II(Medium)
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:
Input: n = 1
Output: [[1]]

Constraints:

1 <= n <= 20
 */
class Solution {
    fun generateMatrix(n: Int): Array<IntArray> {
        val result = Array(n){IntArray(n){0} }

        var i1 = 0
        var i2 = -1
        //0->right 1->down 2->left 3->right
        var moveStatus =0
        for(i in 1 .. n*n) {
            when (moveStatus){
                0->{
                    //填數字
                    result[i1][++i2] = i

                    //檢查要不要換方向
                    if(1+i2 > n-1 || result[i1][1+i2] != 0)
                        moveStatus = 1
                }

                1->{
                    //填數字
                    result[++i1][i2] = i

                    //檢查要不要換方向
                    if(1+i1 > n-1 || result[1+i1][i2] != 0)
                        moveStatus = 2
                }

                2->{
                    //填數字
                    result[i1][--i2] = i

                    //檢查要不要換方向
                    if(i2-1 < 0 || result[i1][i2-1] != 0)
                        moveStatus = 3
                }

                3->{
                    //填數字
                    result[--i1][i2] = i

                    //檢查要不要換方向
                    if(i1-1 < 0 || result[i1-1][i2] != 0)
                        moveStatus = 0
                }
            }
        }
        return result
    }
}

fun main(args: Array<String>) {
    val n = 7
    val result = Solution().generateMatrix(n)

    for(r in result){
        println(r.contentToString())
    }

    println("Hello World!")
}