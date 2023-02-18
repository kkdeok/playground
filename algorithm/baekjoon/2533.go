package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer

/*
https://www.acmicpc.net/problem/2533

문제 설명:
서비스에 속한 사란들은 얼리어답터이거나 얼리어답터가 아니다.
얼리어답터가 아닌 사람들은 자신의 모든 친구들이 얼리 어답터일때만 이 아이디어를 받아들인다.
어떤 아이디어를 사회망 서비스에서 퍼뜨리고자 할 때, 가능한 한 최소의 수의 얼리 아답터를 확보하여
모든 사람이 이 아이디어를 받아들이게 하는 문제는 매우 중요하다.

친구 관계 트리가 주어졌을 때, 모든 개인이 새로운 아이디어를 수용하기 위하여
필요한 최소 얼리 어답터의 수를 구하는 프로그램을 작성하시오.

input:
- 친구 관계 트리의 정점 개수 N ( 2 <= N <= 1,000,000 )
- 각 정점음 1부터 N까지 일련번호로 표현됨.
= 두번째 줄에서 N-1 줄까지 각 줄마다 친구관계 트리의 edge (u, v) 가 나타난다.
*/
func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n int
	fmt.Fscanln(reader, &n)

	d := make([][2]int, n+1)
	visited := make([]bool, n+1)
	graph := make(map[int][]int)

	var u, v int
	for i := 0; i < n-1; i++ {
		fmt.Fscanln(reader, &u, &v)
		graph[u] = append(graph[u], v)
		graph[v] = append(graph[v], u)
	}
	find(1, graph, visited, d)
	fmt.Fprintln(writer, int(math.Min(float64(d[1][0]), float64(d[1][1]))))
}

func find(currNodeNum int, graph map[int][]int, visited []bool, d [][2]int) {
	visited[currNodeNum] = true
	d[currNodeNum][0] = 1 // early adapter
	d[currNodeNum][1] = 0 // no early

	for _, next := range graph[currNodeNum] {
		if !visited[next] {
			find(next, graph, visited, d)
			d[currNodeNum][1] += d[next][0]
			d[currNodeNum][0] += int(math.Min(float64(d[next][0]), float64(d[next][1])))
		}
	}
}
