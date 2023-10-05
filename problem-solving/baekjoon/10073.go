package main

import (
	"bufio"
	"fmt"
	"os"
)

type Stack []int

func (s *Stack) Push(n int) {
	*s = append(*s, n)
}

func (s *Stack) Pop() {
	if len(*s) > 0 {
		*s = (*s)[:len(*s)-1]
	}
}

// https://www.acmicpc.net/problem/10773
func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var k, n int
	var s Stack

	fmt.Fscanln(reader, &k)
	for i := 0; i < k; i++ {
		fmt.Fscanln(reader, &n)
		if n == 0 {
			s.Pop()
		} else {
			s.Push(n)
		}
	}

	var sum int
	for _, val := range s {
		sum += val
	}
	fmt.Fprintln(writer, sum)
}
