package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class _2178 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int[][] board = new int[n][m];
        for (int i=0 ; i<n ; i++) {
            char[] carr = br.readLine().toCharArray();
            for (int j=0 ; j<m ; j++) {
                board[i][j] = carr[j] - '0';
            }
        }

        int result = doBFS(board, n, m);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }


    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    private static int doBFS(int[][] board, int n, int m) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();

        int[][] visited = new int[n][m];
        visited[0][0] = 1;
        xq.add(0);
        yq.add(0);

        while (!xq.isEmpty()) {
            int x = xq.poll();
            int y = yq.poll();

            if (x == n-1 && y == m-1) {
                return visited[x][y];
            }

            for (int i=0 ; i<4 ; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx>=0 && nx < n && ny>=0 && ny < m) {
                    if (board[nx][ny] == 1 && visited[nx][ny] == 0) {
                        visited[nx][ny] = visited[x][y] + 1;
                        xq.add(nx);
                        yq.add(ny);
                    }
                }
            }
        }
        return 0;
    }
}
