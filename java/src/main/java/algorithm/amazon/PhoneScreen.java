package algorithm.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PhoneScreen {
    class Item implements Comparable<Item> {
        int id;
        int count;

        public Item(int id) {
            this.id = id;
            this.count = 0;
        }

        @Override
        public int compareTo(Item o) {
            return Integer.compare(o.count, this.count);
        }
    }

    private Map<Integer, Item> map = new HashMap<>();
    private Queue<Item> queue = new PriorityQueue<>();

    public void add(int id, int count) {
        if (!map.containsKey(id)) {
            Item item = new Item(id);
            map.put(id, item);
            queue.add(item);
        }
        Item item = map.get(id);
        item.count += count;
        queue.remove(item);
        queue.add(item);
    }

    public List<Integer> getTop10(int topK) {
        if (topK <= 0) {
            return new ArrayList<>();
        }

        int size = queue.size();
        if (size < topK) {
            topK = size;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < topK; i++) {
            result.add(queue.poll().id);
        }
        return result;
    }

    public static void main(String[] args) {
        PhoneScreen amazon = new PhoneScreen();
        amazon.add(1, 1);
        amazon.add(2, 1);
        amazon.add(3, 1);
        amazon.add(2, 1);
        amazon.add(3, 1);
        amazon.add(3, 1);

        System.out.println(amazon.getTop10(3));
    }
    
}
