package main

import (
	"encoding/json"
	"fmt"
	"time"
)

type Event2 struct {
	Time time.Time
}

func main() {
	t := time.Now()
	event1 := Event2{
		Time: t,
	}

	b, err := json.Marshal(event1)
	if err != nil {
		panic(err)
	}

	var event2 Event2
	err = json.Unmarshal(b, &event2)
	if err != nil {
		panic(err)
	}

	fmt.Println(event1)
	fmt.Println(event2)
	fmt.Println(event1 == event2)
}
