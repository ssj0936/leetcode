class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node){
        if(node==null)
            return 0;

        var leftChildLength = dfs(node.left);
        var rightChildLength = dfs(node.right);
        var maxPathStartFromThisNode = Math.max(Math.max(leftChildLength, rightChildLength),0) + node.val;
        var pathWithThisNodeMiddle = leftChildLength + rightChildLength + node.val;
        max = Math.max(max,Math.max(maxPathStartFromThisNode, pathWithThisNodeMiddle));

        return maxPathStartFromThisNode;
    }
}