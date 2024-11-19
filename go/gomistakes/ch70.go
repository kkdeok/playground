package main

import "sync"

type Cache struct {
	mu       sync.RWMutex
	balances map[string]float64
}

func (c *Cache) AddBalance(id string, balance float64) {
	c.mu.Lock()
	c.balances[id] = balance
	c.mu.Unlock()
}

func (c *Cache) AvgBalance() float64 {
	c.mu.Lock()
	balances := c.balances
	c.mu.Unlock()

	sum := 0.
	for _, balance := range balances {
		sum += balance
	}

	return sum / float64(len(balances))
}
