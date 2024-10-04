package main

import "fmt"

func main() {
	defer func() {
		if r := recover(); r != nil {
			fmt.Println("recover", r)
		}
	}()

	f()
}

func f() {
	fmt.Println("a")
	panic("foo")
	fmt.Println("b")
}
