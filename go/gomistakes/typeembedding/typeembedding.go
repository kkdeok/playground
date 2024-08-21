package typeembedding

import "fmt"

type Foo struct {
	Bar
}

type Bar struct {
	Baz int
}

func Process() {
	f := Foo{}
	f.Baz = 42
	fmt.Println(f.Baz)
}
