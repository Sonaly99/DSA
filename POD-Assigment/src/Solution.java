import java.util.*;

public class Solution {

    public int[] findNearestAncestors(int[] values, int[][] edges) {
        // Build a mapping from node to its parent
        int n = values.length;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            parent[edge[1]] = edge[0];
        }

        // Build a mapping from node to its nearest ancestor with a relatively prime value
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < n; i++) {
            int node = i;
            while (node != -1) {
                if (gcd(values[i], values[node]) == 1) {
                    result[i] = node;
                    break;
                }
                node = parent[node];
            }
        }

        return result;
    }

    // Compute the greatest common divisor of two integers
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static void main(String[] args) {
        int[] values = {3, 2, 6, 6, 4, 7, 12};
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}};
        Solution sol = new Solution();
        int[] result = sol.findNearestAncestors(values, edges);
        System.out.println(Arrays.toString(result)); // Output: [-1, 0, -1, -1, 0, 2, -1]
    }
}
