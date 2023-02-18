package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

var reader *bufio.Reader
var writer *bufio.Writer

type Point struct {
	x int
	y int
}

// https://www.acmicpc.net/problem/11651
func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n int
	var points []Point
	fmt.Fscanln(reader, &n)
	for i := 0; i < n; i++ {
		var point Point
		fmt.Fscanln(reader, &point.x, &point.y)
		points = append(points, point)
	}

	sort.Slice(points, func(i, j int) bool {
		if points[i].y < points[j].y {
			return true
		} else if points[i].y > points[j].y {
			return false
		}
		return points[i].x < points[j].x
	})

	for _, point := range points {
		fmt.Fprintln(writer, point.x, point.y)
	}
}
