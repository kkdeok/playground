package com.kkd.study.algorithm.codingtest.amazon;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Amazon Online Test
 */
public class TestCode1 {

	public static void main(String[] args) {

		Map<String, Integer> map = new HashMap<>();
		map.put("D", 2);
		map.put("E", 2);
		map.put("B", 2);
		map.put("A", 2);
		map.put("C", 1);
//		Map<String, Integer> newMap = sort(map);

		Map<String, Integer> newMap = map.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.<String, Integer>comparingByValue())
				.thenComparing(Map.Entry.comparingByKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap::new));

		for (String a : newMap.keySet()) {
			System.out.println(a);
		}
	}

	public ArrayList<String> popularNFeatures(
			int numFeatures,
			int topFeatures,
			List<String> possibleFeatures,
			int numFeatureRequests,
			List<String> featureRequests) {

		// init requestCountMap
		Map<String, Long> requestCountMap = new HashMap<>();
		for (String possibleFeature : possibleFeatures) {
			requestCountMap.put(possibleFeature, 0L);
		}

		for (String featureRequest : featureRequests) {
			String request = featureRequest.toLowerCase();
			for (String possibleFeature : possibleFeatures) {
				String feature = possibleFeature.toLowerCase();
				if (request.contains(feature)) {
					long count = requestCountMap.get(possibleFeature);
					requestCountMap.put(possibleFeature, count + 1);
				}
			}
		}

		Map<String, Long> sortedRequestCountMap = requestCountMap.entrySet().stream()
				.sorted(Map.Entry.<String, Long>comparingByValue()
						.thenComparing(Map.Entry.comparingByKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//		List<String> response = new ArrayList<>();
//		for (String feature : sortedRequestCountMap.keySet()) {
//
//		}
		return null;
	}

	static <K, V extends Comparable<? super V>> Map<K, V> sort(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
		list.sort(Map.Entry.comparingByValue());


		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}

		return result;
	}
}
