package main

import (
	"bufio"
	"fmt"
	"os"
)

type Node struct {
	value int
	next  *Node
	prev  *Node
}

func main() {
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var root *Node
	for i := 0; i < 5; i++ {
		var n int
		fmt.Fscanf(reader, "%d\n", &n)
		current := &Node{
			value: n,
			prev:  nil,
			next:  nil,
		}

		if root == nil {
			root = current
		} else {
			var prev *Node
			for prev = root; prev.next != nil; prev = prev.next {
			}
			prev.next = current
		}

		node := root
		for node != nil {
			fmt.Printf("%d ", node.value)
			node = node.next
		}
		fmt.Println()
	}
}
