import java.util.*;

public class Main {
    //#1
    public int getOperationCount(int nodeCount, List<Integer> connectFrom, List<Integer> connectTo){
        var edgeCount = connectFrom.size();
        if(edgeCount < nodeCount-1)
            return -1;

        var father = new int[nodeCount];
        for(int i=0;i<father.length;++i){
            father[i] = i;
        }

        var takenEdgeCount = 0;
        for(int i=0;i<connectFrom.size();++i){
            var nodeA = connectFrom.get(i);
            var nodeB = connectTo.get(i);
            if(findRoot(father, nodeA) == findRoot(father, nodeB)) continue;

            union(father, nodeA, nodeB);
            ++takenEdgeCount;
        }

        return (nodeCount-1) - takenEdgeCount;
    }

    private int findRoot(int[] father, int node){
        if(father[node] == node)
            return node;

        father[node] = findRoot(father, father[node]);
        return father[node];
    }

    private void union(int[] father, int nodeA, int nodeB){
        var rootA = findRoot(father, nodeA);
        var rootB = findRoot(father, nodeB);

        father[rootA] = rootB;
    }


    //#2
    int result = 0;
    private int getFillCount(List<String> board){
        var n = board.size();
        var m = board.get(0).length();
        boolean[][] visited = new boolean[n][m];

        for(int i=0; i<n;++i){
            for(int j=0; j<m;++j){
                bfs(board, visited, i, j);
            }
        }

        return result;
    }

    int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
    private void bfs(List<String> board, boolean[][] visited, int i, int j){
        if(visited[i][j]) return;

        visited[i][j] = true;
        var n = board.size();
        var m = board.get(0).length();
        char c = board.get(i).charAt(j);

        var queue = new LinkedList<int[]>();
        queue.offer(new int[]{i,j});
        while (!queue.isEmpty()){
            int[] node = queue.poll();

            for(int[] dir :dirs){
                var newI = node[0] + dir[0];
                var newJ = node[1] + dir[1];

                if(newI>=0 && newI<n
                    && newJ>=0 && newJ<m
                    && board.get(newI).charAt(newJ) == c
                    && !visited[newI][newJ]){
                    visited[newI][newJ] = true;
                    queue.offer(new int[]{newI, newJ});
                }
            }
        }

        ++result;
    }

    //#3
    public int getCentral(int nodeCount, List<Integer> connectFrom, List<Integer> connectTo, List<Integer> weight, int x, int y, int z){
        List<Set<Integer>> graph = new ArrayList<>();
        int[] connectedCount = new int[nodeCount];
        int edgeCount = connectFrom.size();

        for(int i=0;i<nodeCount;++i){
            graph.add(new HashSet<>());
        }

        for(int i=0;i<edgeCount;++i){
            var from = connectFrom.get(i);
            var to = connectTo.get(i);
            var w = weight.get(i);
            ++connectedCount[from];
            ++connectedCount[to];

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        //phase 1
        var queue = new LinkedList<Integer>();
        for(int i=0;i<nodeCount;++i){
            if(connectedCount[i]==1 && !isXYZ(i,x,y,z))
                queue.offer(i);
        }

        while (!queue.isEmpty()){
            var node = queue.poll();
            for(var nodeConnectTo : graph.get(node)){
                if(connectedCount[nodeConnectTo]>0 && !isXYZ(nodeConnectTo,x,y,z)){
                    //degree -1
                    --connectedCount[node];
                    --connectedCount[nodeConnectTo];

                    if(connectedCount[nodeConnectTo] == 1)
                        queue.offer(nodeConnectTo);
                }
            }
        }

        //phase 2
        queue.offer(x);
        queue.offer(y);
        queue.offer(z);

        //0:x 1:y, 2:z
        boolean[][] rootOf = new boolean[nodeCount][3];
        rootOf[x][0] = true;
        rootOf[y][1] = true;
        rootOf[z][2] = true;

        while (queue.size()>2){
            var node = queue.poll();
            for(var nodeConnectTo : graph.get(node)){
                if(connectedCount[nodeConnectTo]>0 && !isXYZ(nodeConnectTo,x,y,z)){
                    //degree -1
                    --connectedCount[node];
                    --connectedCount[nodeConnectTo];

                    rootOf[nodeConnectTo][0] = rootOf[nodeConnectTo][0]||rootOf[node][0];
                    rootOf[nodeConnectTo][1] = rootOf[nodeConnectTo][1]||rootOf[node][1];
                    rootOf[nodeConnectTo][2] = rootOf[nodeConnectTo][2]||rootOf[node][2];

                    if(connectedCount[nodeConnectTo] == 1)
                        queue.offer(nodeConnectTo);
                }
            }
        }

        if(queue.size()==1)
            return queue.poll();
        else{
            var point1 = queue.poll();
            var point2 = queue.poll();
            if(isRootOfTwoOrMore(rootOf[point1]))
                return point1;
            else
                return point2;
        }
    }

    private boolean isRootOfTwoOrMore(boolean[] rootOf){
        var trueCount = 0;
        for(var r : rootOf){
            if(r) ++trueCount;
        }
        return trueCount>=2;
    }

    private boolean isXYZ(Integer i, int x, int y, int z){
        return i == x || i==y || i==z;
    }

}
