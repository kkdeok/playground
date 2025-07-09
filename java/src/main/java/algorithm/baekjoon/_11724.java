package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _11724 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int[][] graph = new int[n+1][n+1];
        for (int i=0 ; i<m ; i++) {
            line = br.readLine().split(" ");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            graph[x][y] = graph[y][x] = 1;
        }

        boolean[] visited = new boolean[n+1];
        int count = 0;
        for (int i=1 ; i<=n ; i++) {
            if (!visited[i]) {
                doDFS(graph, visited, i);
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static void doDFS(int[][] graph, boolean[] visited, int v) {
        visited[v] = true;
        for (int i=1 ; i<graph.length ; i++) {
            if (graph[v][i] == 1 && !visited[i]) {
                doDFS(graph, visited, i);
            }
        }
    }
}
