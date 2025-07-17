package algorithm.leetcode;

public class _122 {
    // 탐욕법으로 접근
    // - 조건1: 현재의 선택이 미래의 선택에 영향을 주지 않는다.**
    // - 조건2: 부분 최적의 해가 모이면 전체 최적의 해가 된다. (Optimal Substructure)**
    // 현재를 기준으로 주식을 샀을 때 가장 가까운 시일 내레 이득을 보며 팔 수 있는 곳이 어딘지만 보면된다. 이건 부분 최적 해이고, 이게 결국 쌓이면 최적의 해가 된다.
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/?envType=study-plan-v2&envId=top-interview-150
    public int maxProfit(int[] prices) {
        int result = 0;
        int cursor = 0;
        for (int i=0 ; i<prices.length ; i++) {
            if (prices[cursor] < prices[i]) {
                result += prices[i] - prices[cursor];
            }
            // if prices[cursor] < prices[i], we should sell at i and start assuming we buy at i.
            // if prices[cursor] = prices[i], we can move cursor to i. nothing changes.
            // if prices[cursor] > prices[i], we should withdraw buying at cursor and start assuming we buy at i.
            cursor = i;
        }
        return result;
    }
}
