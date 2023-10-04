package main

import (
	"bufio"
	"fmt"
	"math"
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

	var num int64
	m := make(map[int64]int)
	for i := 0; i < n; i++ {
		fmt.Fscanln(reader, &num)
		m[num] += 1
	}

	maxCnt := 0
	var ans int64 = math.MaxInt64
	for val, cnt := range m {
		if maxCnt == cnt {
			if val < ans {
				ans = val
			}
		} else if maxCnt < cnt {
			maxCnt = cnt
			ans = val
		}
	}
	fmt.Fprintln(writer, ans)
}
