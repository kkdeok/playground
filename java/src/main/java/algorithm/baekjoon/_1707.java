package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class _1707 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            String[] line = br.readLine().split(" ");
            int v = Integer.parseInt(line[0]);
            int e = Integer.parseInt(line[1]);

            Map<Integer, List<Integer>> graph = new HashMap<>();
            int[] visited = new int[v+1];
            Arrays.fill(visited, 0);

            for (int i=0 ; i<e ; i++) {
                line = br.readLine().split(" ");
                int x = Integer.parseInt(line[0]);
                int y = Integer.parseInt(line[1]);
                if (!graph.containsKey(x)) {
                    graph.put(x, new ArrayList<>());
                }
                graph.get(x).add(y);

                if (!graph.containsKey(y)) {
                    graph.put(y, new ArrayList<>());
                }
                graph.get(y).add(x);
            }
            boolean isBipartite = true;
            for (int i=1 ; i<=v ; i++) {
                if (visited[i] == 0) {
                    isBipartite = doDFS(graph, visited, i, 1);
                }
                if (!isBipartite) {
                    break;
                }
            }
            if (isBipartite) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean doDFS(Map<Integer, List<Integer>> graph, int[] visited, int v, int key) {
        visited[v] = key;
        for (Integer next : graph.getOrDefault(v, new ArrayList<>())) {
            if (visited[next] == key) {
                return false;
            }
            if (visited[next] == 0) {
                boolean isBipartite = doDFS(graph, visited, next, 3 - key);
                if (!isBipartite) {
                    return false;
                }
            }
        }
        return true;
    }
}
