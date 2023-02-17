package algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();

        s.solution(3, new int[][]{{1,1,0}, {1,1,0}, {0,0,1}});
        System.out.println(res);
    }

    public int solution(int[][] maps) {
        int answer = -1;
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{-1, 1, 0, 0};

        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];

        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        Queue<Integer> cq = new LinkedList<>(); // count

        xq.offer(0);
        yq.offer(0);
        cq.offer(1);

        while (!xq.isEmpty()) {
            int x = xq.poll();
            int y = yq.poll();
            int c = cq.poll();

            if (x == n-1 && y == m-1) {
                answer = c;
                break;
            }
            for (int i=0 ; i<4 ; i++) {
                int tempX = x + dx[i];
                int tempY = y + dy[i];

                if (tempX >= 0 && tempX <n && tempY >= 0 && tempY < m) {
                    if (maps[tempX][tempY] == 1 && !visited[tempX][tempY]) {
                        xq.offer(tempX);
                        yq.offer(tempY);
                        cq.offer(c + 1);
                        visited[tempX][tempY] = true;
                    }
                }
            }
        }
        return answer;
    }
}