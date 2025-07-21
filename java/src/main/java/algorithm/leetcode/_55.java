package algorithm.leetcode;

class _55 {
    // Because array size is quite a small, I thought I can resolve it
    // by graph algorithm. DFS with visited checking should work for here.
    // https://leetcode.com/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150
    public boolean canJump(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        return traversal(nums, visited, 0);
    }

    private boolean traversal(int[] nums, boolean[] visited, int idx) {
        visited[idx] = true;
        if (idx == nums.length - 1) {
            return true;
        }

        for (int i=1 ; i<=nums[idx] ; i++) {
            int next = idx + i;
            if (next < nums.length && !visited[next]) {
                if (traversal(nums, visited, next)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _55 solution = new _55();
        System.out.println(solution.canJump(new int[]{2,3,1,1,4}));
        System.out.println(solution.canJump(new int[]{3,2,1,0,4}));
    }
}