package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer

// 루트 없는 트리가 주어진다.
// 이때 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성해라
// N: 노드 개수 2 <= N <= 100,000
func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n, a, b int
	fmt.Fscanln(reader, &n)
	m := make(map[int][]int)
	for i := 0; i < n-1; i++ {
		fmt.Fscanln(reader, &a, &b)
		m[a] = append(m[a], b)
		m[b] = append(m[b], a)
	}
	visited := make([]bool, n+1)
	parents := make([]int, n+1)

	process(visited, parents, m, 1)
	for i := 2; i <= n; i++ {
		fmt.Fprintln(writer, parents[i])
	}
}

func process(visited []bool, parents []int, m map[int][]int, node int) {
	visited[node] = true
	for _, next := range m[node] {
		if !visited[next] {
			parents[next] = node
			process(visited, parents, m, next)
		}
	}
}
