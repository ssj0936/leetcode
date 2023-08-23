class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        var dp = new int[text1.length()+1][text2.length()+1];

        for(int i=0;i<text1.length();++i){
            for(int j=0;j<text2.length();++j){
                var dpI = i+1;
                var dpJ = j+1;

                if(text1.charAt(i) == text2.charAt(j))
                    dp[dpI][dpJ] = dp[dpI-1][dpJ-1] + 1;
                else
                    dp[dpI][dpJ] = Math.max(dp[dpI-1][dpJ], dp[dpI][dpJ-1]);
            }
        }

        return dp[text1.length()][text2.length()];
    }
}