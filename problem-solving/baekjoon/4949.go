package main

import (
	"bufio"
	"fmt"
	"os"
)

type Stack []rune

func (s *Stack) Push(c rune) {
	*s = append(*s, c)
}

func (s *Stack) Pop() {
	if s.Top() != '.' {
		*s = (*s)[:len(*s)-1]
	}
}

func (s *Stack) Top() rune {
	if len(*s) > 0 {
		return (*s)[len(*s)-1]
	}
	return '.'
}

// https://www.acmicpc.net/problem/4949
func main() {
	scanner := bufio.NewScanner(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	for scanner.Scan() {
		var s Stack
		str := scanner.Text()
		if str == "." {
			break
		}

		isBalanced := true
		for _, c := range str {
			if c == '.' {
				break
			}

			if c == '(' || c == '[' {
				s.Push(c)
			} else if c == ')' {
				if s.Top() == '(' {
					s.Pop()
				} else {
					isBalanced = false
					break
				}
			} else if c == ']' {
				if s.Top() == '[' {
					s.Pop()
				} else {
					isBalanced = false
					break
				}
			}
		}
		if len(s) > 0 {
			isBalanced = false
		}

		if isBalanced {
			fmt.Fprintln(writer, "yes")
		} else {
			fmt.Fprintln(writer, "no")
		}
	}
}
