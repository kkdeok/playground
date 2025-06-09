package leetcode

func merge(nums1 []int, m int, nums2 []int, n int) {
	idx := m + n - 1
	for n > 0 {
		if m == 0 {
			nums1[idx] = nums2[n-1]
			idx--
			n--
			continue
		}

		if nums1[m-1] > nums2[n-1] {
			nums1[idx] = nums1[m-1]
			idx--
			m--
		} else {
			nums1[idx] = nums2[n-1]
			idx--
			n--
		}
	}
}
