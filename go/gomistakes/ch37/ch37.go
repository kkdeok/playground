package main

import "fmt"

func main() {
	s := "h이llo"
	for i := range s {
		fmt.Printf("position %d: %c\n", i, s[i])
	}
	fmt.Printf("len=%d\n", len(s))
}
