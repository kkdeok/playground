package com.kkd.study.algorithm.codingtest.amazon.testcode;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/782606/Amazon-or-OA-2020-or-Largest-Item-Association
 */
public class LargestItemAssociationGroup {
    
    public static void main(String[] args) {
        LargestItemAssociationGroup app = new LargestItemAssociationGroup();

        final PairString p1 = new PairString("Item1", "Item2");
        final PairString p2 = new PairString("Item3", "Item4");
        final PairString p3 = new PairString("Item4", "Item5");
        final List<PairString> list = Arrays.asList(p1, p2, p3);
        
//        final PairString p1 = new PairString("Item1", "Item2");
//        final PairString p2 = new PairString("Item2", "Item3");
//        final PairString p3 = new PairString("Item4", "Item5");
//        final PairString p4 = new PairString("Item6", "Item7");
//        final PairString p5 = new PairString("Item5", "Item6");
//        final PairString p6 = new PairString("Item3", "Item7");
//        final List<PairString> list = Arrays.asList(p1, p2, p3, p4, p5, p6);
        
        for (String str : app.largestItemAssociation(list)) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    private Map<String, List<String>> adj;
    
    public List<String> largestItemAssociation(List<PairString> itemAssociation) {
        // init adjacent matrix and in-degree map.
        adj = new HashMap<>();
        Map<String, Integer> ind = new HashMap<>();
        for (PairString pairString : itemAssociation) { 
            adj.put(pairString.first, new ArrayList<>());
            adj.put(pairString.second, new ArrayList<>());
            ind.put(pairString.first, 0);
            ind.put(pairString.second, 0);
        }
        
        for (PairString pairString : itemAssociation) {
            String a = pairString.first;
            String b = pairString.second;
            
            List<String> list = adj.get(a);
            list.add(b);
            adj.put(a, list);
            ind.put(b, ind.get(b) + 1);
        }
        
        List<String> start = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : ind.entrySet()) {
            if (entry.getValue() == 0) {
                start.add(entry.getKey());
            }
        }
        
        Vector<String> ret = new Vector<>();
        for (String s : start) {
            Vector<String> temp = doDFS(s);
            ret = getLarge(ret, temp);
        }
        return ret;
    }
    
    private Vector<String> doDFS(String item) {
        Vector<String> ret = new Vector<>();
        ret.add(item);
        for (String next : adj.get(item)) {
            Vector<String> temp = doDFS(next);
            temp.add(0, item);
            ret = getLarge(ret, temp);
        }
        return ret;
    }
    
    private Vector<String> getLarge(Vector<String> a, Vector<String> b) {
        int aLen = a.size();
        int bLen = b.size();
        if (aLen < bLen) return b;
        if (aLen > bLen) return a;
        
        Vector<String> sortedA = new Vector<>(a);
        sortedA.sort(Comparator.naturalOrder());
        Vector<String> sortedB = new Vector<>(b);
        sortedB.sort(Comparator.naturalOrder());
        
        for (int i=0 ; i<aLen ; i++) {
            int comp = sortedA.get(i).compareTo(sortedB.get(i));
            if (comp > 0) return b;
            if (comp < 0) return a;
        }
        return a;
    }
}

class PairString {
    String first;
    String second;

    public PairString(String first, String second) {
        this.first = first;
        this.second = second;
    }
}