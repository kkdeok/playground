package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer

// 상근 N개국 돌면서 여행한다.
// 최대한 적은 종류의 비행기를 타고 국가들을 이동하려고 한다.
// 상근이가 가장 적은 종류의 비행기를 타고 모든 국가들을 여행할 수 있도록 도와주자.
// 한 국가에서 다른 국가로 이동할 때 다른 국가를 거쳐 가도(이미 방문한 국가라도 오케이) 된다.
// 상근이가 모든 국가를 여행하기 위해 타야 하는 비행기 종류의 최소 개수를 구해라
//
// T: 테스트 케이스 수
// 2 <= N(국가의 수) <= 1000
// 1 <= M(비행기 종류) <= 10000
// M개의 줄에 a, b 쌍이 입력된다. a와  b를 왕복하는 비행기가 있다는 것을 의미함.
// 1 <= a, b <= n , a != b
// 주어지는 비행 스케줄은 항상 연결 그래프를 이룬다.
func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var t int
	fmt.Fscanln(reader, &t)
	for i := 0; i < t; i++ {
		var n, m int
		fmt.Fscanln(reader, &n, &m)

		//visited := make([]bool, n+1)
		board := make(map[int][]int)
		for j := 0; j < m; j++ {
			var a, b int
			fmt.Fscanln(reader, &a, &b)
			board[a] = append(board[a], b)
			board[b] = append(board[b], a)
		}
		visited := make([]bool, n+1)

		queue := make([]int, 0)
		queue = append(queue, 1)
		visited[1] = true

		cnt := 0
		for {
			if len(queue) == 0 {
				break
			}
			node := queue[0]
			queue = queue[1:] // discard top element
			nodes := board[node]
			for _, next := range nodes {
				if !visited[next] {
					cnt++
					visited[next] = true
					queue = append(queue, next)
				}
			}
		}
		fmt.Fprintln(writer, cnt)
	}
}
