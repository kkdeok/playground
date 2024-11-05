package main

import (
	"fmt"
	"time"
)

func main() {
	s := make([]int, 1)

	go func() {
		s1 := append(s, 1)
		fmt.Println(s1)
	}()

	go func() {
		s2 := append(s, 2)
		fmt.Println(s2)
	}()

	time.Sleep(1 * time.Second)
}
