package main

import (
	"fmt"
	"sync"
)

type Customer struct {
	mutex sync.RWMutex
	id    string
	age   int
}

func (c *Customer) UpdateAge(age int) error {
	c.mutex.Lock()
	defer c.mutex.Unlock()

	if age < 0 {
		return fmt.Errorf("age should be positive for suctomer %v", c)
	}

	c.age = age
	return nil
}

func (c *Customer) String() string {
	c.mutex.RLock()
	defer c.mutex.RUnlock()
	return fmt.Sprintf("id %s, age %d", c.id, c.age)
}

func main() {
	customer := &Customer{id: "1", age: 1}

	err := customer.UpdateAge(-1)
	if err != nil {
		panic(err)
	}
}
