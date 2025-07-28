package algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class _380 {
    class RandomizedSet {        
        private final Map<Integer, Integer> map;
        private final List<Integer> list;
        private final Random rand;
        
        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            int idx = map.get(val);
            int last = list.get(list.size() - 1);

            list.set(idx, last); // just override
            map.put(last, idx);

            // for ArrayList, remove last element time complexity is O(1).
            // Otherwise, O(n).
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}