package main

import (
	"fmt"
	"strconv"
)

type Customer struct {
	ID      string
	Balance float64
}

type Store struct {
	m map[string]*Customer
}

func (s *Store) storeCustomers(customers []Customer) {
	for _, customer := range customers {
		s.m[customer.ID] = &customer
	}
}

func main() {
	s := &Store{
		m: make(map[string]*Customer),
	}
	s.storeCustomers([]Customer{
		{ID: "1", Balance: 10},
		{ID: "2", Balance: -10},
		{ID: "3", Balance: 0},
	})

	for _, v := range s.m {
		fmt.Println(v.ID + " : " + strconv.FormatFloat(v.Balance, 'f', -1, 64))
	}
}
