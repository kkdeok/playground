package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer
var heapIdx int

func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n, val int
	fmt.Fscanln(reader, &n)
	heap := make([]int, n+1)
	for i := 0; i < n; i++ {
		fmt.Fscanln(reader, &val)
		if val == 0 {
			fmt.Fprintln(writer, pop(heap))
		} else {
			push(heap, val)
		}
	}
}

func push(heap []int, val int) {
	heapIdx += 1
	heap[heapIdx] = val

	for i := heapIdx; i > 1; i /= 2 {
		if heap[i] > heap[i/2] {
			swap(heap, i, i/2)
		} else {
			break
		}
	}
}

func pop(heap []int) int {
	if heapIdx == 0 {
		return 0
	}
	maxValue := heap[1]
	heap[1] = heap[heapIdx]
	heap[heapIdx] = -1 // heapify 과정에서 이 부분을 참조할 수도 있다. 그래서 반드시 초기화가 필요하다.
	heapIdx -= 1
	for i := 1; i*2 <= heapIdx; {
		if heap[i] > heap[i*2] && heap[i] > heap[i*2+1] {
			break
		} else if heap[i*2] > heap[i*2+1] {
			swap(heap, i, i*2)
			i = i * 2
		} else {
			swap(heap, i, i*2+1)
			i = i*2 + 1
		}
	}
	return maxValue
}

func swap(heap []int, aIdx, bIdx int) {
	temp := heap[aIdx]
	heap[aIdx] = heap[bIdx]
	heap[bIdx] = temp
}
