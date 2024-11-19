package main

import (
	"encoding/json"
	"fmt"
	"time"
)

type Event struct {
	ID        int
	time.Time // type embedding
}

func main() {
	event := Event{
		ID:   1234,
		Time: time.Now(),
	}

	b, err := json.Marshal(event)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(b))
}
