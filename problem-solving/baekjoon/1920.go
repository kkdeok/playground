package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

// https://www.acmicpc.net/problem/1920
func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n int
	fmt.Fscanf(reader, "%d\n", &n)

	var nums []int
	for i := 0; i < n; i++ {
		var num int
		fmt.Fscanf(reader, "%d ", &num)
		nums = append(nums, num)
	}

	// sort nums
	sort.Ints(nums)

	var m, k int
	var arr []int
	fmt.Fscanf(reader, "%d\n", &m)

	for i := 0; i < m; i++ {
		fmt.Fscanf(reader, "%d ", &k)
		arr = append(arr, k)
	}

	for i := 0; i < m; i++ {
		if isContained(nums, arr[i], 0, n-1) {
			fmt.Fprintln(writer, 1)
		} else {
			fmt.Fprintln(writer, 0)
		}
	}
}

func isContained(nums []int, k int, s int, e int) bool {
	if s > e {
		return false
	}
	var mid int
	if s == 0 && e == 0 {
		mid = 0
	} else {
		mid = (s + e) / 2
	}

	if nums[mid] == k {
		return true
	} else if nums[mid] < k {
		return isContained(nums, k, mid+1, e)
	} else {
		return isContained(nums, k, s, mid-1)
	}
}
