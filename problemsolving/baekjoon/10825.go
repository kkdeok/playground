package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
)

var reader *bufio.Reader
var writer *bufio.Writer

type Student struct {
	name    string
	korean  int
	english int
	math    int
}

type Students []Student

func (s Students) Len() int {
	return len(s)
}

func (s Students) Less(i, j int) bool {
	if s[i].korean > s[j].korean {
		return true
	} else if s[i].korean < s[j].korean {
		return false
	} else if s[i].english > s[j].english {
		return false
	} else if s[i].english < s[j].english {
		return true
	} else if s[i].math > s[j].math {
		return true
	} else if s[i].math < s[j].math {
		return false
	}
	return s[i].name < s[j].name
}

func (s Students) Swap(i, j int) {
	s[i], s[j] = s[j], s[i]
}

// https://www.acmicpc.net/problem/10825
func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n int
	fmt.Fscanln(reader, &n)

	var students Students
	var name string
	var k, e, m int
	for i := 0; i < n; i++ {
		fmt.Fscanln(reader, &name, &k, &e, &m)
		student := Student{
			name:    name,
			korean:  k,
			english: e,
			math:    m,
		}
		students = append(students, student)
	}
	sort.Sort(students)
	for i := 0; i < n; i++ {
		fmt.Fprintln(writer, students[i].name)
	}
}
