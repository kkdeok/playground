package com.kkd.study.problem_solving.leetcode.dynamic;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/knight-probability-in-chessboard/solution/
 * FAILED
 */
public class KnightProbabilityInChessBoard {
    
    public static void main(String[] args) {
        KnightProbabilityInChessBoard app = new KnightProbabilityInChessBoard();
        System.out.println(app.knightProbability(8, 30, 6, 4));
    }

    private static final int[] dx = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] dy = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    private static long[][] memo;
    
    public double knightProbability(int N, int K, int r, int c) {
        memo = new long[N+1][N+1];
        for(int i=0 ; i<=N ; i++) {
            Arrays.fill(memo[i], -1L);
        }
        long count = doDP(N, K, r, c);
        double ans = (double) count;
        for (int i=0 ; i<K ; i++) {
            ans /= 8;
        }
        return ans;
    }
    
    private long doDP(int N, int K, int r, int c) {
        if (K == 0) {
            return 1;
        }
        if (memo[r][c] != -1) {
            return memo[r][c];
        }
        
        long count = 0;
        for (int i=0 ; i<8 ; i++) {
            int x = r + dx[i];
            int y = c + dy[i];
            if (x>=0 && x<N && y>=0 && y<N) {
                count += doDP(N, K-1, x, y);
            }
        }
        return memo[r][c] = count;
    } 
}
