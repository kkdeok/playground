package main

import (
	"fmt"
	"unicode/utf8"
)

func main() {

	a := "a"
	fmt.Println(len(a)) // 1

	b := "가"
	fmt.Println(len(b)) // 3

	var c rune

	c = []rune(b)[0]
	fmt.Println(c)
	fmt.Println(utf8.RuneLen(c))
}
