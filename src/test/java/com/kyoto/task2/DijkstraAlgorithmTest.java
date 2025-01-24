package com.kyoto.task2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.kyoto.task2.DijkstraAlgorithm.dijkstra;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraAlgorithmTest {

    @Test
    void testShortestPathCompareWithConst() {
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        // Проверка кратчайшего пути от вершины 0 до вершины 4
        int start = 0;
        int end = 4;
        int[] expectedPath = {0, 7, 6, 5, 4};
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), expectedPath);

        start = 3;
        end = 6;
        expectedPath = new int[]{3, 2, 5, 6};
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), expectedPath);

        start = 0;
        end = 8;
        expectedPath = new int[]{0, 1, 2, 8};
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), expectedPath);

        start = 7;
        end = 3;
        expectedPath = new int[]{7, 6, 5, 2, 3};
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), expectedPath);
    }

    @Test
    void testShortestPathCompareWithAnalog() {
        int[][] graph = {
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };

        int start = 3;
        int end = 6;
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), findShortestPath(graph, start, end));

        start = 2;
        end = 7;
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), findShortestPath(graph, start, end));

        start = 6;
        end = 3;
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), findShortestPath(graph, start, end));

        start = 8;
        end = 1;
        assertArrayEquals(convertListToArr(dijkstra(graph, start, end)), findShortestPath(graph, start, end));
    }


    private int[] findShortestPath(int[][] graph, int start, int end) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        int[] previous = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        previous[start] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    previous[v] = u;
                }
            }
        }

        return constructPath(previous, end);
    }

    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private int[] constructPath(int[] previous, int end) {
        List<Integer> path = new ArrayList<>();
        int current = end;
        while (current != -1) {
            path.add(current);
            current = previous[current];
        }

        Collections.reverse(path);
        return path.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] convertListToArr(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
