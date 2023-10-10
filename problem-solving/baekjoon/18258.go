package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Queue []int

func (q *Queue) push(n int) {
	*q = append(*q, n)
}

func (q *Queue) pop() int {
	if q.isEmpty() == 1 {
		return -1
	}
	n := (*q)[0]
	*q = (*q)[1:]
	return n
}

func (q *Queue) size() int {
	return len(*q)
}

func (q *Queue) isEmpty() int {
	if len(*q) == 0 {
		return 1
	}
	return 0
}

func (q *Queue) front() int {
	if q.isEmpty() == 1 {
		return -1
	}
	return (*q)[0]
}

func (q *Queue) back() int {
	if q.isEmpty() == 1 {
		return -1
	}
	return (*q)[len(*q)-1]
}

// https://www.acmicpc.net/problem/18258
func main() {
	scanner := bufio.NewScanner(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	scanner.Scan()
	n, _ := strconv.Atoi(scanner.Text())

	var q Queue
	for i := 0; i < n; i++ {
		scanner.Scan()
		cmd := scanner.Text()

		if strings.Contains(cmd, "push") {
			val, _ := strconv.Atoi(strings.Split(cmd, " ")[1])
			q.push(val)
		} else if cmd == "pop" {
			fmt.Fprintln(writer, q.pop())
		} else if cmd == "size" {
			fmt.Fprintln(writer, q.size())
		} else if cmd == "empty" {
			fmt.Fprintln(writer, q.isEmpty())
		} else if cmd == "front" {
			fmt.Fprintln(writer, q.front())
		} else if cmd == "back" {
			fmt.Fprintln(writer, q.back())
		}
	}
}
