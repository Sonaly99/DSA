import java.util.*;

public class NetworkDevices {

    public static List<Integer> findImpactedDevices(int[][] edges, int target) {
        List<Integer> impactedDevices = new ArrayList<>();
        Map<Integer, List<Integer>> graph = buildGraph(edges);
        Set<Integer> visited = new HashSet<>();
        dfs(graph, target, visited);
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (visited.contains(edge[0]) && visited.contains(edge[1])) {
                continue;
            }
            if (visited.contains(edge[0])) {
                impactedDevices.add(edge[1]);
            }
            if (visited.contains(edge[1])) {
                impactedDevices.add(edge[0]);
            }
        }
        return impactedDevices;
    }

    public static Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            List<Integer> neighbors = graph.getOrDefault(from, new ArrayList<>());
            neighbors.add(to);
            graph.put(from, neighbors);
            neighbors = graph.getOrDefault(to, new ArrayList<>());
            neighbors.add(from);
            graph.put(to, neighbors);
        }
        return graph;
    }

    public static void dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        visited.add(node);
        List<Integer> neighbors = graph.get(node);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    dfs(graph, neighbor, visited);
                }
            }
        }
    }
    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        int target = 4;
        List<Integer> impactedDevices = findImpactedDevices(edges, target);
        System.out.println("Impacted Device List: " + impactedDevices);
    }
}
