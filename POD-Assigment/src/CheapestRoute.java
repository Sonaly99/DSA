import java.util.*;

public class CheapestRoute {

    static class Edge {
        int source;
        int destination;
        int time;
        int cost;

        public Edge(int source, int destination, int time, int cost) {
            this.source = source;
            this.destination = destination;
            this.time = time;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        int time;
        int cost;

        public Node(int id, int time, int cost) {
            this.id = id;
            this.time = time;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cost != other.cost) {
                return Integer.compare(this.cost, other.cost);
            }
            return Integer.compare(this.time, other.time);
        }
    }

    public static int cheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        // Create an adjacency list to represent the graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int sourceNode = edge[0];
            int destinationNode = edge[1];
            int time = edge[2];
            int cost = charges[destinationNode];
            Edge e = new Edge(sourceNode, destinationNode, time, cost);
            List<Edge> list = graph.getOrDefault(sourceNode, new ArrayList<>());
            list.add(e);
            graph.put(sourceNode, list);
        }

        // Initialize the priority queue and the distance and visited arrays
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0, charges[source]));
        int[] dist = new int[charges.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = charges[source];
        boolean[] visited = new boolean[charges.length];

        // Dijkstra's algorithm
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int id = node.id;
            if (visited[id]) {
                continue;
            }
            visited[id] = true;
            if (id == destination) {
                return node.cost;
            }
            if (node.time > timeConstraint) {
                continue;
            }
            for (Edge edge : graph.getOrDefault(id, new ArrayList<>())) {
                int nextId = edge.destination;
                int nextTime = node.time + edge.time;
                int nextCost = node.cost + edge.cost;
                if (!visited[nextId] && nextTime <= timeConstraint && nextCost < dist[nextId]) {
                    pq.offer(new Node(nextId, nextTime, nextCost));
                    dist[nextId] = nextCost;
                }
            }
        }

        // If destination is unreachable, return -1
        return -1;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] charges = {10,2,3,25,25,4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;
        int cheapestCost = cheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println(cheapestCost); // Output: 64
    }
}
