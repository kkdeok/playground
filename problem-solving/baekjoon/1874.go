package main

import (
	"bufio"
	"fmt"
	"os"
)

type Stack struct {
	arr []int
	op  []int
}

func (s *Stack) Push(n int) {
	s.arr = append(s.arr, n)
	s.op = append(s.op, 1)
}

func (s *Stack) Pop() {
	if len(s.arr) > 0 {
		s.arr = (s.arr)[:len(s.arr)-1]
		s.op = append(s.op, 0)
	}
}

func (s *Stack) Top() int {
	if len(s.arr) > 0 {
		return (s.arr)[len(s.arr)-1]
	}
	return -1
}

// https://www.acmicpc.net/problem/1874
func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n int
	fmt.Fscanln(reader, &n)
	seq := make([]int, n)
	seqIdx := 0
	for i := 0; i < n; i++ {
		fmt.Fscanln(reader, &seq[i])
	}
	var s Stack
	s.Push(1)

	for i := 2; i <= n; i++ {
		if s.Top() == seq[seqIdx] {
			s.Pop()
			seqIdx++
			i--
		} else {
			s.Push(i)
		}
	}
	for ; seqIdx < n; seqIdx++ {
		if s.Top() == seq[seqIdx] {
			s.Pop()
		} else {
			fmt.Fprintln(writer, "NO")
			return
		}
	}

	for _, k := range s.op {
		if k == 1 {
			fmt.Fprintln(writer, "+")
		} else {
			fmt.Fprintln(writer, "-")
		}
	}
}
