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
	fmt.Fscanln(reader, &n)
	for i := 0; i < n; i++ {
		var line string
		fmt.Fscanln(reader, &line)
		var cnt int
		for _, c := range line {
			if c == '(' {
				cnt += 1
			} else {
				cnt -= 1
			}
			if cnt < 0 {
				break
			}
		}
		if cnt == 0 {
			fmt.Fprintln(writer, "YES")
		} else {
			fmt.Fprintln(writer, "NO")
		}
	}
}
