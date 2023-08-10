import java.util.Arrays;
import java.util.LinkedList;

public class Solution{
    int mod = 1_000_000_007;
    public int numMusicPlaylists(int n, int goal, int k) {
        long[][] dp = new long[goal+1][n+1];

        for(int i=0;i<=goal;++i){
            for(int j=0;j<=n;++j){
                if(i==0 && j==0) dp[i][j] = 1;
                else if(i==0 || j==0) dp[i][j]=0;
                else if(j>i) dp[i][j] = 0;
                else {
                    long thisSongListenedBefore = (dp[i - 1][j - 1] * (n - (j - 1)));
                    long thisSongNotListenedBefore = (dp[i-1][j] * Math.max(0,(j-k)));
                    dp[i][j] = (thisSongListenedBefore + thisSongNotListenedBefore)%mod;
                }
            }
        }

        return (int)dp[goal][n];
    }
}
