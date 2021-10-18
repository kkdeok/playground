package com.kkd.study.algorithm.codingtest.amazon._20211018.no1;


/**
 * Amazon sellers sometimes provide a link to documentation about a product they are offering.
 * Documentation is usually large, so it is broken into an even number of volumes for download.
 *
 * 다운로드를 위해 짝수 개의 볼륨으로 나뉩니다.
 *
 * Each volume:
 * - is stored in a node instance as a SinglyLinkedListNode
 * - has a page count stored in data
 * - has a pointer to the next volume stored in next.
 *
 * A customer will read the first and last volumes each day, removing the volumes from the list after reading them.
 * 고객은 매일 첫 번째 권과 마지막 권을 읽고 읽은 후 목록에서 해당 권을 제거합니다.
 *
 * Given a reference to the head of a singly-linked list, calculate the maximum number of pages read on any day.
 * 단일 연결 목록의 헤드에 대한 참조가 주어지면 하루에 읽은 최대 페이지 수를 계산합니다.
 *
 * Try to create a solution that has a space complexity of O(1)
 */
public class Solution {

	static class SinglyLinkedList {
		int data;
		SinglyLinkedList next;
	}

	private static SinglyLinkedList firstVolume;

//	public static int maximumPages(SinglyLinkedList head) {
//		List<SinglyLinkedList>
//	}
//
//	private static int find(SinglyLinkedList lastVolume, int lastVolumePosition) {
//		int res = 0;
//		if (lastVolume.next != null) {
//			res = Math.max(res, find(lastVolume.next, lastVolumePosition + 1));
//		}
//		if (fistVolumePosition > lastVolumePosition) {
//			return res;
//		}
//		// compare
//		res = Math.max(res, firstVolume.data + lastVolume.data);
//		firstVolume = firstVolume.next;
//		fistVolumePosition++;
//		return res;
//	}
}
