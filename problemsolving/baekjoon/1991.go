package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer

func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	m := make(map[string][]string)

	var n int
	var c, l, r string
	fmt.Fscanln(reader, &n)
	for i := 0; i < n; i++ {
		fmt.Fscanln(reader, &c, &l, &r)
		m[c] = []string{l, r}
	}

	preorder(m, "A")
	fmt.Fprintln(writer)

	inorder(m, "A")
	fmt.Fprintln(writer)

	postorder(m, "A")
	fmt.Fprintln(writer)
}

// root -> left -> right
func preorder(m map[string][]string, node string) {
	if node == "." {
		return
	}
	fmt.Fprint(writer, node)
	preorder(m, m[node][0])
	preorder(m, m[node][1])
}

// left -> root -> right
func inorder(m map[string][]string, node string) {
	if node == "." {
		return
	}
	inorder(m, m[node][0])
	fmt.Fprint(writer, node)
	inorder(m, m[node][1])
}

// left -> right -> root
func postorder(m map[string][]string, node string) {
	if node == "." {
		return
	}
	postorder(m, m[node][0])
	postorder(m, m[node][1])
	fmt.Fprint(writer, node)
}
