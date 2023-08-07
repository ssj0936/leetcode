import java.util.Arrays;
import java.util.LinkedList;

class Solution {
    int result = 0;
    public int numMusicPlaylists(int n, int goal, int k) {
        var prevK = new int[n];
        Arrays.fill(prevK, 0);

        var visited = new int[n];
        Arrays.fill(visited, 0);

        var stack = new LinkedList<Integer>();
        backTracking(n, goal, k,0, prevK, visited, stack);
        return result;
    }

    private void backTracking(int n, int goal, int k, int index, int[] prevK, int[] visited, LinkedList<Integer> stack){
        if(index>=goal){
            if(Arrays.stream(visited).noneMatch(it -> it ==0)) {
                ++result;
            }
            return;
        }

        for(int i=0; i<n;++i){
            if(k>0 && prevK[i]>0) continue;

            stack.push(i);
            ++prevK[i];
            ++visited[i];
            var tmp = -1;
            if(k>0 && index- k >=0){
                tmp = stack.get(stack.size()-1 - (index- k));
                --prevK[tmp];
            }
            backTracking(n, goal, k, index+1, prevK, visited, stack);

            if(k>0 && index- k >=0){
                ++prevK[tmp];
            }
            --visited[i];
            --prevK[i];
            stack.pop();
        }
    }
}
