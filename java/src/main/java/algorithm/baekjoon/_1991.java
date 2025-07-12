package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class _1991 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static class Children {
        char left;
        char right;
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        Map<Character, Children> map = new HashMap<>();
        for (int i=1 ; i<=n ; i++) {
            String[] line = br.readLine().split(" ");
            char root = line[0].charAt(0);
            char left = line[1].charAt(0);
            char right = line[2].charAt(0);
            map.putIfAbsent(root, new Children());
            map.get(root).left = left;
            map.get(root).right = right;
        }

        // preorder: root -> left -> right
        preorder(map, 'A');
        bw.write("\n");
        // inorder: left -> root -> right
        inorder(map, 'A');
        bw.write("\n");
        // postorder: left -> right -> root
        postorder(map, 'A');
        bw.write("\n");

        bw.flush();
        bw.close();
    }

    private static void preorder(Map<Character, Children> map, char key) throws Exception {
        bw.write(String.valueOf(key));
        if (map.containsKey(map.get(key).left)) {
            preorder(map, map.get(key).left);
        }
        if (map.containsKey(map.get(key).right)) {
            preorder(map, map.get(key).right);
        }
    }

    private static void inorder(Map<Character, Children> map, char key) throws Exception {
        if (map.containsKey(map.get(key).left)) {
            inorder(map, map.get(key).left);
        }
        bw.write(String.valueOf(key));
        if (map.containsKey(map.get(key).right)) {
            inorder(map, map.get(key).right);
        }
    }

    private static void postorder(Map<Character, Children> map, char key) throws Exception {
        if (map.containsKey(map.get(key).left)) {
            postorder(map, map.get(key).left);
        }
        if (map.containsKey(map.get(key).right)) {
            postorder(map, map.get(key).right);
        }
        bw.write(String.valueOf(key));
    }
}
