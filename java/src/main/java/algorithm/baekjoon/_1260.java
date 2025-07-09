package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class _1260 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]); // 정점의 개수
        int m = Integer.parseInt(line[1]); // 간선의 개수
        int v = Integer.parseInt(line[2]); // 시작 정점

        int[][] graph = new int[n+1][n+1];

        for (int i=0 ; i<m ; i++) {
            line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            graph[a][b] = graph[b][a] = 1; // 간선이 존재
        }

        boolean[] visited = new boolean[n+1];
        runDFS(graph, visited, v);
        bw.write("\n");
        visited = new boolean[n+1];
        runBFS(graph, visited, v);

        bw.flush();
        bw.close();
    }

    private static void runDFS(int[][] graph, boolean[] visited, int v) throws Exception {
        visited[v] = true;
        bw.write(v + " ");
        for (int i=1 ; i<graph.length ; i++) {
            if (graph[v][i] == 1 && !visited[i]) {
                runDFS(graph, visited, i);
            }
        }
    }

    private static void runBFS(int[][] graph, boolean[] visited, int v) throws Exception {
        Queue<Integer> q = new LinkedList<>();
        visited[v]= true;
        q.add(v);

        while (!q.isEmpty()) {
            int value = q.poll();
            bw.write(value + " ");
            for (int i=1 ; i<graph.length ; i++) {
                if (graph[value][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
