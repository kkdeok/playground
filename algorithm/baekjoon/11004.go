package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

var reader *bufio.Reader
var writer *bufio.Writer

func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n, k int
	fmt.Fscanln(reader, &n, &k)

	arr := make([]int, n)
	for i := 0; i < n; i++ {
		fmt.Fscanf(reader, "%d", &arr[i])
	}

	sort.Slice(arr, func(i, j int) bool {
		return arr[i] < arr[j]
	})
	// mergeSort(arr, 0, n-1)
	fmt.Fprintln(writer, arr[k-1])
}

//
//func mergeSort(arr []int, l int, r int) {
//	if l < r {
//		mid := (l + r) / 2
//		mergeSort(arr, l, mid)
//		mergeSort(arr, mid+1, r)
//		sort(arr, l, mid, r)
//	}
//}
//
//func sort(arr []int, l int, mid int, r int) {
//	tmp := make([]int, r-l+1)
//	k := 0
//	i := l
//	j := mid + 1
//
//	for {
//		if i <= mid && j <= r {
//			if arr[i] <= arr[j] {
//				tmp[k] = arr[i]
//				k++
//				i++
//			} else {
//				tmp[k] = arr[j]
//				k++
//				j++
//			}
//		} else {
//			break
//		}
//	}
//
//	for ; i <= mid; i++ {
//		tmp[k] = arr[i]
//		k++
//	}
//	for ; j <= r; j++ {
//		tmp[k] = arr[j]
//		k++
//	}
//
//	k = 0
//	for t := l; t <= r; t++ {
//		arr[t] = tmp[k]
//		k++
//	}
//}
