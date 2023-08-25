class Solution {
    int[][] pathMatrix;
    int n,m;
    int UNVISITED = 0;
    int result = 0;

    public int longestIncreasingPath(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        pathMatrix = new int[n][m];
        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                result = Math.max(result,dfs(matrix, i, j));
            }
        }
        return result;
    }

    int[][] dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    private int dfs(int[][] matrix, int i, int j){
        if(pathMatrix[i][j] != UNVISITED)
            return pathMatrix[i][j];

        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if(x<0 || x>=n || y<0 || y>=m || matrix[x][y]<=matrix[i][j]) continue;
                pathMatrix[i][j] = Math.max(pathMatrix[i][j], dfs(matrix, x, y));
        }

        return ++pathMatrix[i][j];
    }
}