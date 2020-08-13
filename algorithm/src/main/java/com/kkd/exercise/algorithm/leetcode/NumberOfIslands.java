package com.kkd.exercise.algorithm.leetcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) {
        NumberOfIslands app = new NumberOfIslands();
        char[][] sample = new char[][]{
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}};
        System.out.println(app.numIslands(sample));
    }

    private static int[] dx = new int[]{0, 0, -1, 1};
    private static int[] dy = new int[]{-1, 1, 0, 0};
    private int[][] visit;
    
    public int numIslands(char[][] grid) {
        int h = grid.length;
        if (h == 0) return 0;
        int w = grid[0].length;
        if (w == 0) return 0;
        
        // init visit
        visit = new int[h][w];
        int count = 0;
        for (int i=0 ; i<h ; i++) {
            for (int j=0 ; j<w ; j++) {
                if (grid[i][j] == '1' && visit[i][j] == 0) { 
                    doBFS(grid, h, w, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void doBFS(char[][] grid, int h, int w, int x, int y) {
        Queue<Integer> xq = new LinkedList<>();
        Queue<Integer> yq = new LinkedList<>();
        
        xq.add(x); yq.add(y);
        visit[x][y] = 1;
        
        while (!xq.isEmpty()) {
            int tempx = xq.poll();
            int tempy = yq.poll();
            
            for (int i=0 ; i<4 ; i++) {
                x = tempx + dx[i];
                y = tempy + dy[i];
                if (x>=0 && x<h && y>=0 && y<w) {
                    if (grid[x][y] == '1' && visit[x][y] == 0) {
                        xq.add(x); yq.add(y);
                        visit[x][y] = 1;
                    }
                }
            }
        }
    }
}
