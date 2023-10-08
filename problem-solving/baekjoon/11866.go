package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

type Node struct {
	val  int
	prev *Node
	next *Node
}

// https://www.acmicpc.net/problem/11866
func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n, k int
	fmt.Fscanf(reader, "%d %d\n", &n, &k)

	var root, prev *Node
	root = &Node{
		val:  1,
		prev: nil,
		next: nil,
	}
	prev = root
	for i := 2; i <= n; i++ {
		curr := &Node{
			val:  i,
			prev: nil,
			next: nil,
		}
		curr.prev = prev
		prev.next = curr
		prev = curr
	}
	root.prev = prev
	prev.next = root

	var ans []string
	for i := 1; ; i++ {
		if i != k {
			prev = root
			root = root.next
		} else {
			ans = append(ans, strconv.Itoa(root.val))
			if prev == root {
				break
			}
			prev.next = root.next
			root = root.next
			i = 0 // init
		}
	}
	fmt.Fprintf(writer, "<%s>\n", strings.Join(ans, ", "))
}
