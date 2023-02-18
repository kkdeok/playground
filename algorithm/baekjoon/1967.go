package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer

type Node struct {
	next     int
	distance int
}

var maxDistance int
var maxDistanceNodeNum int

func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n int
	fmt.Fscanln(reader, &n)

	var a, b, d int
	board := make(map[int][]Node)
	for i := 0; i < n-1; i++ {
		fmt.Fscanln(reader, &a, &b, &d)
		board[a] = append(board[a], Node{b, d})
		board[b] = append(board[b], Node{a, d})
	}
	maxDistance = 0
	maxDistanceNodeNum = 0
	visited := make([]bool, n+1)
	dfs(1, 0, board, visited)

	currDistanceNodeNum := maxDistanceNodeNum
	maxDistance = 0
	maxDistanceNodeNum = 0
	dfs(currDistanceNodeNum, 0, board, visited)

	fmt.Fprintln(writer, maxDistance)
}

func dfs(currNodeNum int, currDist int, board map[int][]Node, visited []bool) {
	visited[currNodeNum] = true
	if maxDistance < currDist {
		maxDistance = currDist
		maxDistanceNodeNum = currNodeNum
	}
	for _, node := range board[currNodeNum] {
		if !visited[node.next] {
			dfs(node.next, currDist+node.distance, board, visited)
		}
	}
	visited[currNodeNum] = false
}
