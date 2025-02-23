package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader = bufio.NewReader(os.Stdin)
var writer = bufio.NewWriter(os.Stdout)

func main() {
	defer writer.Flush()
	
	ans := []int{0}
	merge(
		ans,
		0,
		[]int{1},
		1,
	)
	
	for i := 0; i < len(ans); i++ {
		fmt.Fprintf(writer, "%d ", ans[i])
	}
}

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
