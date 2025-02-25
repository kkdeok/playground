package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
)

func main() {
	var writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	//ans := snakesAndLadders([][]int{
	//	{-1, -1, -1, -1, -1, -1},
	//	{-1, -1, -1, -1, -1, -1},
	//	{-1, -1, -1, -1, -1, -1},
	//	{-1, 35, -1, -1, 13, -1},
	//	{-1, -1, -1, -1, -1, -1},
	//	{-1, 15, -1, -1, -1, -1},
	//})

	//ans := snakesAndLadders([][]int{
	//	{-1, -1},
	//	{-1, 3},
	//})

	ans := snakesAndLadders([][]int{
		{1, 1, -1},
		{1, 1, 1},
		{-1, 1, 1},
	})

	fmt.Fprintf(writer, "%d\n", ans)
}

const VISITED = -2

func snakesAndLadders(board [][]int) int {
	n := len(board)
	nQueue := list.New()
	nQueue.PushBack(1)

	cQueue := list.New()
	cQueue.PushBack(0)

	// check visit to avoid (prune) revisiting
	board[n-1][0] = VISITED

	for nQueue.Len() != 0 {
		nFront := nQueue.Front()
		num := nFront.Value.(int)
		nQueue.Remove(nFront)

		cFront := cQueue.Front()
		cnt := cFront.Value.(int)
		cQueue.Remove(cFront)

		if num == n*n {
			return cnt
		}

		if cnt > n*n {
			return -1
		}

		var maxNext int
		if num+6 < n*n {
			maxNext = num + 6
		} else {
			maxNext = n * n
		}

		for i := num + 1; i <= maxNext; i++ {
			x, y := coordinate(i, n)
			if board[x][y] == -1 {
				nQueue.PushBack(i)
				cQueue.PushBack(cnt + 1)
			} else if board[x][y] != VISITED {
				nQueue.PushBack(board[x][y])
				cQueue.PushBack(cnt + 1)
			}
			board[x][y] = VISITED
		}
	}
	return -1
}

func coordinate(num, n int) (int, int) {
	a := num % n
	b := num / n

	var x int
	x = n - b - 1
	if a == 0 {
		x++
	}

	var y int
	if b%2 == 1 {
		y = n - a
		if a == 0 {
			y--
		}
	} else {
		y = a - 1
		if a == 0 {
			y++
		}
	}
	return x, y
}
