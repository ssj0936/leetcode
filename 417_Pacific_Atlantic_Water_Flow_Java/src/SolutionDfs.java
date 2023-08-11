import java.util.ArrayList;
import java.util.List;

public class Solution{
    int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int n,m;
    boolean[][] visitedPacific,visitedAtlantic;
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        n = heights.length;
        m = heights[0].length;
        visitedPacific = new boolean[n][m];
        visitedAtlantic = new boolean[n][m];

        //left
        for(int i=0;i<n;++i){
            dfs(heights, i, 0, true, -1);
        }

        //top
        for(int i=0;i<m;++i){
            dfs(heights, 0,i, true, -1);
        }

        //right
        for(int i=0;i<n;++i){
            dfs(heights, i,m-1, false,-1);
        }

        //bottom
        for(int i=0; i<m; ++i){
            dfs(heights, n-1, i, false, -1);
        }

        return result;

    }

    private void dfs(int[][] heights, int i, int j, boolean isPacific, int prevHeight){
        if(i<0 || i>=n ||j<0 || j>=m) return;
        if(isPacific && visitedPacific[i][j]) return;
        if(!isPacific && visitedAtlantic[i][j]) return;
        if(heights[i][j] < prevHeight) return;

        if(isPacific)
            visitedPacific[i][j] = true;
        else {
            visitedAtlantic[i][j] = true;
            if(visitedPacific[i][j])
                result.add(new ArrayList<>(List.of(i, j)));
        }

        for(int[] dir:dirs){
            var newX = i + dir[0];
            var newY = j + dir[1];

            dfs(heights, newX, newY, isPacific, heights[i][j]);
        }
    }
}