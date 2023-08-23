import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    final int VISITED = 1;

    int[][] dirs = new int[][]{{-1,0},{0,1},{1,0}, {0,-1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        var n = heights.length;
        var m = heights[0].length;
        int[][] visitedPacific = new int[n][m];
        int[][] visitedAtlantic = new int[n][m];

        var queue = new LinkedList<int[]>();
        var result = new ArrayList<List<Integer>>();
        //PACIFIC
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, 0});
            visitedPacific[i][0] = VISITED;
        }
        for (int i = 0; i < m; ++i) {
            queue.offer(new int[]{0, i});
            visitedPacific[0][i] = VISITED;
        }

        while (!queue.isEmpty()) {
            var pop = queue.poll();
            for (int[] dir : dirs) {
                var x = pop[0] + dir[0];
                var y = pop[1] + dir[1];
                if (x >= 0 && x < n && y >= 0 && y < m
                        && visitedPacific[x][y] != VISITED
                        && heights[x][y] >= heights[pop[0]][pop[1]]) {
                    visitedPacific[x][y] = VISITED;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        //ATLANTIC
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, m - 1});
            visitedAtlantic[i][m - 1] = VISITED;
        }

        for (int i = 0; i < m; ++i) {
            queue.offer(new int[]{n - 1, i});
            visitedAtlantic[n - 1][i] = VISITED;
        }

        while (!queue.isEmpty()) {
            var pop = queue.poll();

            for (int[] dir : dirs) {
                var x = pop[0] + dir[0];
                var y = pop[1] + dir[1];
                if (x >= 0 && x < n && y >= 0 && y < m
                        && visitedAtlantic[x][y] != VISITED
                        && heights[x][y] >= heights[pop[0]][pop[1]]) {
                    visitedAtlantic[x][y] = VISITED;
                    queue.offer(new int[]{x, y});
                }
            }
        }

        for(int i=0;i<n;++i){
            for(int j=0;j<m;++j){
                if(visitedPacific[i][j]==1 && visitedAtlantic[i][j]==1)
                    result.add(new ArrayList<>(List.of(i,j)));
            }
        }

        return result;
    }
}