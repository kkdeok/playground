package main

import (
	"encoding/json"
	"fmt"
)

func getMessage() []byte {
	str := `{ "id": 32, "name": "foo"}`
	return []byte(str)
}

func main() {
	b := getMessage()
	var m map[string]any
	err := json.Unmarshal(b, &m)
	if err != nil {
		panic(err)
	}
	fmt.Println(m)
	fmt.Printf("%T\n", m["id"])
}
