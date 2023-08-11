class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        var parent = new int[edges.length+1];
        for(int i=0;i<edges.length+1;++i){
            parent[i] = i;
        }

        for(int[] edge : edges){
            var node1 = edge[0];
            var node2 = edge[1];

            if(findRoot(parent, node1) == findRoot(parent, node2)){
                return edge;
            }else {
                union(parent, node1, node2);
            }
        }

        return null;
    }

    private int findRoot(int[] parent, int node){
        if(parent[node]==node)
            return node;

        parent[node] = findRoot(parent, parent[node]);
        return parent[node];
    }

    private void union(int[] parent, int node1, int node2){
        var root1 = findRoot(parent, node1);
        var root2 = findRoot(parent, node2);
        parent[root1] = root2;
    }
}