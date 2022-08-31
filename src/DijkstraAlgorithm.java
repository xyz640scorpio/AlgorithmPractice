import java.util.*;
public class DijkstraAlgorithm {
    static class Edge{
        private int[] cost = new int[5];

        public Edge(int num) {
            Arrays.fill(cost, -1); // -1 represents unreachable
        }

        public Edge(int[] values) {
            for (int i = 0; i < cost.length; i++) {
                cost[i] = values[i];
            }
        }

        public void update(int node, int newCost) {
            cost[node] = newCost;
        }
    }

    private final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // graph: n * 3, graph[i] = [node1, node2, cost], num nodes num
    public int minRoute(List<Edge> edgeTo, int A, int B, int C) {
        // trace the shortest path of node and its previous node
        int[] path = new int[edgeTo.size()];
        Arrays.fill(path, -1);
        // find minDistance between A and B
        int minDist = dijkstra(edgeTo, A, B, path);
        // update Edge
        int node = B;
        while (path[node] != A) {
            int prevNode = path[node];
            edgeTo.get(node).update(prevNode, 0);
            edgeTo.get(prevNode).update(node, 0);
            node = prevNode;
        }

        // find minDistance between B and C
        Arrays.fill(path, -1);
        minDist += dijkstra(edgeTo, B, C, path);
        return minDist;
    }

    public int dijkstra(List<Edge> edgeTo, int A, int B, int[] path) {
        int[] dist = new int[edgeTo.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[A] = 0;
        // record the visited nodes
        boolean[] visited = new boolean[edgeTo.size()];
        // record the current minimum distance
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{A, 0});
        while (!pq.isEmpty()) {
            // node[0] -- node name, node[1] distTo node
            int[] node = pq.poll();
            visited[node[0]] = true;
            if (node[0] == B) break; // find target
            Edge e = edgeTo.get(node[0]);
            for (int nei = 0; nei < e.cost.length; nei++) {
                if (!visited[nei] && e.cost[nei] != -1) { // not visited and reachable
                    int newDist = node[1] + e.cost[nei];
                    if (newDist < dist[nei]) {
                        dist[nei] = newDist;
                        path[nei] = node[0];
                        pq.add(new int[]{nei, newDist});
                    }
                }
            }
        }
        return dist[B];
    }

    public static void main(String[] args) {
        List<Edge> edgeTo = new ArrayList<>();
        edgeTo.add(new Edge(new int[]{-1, 2, 3, -1, -1}));
        edgeTo.add(new Edge(new int[]{2, -1, 2, -1, 4}));
        edgeTo.add(new Edge(new int[]{3, 2, -1, 3, -1}));
        edgeTo.add(new Edge(new int[]{-1, -1, 3, -1, 2}));
        edgeTo.add(new Edge(new int[]{-1, 4,-1, 2, -1}));
        DijkstraAlgorithm o = new DijkstraAlgorithm();
        System.out.println(o.minRoute(edgeTo, 0, 2, 4));
    }
}