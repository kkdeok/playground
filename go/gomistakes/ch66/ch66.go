package main

import (
	"log"
	"time"
)

func main() {

	ch1 := make(chan int, 1)
	ch2 := make(chan int, 1)

	go func() {
		ch := merge(ch1, ch2)
		for {
			select {
			case v, ok := <-ch:
				if !ok {
					log.Println("channel closed")
					break
				} else {
					log.Println(v)
				}
			}
		}
	}()

	ch1 <- 1
	time.Sleep(1 * time.Second)
	ch2 <- 2
	time.Sleep(1 * time.Second)
	ch1 <- 3
	time.Sleep(1 * time.Second)
	ch2 <- 4
	time.Sleep(1 * time.Second)
	close(ch1)
	time.Sleep(1 * time.Second)
	ch2 <- 2
	time.Sleep(1 * time.Second)
	ch2 <- 4
	time.Sleep(1 * time.Second)
}

// case 1
//func merge(ch1, ch2 <-chan int) <-chan int {
//	ch := make(chan int, 1)
//
//	go func() {
//		for v := range ch1 {
//			ch <- v
//		}
//		for v := range ch2 {
//			ch <- v
//		}
//		close(ch)
//	}()
//
//	return ch
//}

func merge(ch1, ch2 <-chan int) <-chan int {
	ch := make(chan int, 1)

	go func() {
		for ch1 != nil || ch2 != nil {
			select {
			case v, open := <-ch1:
				log.Printf("ch 1: %v", v)
				if !open {
					ch1 = nil
					break
				}
				ch <- v
			case v, open := <-ch2:
				log.Printf("ch 2: %v", v)
				if !open {
					ch2 = nil
					break
				}
				ch <- v
			}
		}
		close(ch)
	}()

	return ch
}
