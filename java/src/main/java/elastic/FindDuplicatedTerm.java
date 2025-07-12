package elastic;

import java.util.*;

public class FindDuplicatedTerm {
    public static List<String> findDuplicateWords(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return Collections.emptyList();
        }
        String[] words = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            word =  word.toLowerCase().replaceAll("[^a-z]", "");
            if (word.isEmpty()) {
                continue;
            }
            Integer count = wordCount.getOrDefault(word, 0);
            wordCount.put(word, count + 1);
        }

        List<String> duplicateWords = new ArrayList<>();
        for (String word : wordCount.keySet()) {
            if  (wordCount.get(word) > 1) {
                duplicateWords.add(word);
            }
        }
        return duplicateWords;
    }

    public static void main(String[] args) {
        System.out.println(findDuplicateWords("This dog is the best dog ever")); // [dog]
        System.out.println(findDuplicateWords("Word, word! Word?"));             // [word]
        System.out.println(findDuplicateWords("One and two and three and one")); // [and, one]
        System.out.println(findDuplicateWords(""));                              // []
    }
}
