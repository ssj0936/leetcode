import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        var n = matrix.length;
        var m = matrix[0].length;

        //binary search 1st time
        var head = 0;
        var tail = n-1;
        while (head<=tail){
            var mid = head + (tail-head)/2;
            if(matrix[mid][0] == target)
                return true;
            else if(matrix[mid][0] < target){
                head = mid+1;
            }else {
                tail = mid-1;
            }
        }

        var t = head-1;
        if(t<0 || matrix[t][m-1]<target) return false;

        head = 0;
        tail = m-1;

        while (head<=tail){
            var mid = head + (tail-head)/2;
            if(matrix[t][mid]==target)
                return true;
            else if(matrix[t][mid] < target){
                head = mid +1;
            }else {
                tail = mid -1;
            }
        }
        return false;
    }


}