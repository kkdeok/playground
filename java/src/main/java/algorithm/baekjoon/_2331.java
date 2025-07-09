package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class _2331 {
    // D[1] = A
    // D[n] = D[n-1]의 각 자리의 숫자를 P번 곱한 수들의 값.
    // 계속 숫자를 구해나가야 한다. 그리고 이미 나왔던 숫자인지를 파악해야 한다.
    // 같은 숫자가 한번이라도 나오면 무조건 그 뒤는 똑같다.
    // Set 으로 숫자에 대한 중복을 체크한다.
    // Vector Array 로 숫자를 하나씩 저장한다.

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        String[] line = br.readLine().split(" ");
        int a = Integer.parseInt(line[0]);
        int p = Integer.parseInt(line[1]);

        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        int value = a;
        while (true) {
            if (set.contains(value)) {
                break;
            }
            set.add(value);
            list.add(value);
            value = calculate(value, p);
        }

        int count = 0;
        for (Integer integer : list) {
            if (integer == value) {
                break;
            } else {
                count++;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    private static int calculate(int a, int p) {
        int res = 0;
        while (a > 0) {
            int digit = a % 10;
            res += (int) Math.pow(digit, p);
            a = a / 10;
        }
        return res;
    }
}
