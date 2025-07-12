package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class _11725 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        for (int i=0 ; i<n-1 ; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);

            List<Integer> aList = map.getOrDefault(a, new ArrayList<>());
            aList.add(b);
            map.put(a, aList);

            List<Integer> bList = map.getOrDefault(b, new ArrayList<>());
            bList.add(a);
            map.put(b, bList);
        }

        int[] parentArr = new int[n+1];
        Arrays.fill(parentArr, -1);
        parentArr[1] = 0;
        traversal(map, parentArr, 1);

        for (int i=2 ; i<=n ; i++) {
            bw.write(parentArr[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static void traversal(Map<Integer, List<Integer>> map, int[] parentArr, int node) {
        for (int next : map.get(node)) {
            if  (parentArr[next] == -1) {
                parentArr[next] = node;
                traversal(map, parentArr, next);
            }
        }
    }
}
