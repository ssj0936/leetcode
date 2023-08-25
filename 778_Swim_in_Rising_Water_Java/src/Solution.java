class Solution {
    int n,m;
    //(x,y) for each node
    int[][] table;
    int[] parent;
    int[][] dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    public int swimInWater(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        table = new int[n*m][2];
        for(int i=0;i<n;++i){
            for(int j=0; j<m;++j){
                table[grid[i][j]] = new int[]{i,j};
            }
        }

        parent = new int[n*m];
        for(int i=0;i<n*m;++i){
            parent[i] = i;
        }

        var startPoint = grid[0][0];
        var endPointValue = grid[n-1][m-1];
        for(int i=1;i<n*m;++i){
            for(int[] dir : dirs){
                var newX = table[i][0]+dir[0];
                var newY = table[i][1]+dir[1];
                if(newX>=0 && newX<n && newY>=0 && newY<m && grid[newX][newY]<i){
                    union(i, grid[newX][newY]);
                }
            }
            if(isSameRoot(startPoint, endPointValue))
                return i;
        }
        return n*m-1;
    }

    private boolean isSameRoot(int node1, int node2){
        return findRoot(node1) == findRoot(node2);
    }

    private int findRoot(int node){
        if(parent[node]==node)
            return node;

        parent[node] = findRoot(parent[node]);
        return parent[node];
    }

    private void union(int node1, int node2){
        var root1 = findRoot(node1);
        var root2 = findRoot(node2);
        if(!isSameRoot(root1,root2))
            parent[root1] = root2;
    }

    private boolean isConnected(int[] node1, int[] node2){
        if(node1[0] == node2[0]){
            return (node1[1] == node2[1]+1 || node2[1] == node1[1]+1);
        }else if(node1[1] == node2[1]){
            return (node1[0] == node2[0]+1 || node2[0] == node1[0]+1);
        }
        return false;
    }

}