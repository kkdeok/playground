package main

import "fmt"

func main() {

	m := map[string]int{
		"a": 1,
		"y": 2,
		"z": 3,
		"c": 4,
		"d": 5,
		"e": 6,
	}

	for k := range m {
		fmt.Print(k)
	}
}
