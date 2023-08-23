
class Solution {
    private final int WITHOUT_SHARE = 0;
    private final int WITH_SHARE = 1;
    private final int COOL_DOWN = 2;

    public int maxProfit(int[] prices) {
        var dp = new int[prices.length][3];
        dp[0][WITH_SHARE] = -prices[0];

        for(int i=1;i<prices.length;++i){
            //without share prev, or sell today
            dp[i][WITHOUT_SHARE] = Math.max(dp[i-1][WITHOUT_SHARE], dp[i-1][WITH_SHARE] + prices[i]);

            //with share prev, or cool down yesterday
            dp[i][WITH_SHARE] = Math.max(dp[i-1][WITH_SHARE], dp[i-1][COOL_DOWN] - prices[i]);

            //cooldown prev, or just cooldown
            dp[i][COOL_DOWN] = Math.max(dp[i-1][COOL_DOWN], dp[i-1][WITHOUT_SHARE]);
        }

        return Math.max(dp[prices.length-1][WITHOUT_SHARE], dp[prices.length-1][COOL_DOWN]);
    }
}