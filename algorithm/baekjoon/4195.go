package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer

const MaxSize = 200_001

func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var t int
	fmt.Fscanln(reader, &t)
	for i := 0; i < t; i++ {
		root := make([]int, MaxSize)
		cnt := make([]int, MaxSize)
		for k := 0; k < MaxSize; k++ {
			root[k] = k
			cnt[k] = 1
		}

		var n int
		var a, b string
		nameMap := make(map[string]int)
		fmt.Fscanln(reader, &n)
		for j := 0; j < n; j++ {
			fmt.Fscanln(reader, &a, &b)
			aIdx := getIdx(nameMap, a)
			bIdx := getIdx(nameMap, b)

			cnt := union(root, cnt, aIdx, bIdx)
			fmt.Fprintln(writer, cnt)
		}
	}
}

func getIdx(nameMap map[string]int, name string) int {
	_, exist := nameMap[name]
	if !exist {
		nameMap[name] = len(nameMap)
	}
	return nameMap[name]
}

func union(root []int, cnt []int, aIdx, bIdx int) int {
	aRootIdx := find(root, aIdx)
	bRootIdx := find(root, bIdx)

	if aRootIdx != bRootIdx {
		root[bRootIdx] = aRootIdx
		cnt[aRootIdx] += cnt[bRootIdx]
		cnt[bRootIdx] = 1
	}
	return cnt[aRootIdx]
}

func find(root []int, idx int) int {
	if idx != root[idx] {
		root[idx] = find(root, root[idx])
	}
	return root[idx]
}
