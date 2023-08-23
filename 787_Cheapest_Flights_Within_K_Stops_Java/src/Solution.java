import java.util.*;

class SolutionBFS {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        HashMap<Integer, List<int[]>> table = new HashMap<>();
        for(int[] flight : flights){
            var from = flight[0];
            var to = flight[1];
            var price = flight[2];
            table.computeIfAbsent(from, k1 -> new ArrayList<>());
            table.get(from).add(new int[]{to, price});
        }

        var bfsQueue = new LinkedList<int[]>();
        bfsQueue.offer(new int[]{src,0});

        var minPrice = new int[n];
        Arrays.fill(minPrice, Integer.MAX_VALUE);
//        minPrice[src] = 0;

        var counter = k+1;
        var min = Integer.MAX_VALUE;

        while (!bfsQueue.isEmpty() && counter>0){
            var size = bfsQueue.size();
            for(int i=0;i<size;++i){
                int[] poll = bfsQueue.poll();
                int from = poll[0];
                var currentCost = poll[1];
                minPrice[from] = currentCost;

                if(table.get(from)==null) continue;

                for(int[] toArr : table.get(from)){
                    int to = toArr[0];
                    int price = toArr[1];
                    int newCost = currentCost + price;
                    if(to == dst){
                        min = Math.min(min, newCost);
                    }else if(minPrice[to]>newCost){
                        bfsQueue.offer(new int[]{to, newCost});
                    }
                }
            }
            --counter;
        }

        if(min==Integer.MAX_VALUE)
            return -1;
        else
            return min;
    }
}

class Solution {
    int INFINITY = 100000;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        var cost = new int[n];
        Arrays.fill(cost, INFINITY);
        cost[src] = 0;

        for(int i =0;i<=k;++i){
            var originalCost = Arrays.copyOf(cost, n);
            var modified = false;
            for(var flight:flights){
                var from = flight[0];
                var to = flight[1];
                var price = flight[2];
                if(originalCost[from] + price < cost[to]){
                    cost[to] = originalCost[from] + price;
                    modified = true;
                }
            }

            if(!modified) break;
        }

        return (cost[dst]==INFINITY) ? -1 : cost[dst];
    }
}