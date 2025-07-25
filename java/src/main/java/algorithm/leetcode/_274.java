package algorithm.leetcode;

public class _274 {
       // return h, which is at least h papers that have each been cited at least h times.
    // n == citations.length
    // 1 <= n <= 5000
    // 0 <= citations[i] <= 1000
    // 
    // [3,0,6,1,5]
    // h = 1, p = 4
    // h = 2, p = 3
    // h = 3, p = 3
    // h = 4, p = 2
    // h = 5, p = 2
    // h = 6, p = 1
    public int hIndex(int[] citations) {
        int[] countArr = new int[1001];

        for (int i=0 ; i<citations.length ; i++) {
            for (int j=0 ; j<=citations[i] ; j++) {
                countArr[j]++;
            }
        }

        for (int i=1000 ; i>=0 ; i--) {
            if (i <= countArr[i]) {
                return i;
            }
        }
        
        return 0;
    }
}