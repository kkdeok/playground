package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

// https://www.acmicpc.net/problem/5430
func main() {
	scanner := bufio.NewScanner(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	const MaxBuf int = 400000
	scanner.Buffer(make([]byte, 0, MaxBuf), MaxBuf) // sc 버퍼의 크기를 MaxBuf로 변경

	scanner.Scan()
	t, _ := strconv.Atoi(scanner.Text())
	for i := 0; i < t; i++ {
		scanner.Scan()
		p := scanner.Text()
		scanner.Scan()
		n, _ := strconv.Atoi(scanner.Text())

		scanner.Scan()
		arr := convertToIntArr(scanner.Text())

		var res string
		if len(arr) != n {
			res = "error"
		} else {
			res = proc(p, arr)
		}
		fmt.Fprintln(writer, res)
	}
}

func proc(p string, arr []int) string {
	s := 0
	e := len(arr) - 1
	isReversed := false

	for _, c := range p {
		if c == 'R' {
			isReversed = !isReversed
			temp := s
			s = e
			e = temp
			continue
		}
		// if c is 'D'
		if isEmpty(s, e, isReversed) {
			return "error"
		}
		if isReversed {
			s--
		} else {
			s++
		}
	}
	if isEmpty(s, e, isReversed) {
		return "[]"
	}

	var res []string
	for {
		res = append(res, strconv.Itoa(arr[s]))
		if isReversed {
			s--
			if s < e {
				break
			}
		} else {
			s++
			if s > e {
				break
			}
		}
	}
	return fmt.Sprintf("[%s]", strings.Join(res, ","))
}

func isEmpty(s, e int, isReversed bool) bool {
	if isReversed {
		return s < e
	}
	return s > e
}

func convertToIntArr(strArr string) []int {
	var res []int
	if len(strArr) > 2 {
		strArr = strArr[1 : len(strArr)-1]
		for _, v := range strings.Split(strArr, ",") {
			intVal, _ := strconv.Atoi(v)
			res = append(res, intVal)
		}
	}
	return res
}
