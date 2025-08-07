package algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class _347 {
    // class Item implements Comparable<Item> {
    //     int val;
    //     int cnt;

    //     public Item(int val) {
    //         this.val = val;
    //     }

    //     public void addCount() {
    //         this.cnt+=1;
    //     }

    //     @Override
    //     public int compareTo(Item o) {
    //         return Integer.compare(o.cnt, this.cnt); // 내림차순 정렬
    //     }
    // }

    // private Queue<Item> queue = new PriorityQueue<>();
    // private Map<Integer, Item> map = new HashMap<>();

    // public int[] topKFrequent(int[] nums, int k) {
    //     for (int i=0 ; i<nums.length ; i++) {
    //         if (!map.containsKey(nums[i])) {
    //             Item item = new Item(nums[i]);
    //             item.addCount();
    //             map.put(nums[i], item);
    //             queue.add(item);
    //         } else {
    //             Item item = map.get(nums[i]);
    //             item.addCount();
    //             queue.remove(item);
    //             queue.add(item);
    //         }
    //     }

    //     int size = queue.size();
    //     if (k > size) {
    //         k = size;
    //     }
    //     List<Item> list = new ArrayList<>();
    //     for (int i=0 ; i<k ; i++) {
    //         list.add(queue.poll());
    //     }

    //     int[] result = new int[k];
    //     for (int i=0 ; i<k ; i++) {
    //         result[i] = list.get(i).val;
    //         queue.add(list.get(i));
    //     }

    //     return result;
    // }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap: the less frequent element first
        Queue<Integer> heap = new PriorityQueue<>(
            (n1, n2) -> count.get(n1) - count.get(n2));

        for (int n: count.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] top = new int[k];
        for (int i=k-1 ; i>=0 ; i--) {
            top[i] = heap.poll();
        }
        return top;
    }
    
    public static void main(String[] args) {
        _347 runner = new _347();

        int[] result = runner.topKFrequent(new int[]{3,0,1,0}, 1);
        for (int i=0 ; i<result.length ; i++) {
            System.out.println(result[i] + " ");
        }
    }
}
