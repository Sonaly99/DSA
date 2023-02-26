public class BinaryTree {
    private TreeNode root;

    public BinaryTree(Integer[] values) {
        root = buildTree(values, 0);
    }

    private TreeNode buildTree(Integer[] values, int index) {
        if (index >= values.length || values[index] == null) {
            return null;
        }
        TreeNode node = new TreeNode(values[index]);
        node.left = buildTree(values, 2 * index + 1);
        node.right = buildTree(values, 2 * index + 2);
        return node;
    }

    public int getMinServiceCenters() {
        int[] count = new int[1];
        traverse(root, count);
        return count[0];
    }

    private boolean traverse(TreeNode node, int[] count) {
        if (node == null) {
            return false;
        }
        boolean hasCenter = traverse(node.left, count) || traverse(node.right, count);
        if (!hasCenter && (node.left == null || node.right == null)) {
            count[0]++;
            return true;
        }
        return hasCenter;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Integer[] tree = {0, 0, null, 0, null, 0, null, null, 0};
        BinaryTree binaryTree = new BinaryTree(tree);
        int minServiceCenters = binaryTree.getMinServiceCenters();
        System.out.println(minServiceCenters); // Output: 2
    }

}
