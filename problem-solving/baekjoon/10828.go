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

	var n int
	var stack []int
	var len int
	fmt.Fscanln(reader, &n)
	for i := 0; i < n; i++ {
		var cmd string
		var val int
		fmt.Fscanln(reader, &cmd, &val)
		switch cmd {
		case "push":
			stack = append(stack, val)
			len += 1
		case "pop":
			if len == 0 {
				fmt.Fprintln(writer, "-1")
			} else {
				len -= 1
				fmt.Fprintln(writer, stack[len])
				stack = stack[:len]
			}
		case "top":
			if len == 0 {
				fmt.Fprintln(writer, "-1")
			} else {
				fmt.Fprintln(writer, stack[len-1])
			}
		case "size":
			fmt.Fprintln(writer, len)
		case "empty":
			if len == 0 {
				fmt.Fprintln(writer, "1")
			} else {
				fmt.Fprintln(writer, "0")
			}
		}
	}
}
