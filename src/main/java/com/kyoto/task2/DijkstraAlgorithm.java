package com.kyoto.task2;

import java.util.*;

public class DijkstraAlgorithm {

    private static final int INF = Integer.MAX_VALUE;

    public static List<Integer> dijkstra(int[][] graph, int start, int end) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] previous = new int[n];

        Arrays.fill(dist, INF);
        dist[start] = 0;
        previous[start] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != INF &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    previous[v] = u;
                }
            }
        }

        return formatPath(previous, end);
    }

    private static int minDistance(int[] dist, boolean[] visited) {
        int min = INF;
        int minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private static List<Integer> formatPath(int[] previous, int end) {
        List<Integer> path = new ArrayList<>();
        int current = end;
        while (current != -1) {
            path.add(current);
            current = previous[current];
        }

        Collections.reverse(path);

        return path;
    }
}
