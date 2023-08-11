class Solution {
    int[][] dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    boolean[][] visited;
    int n,m;
    int blockCount = 0;
    int result = 0;
    int startX,startY/*, endX,endY*/;
    int pathLen;
    public int uniquePathsIII(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];

        for(int i =0; i<n;++i){
            for(int j=0;j<m;++j){
                if(grid[i][j]==1) {
                    startX = i;
                    startY = j;
                }else if(grid[i][j]==-1){
                    visited[i][j] = true;
                    ++blockCount;

                }
            }
        }
        pathLen = m*n - blockCount-1;

        visited[startX][startY] = true;
        dfs(grid, startX, startY, 0);
        return result;
    }

    private void dfs(int[][] grid, int i, int j, int length){
        if(grid[i][j] == 2 && length == pathLen){
            ++result;
            return;
        }

        for(int[] dir : dirs){
            var newX = i + dir[0];
            var newY = j + dir[1];
            if(newX>=0 && newX<n && newY>=0 && newY<m && !visited[newX][newY]){
                visited[newX][newY] = true;
                dfs(grid, newX, newY, length+1);
                visited[newX][newY] = false;
            }
        }
    }
}