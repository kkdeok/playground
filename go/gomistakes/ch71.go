package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

func main() {
	wg := sync.WaitGroup{}
	var v uint64

	// wg.Add(1) 이 부모 고루틴이 아닌 새로 생성된 고루틴에서 호출되고 있다.
	// 따라서, wg.Wait() 을 호출하기 전에 대기 그룹에게 세 고루틴이 끝나길 기다리고록 알리지 못할 수도 있다.
	//for i := 0; i < 3; i++ {
	//	go func() {
	//		wg.Add(1)
	//		atomic.AddUint64(&v, 1)
	//		wg.Done()
	//	}()
	//}

	for i := 0; i < 3; i++ {
		wg.Add(1)
		go func() {
			atomic.AddUint64(&v, 1)
			wg.Done()
		}()
	}
	wg.Wait()
	fmt.Println(v)
}
