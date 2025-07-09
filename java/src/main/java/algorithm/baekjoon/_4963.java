package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class _4963 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        while (true) {
            String[] line = br.readLine().split(" ");
            int y = Integer.parseInt(line[0]);
            int x = Integer.parseInt(line[1]);
            if (x == 0 && y == 0) {
                break;
            }

            int[][] graph = new int[x][y];
            for (int i = 0; i < x; i++) {
                line = br.readLine().split(" ");
                for (int j = 0; j < y; j++) {
                    graph[i][j] = Integer.parseInt(line[j]);
                }
            }

            int count = 0;
            boolean[][] visited = new boolean[x][y];
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (graph[i][j] == 1 && !visited[i][j]) {
                        visited[i][j] = true;
                        doBFS(graph, visited, i, j);
                        count++;
                    }
                }
            }
            bw.write(count+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void doBFS(int[][] graph, boolean[][] visited, int x, int y) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        xq.add(x);
        yq.add(y);

        while (!xq.isEmpty()) {
            int sx = xq.poll();
            int sy = yq.poll();

            for (int i = 0; i < 8; i++) {
                int newX = sx + dx[i];
                int newY = sy + dy[i];
                if (newX >= 0 && newX < graph.length && newY >= 0 && newY < graph[0].length) {
                    if (graph[newX][newY] == 1 && !visited[newX][newY]) {
                        xq.add(newX);
                        yq.add(newY);
                        visited[newX][newY] = true;
                    }
                }
            }
        }
    }
}
