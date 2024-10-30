package main

import (
	"fmt"
	"time"
)

func main() {
	s := []int{1, 2, 3}
	for _, i := range s {
		go func() {
			fmt.Print(i)
		}()
	}
	time.Sleep(1 * time.Second)

	fmt.Println()
	for _, i := range s {
		go func(v int) {
			fmt.Print(v)
		}(i)
	}
	time.Sleep(1 * time.Second)
}
