class Solution {
    fun myPow(x: Double, n: Int): Double {
        if(x == 0.0 || x == 1.0) return x

        if(n == 0) return 1.0
        if(n == 1) return x
        if(n == -1) return 1/x

        var res = pow(x, n)

        return if(n<0) (1/res) else res
    }

    fun pow(x: Double, n: Int):Double{
        if(n==0) return 1.0
        if(n==1) return x

        var res = pow(x*x, n/2)
        if(n%2 != 0)
            res *= x
        return res
    }
}