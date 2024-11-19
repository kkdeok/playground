package main

import (
	"context"
	"log"
	"time"
)

func main() {

	ctx, cancel := context.WithCancel(context.Background())
	defer func() {
		cancel()
		log.Printf("context canceled")
	}()

	err := startWatcher(ctx)
	if err != nil {
		return
	}

}

func startWatcher(ctx context.Context) error {
	w := Watcher{ctx: ctx}
	go func() {
		err := w.start()
		if err != nil {
			log.Printf("watcher error: %v", err)
		}
	}()
	log.Printf("Watcher started")
	return nil
}

type Watcher struct {
	ctx context.Context
}

func (w *Watcher) start() error {
	for {
		select {
		case <-w.ctx.Done():
			log.Printf("context is done")
			w.close()
			return w.ctx.Err()
		default:
			log.Printf("watching...")
			time.Sleep(1 * time.Second)
		}
	}
}

func (w *Watcher) close() {
	log.Printf("watcher closing...")
	time.Sleep(3 * time.Second)
	log.Printf("watcher closed")
}

//func main() {
//
//	wg := &sync.WaitGroup{}
//	ctx, cancel := context.WithCancel(context.Background())
//	defer func() {
//		cancel()
//		wg.Wait()
//	}()
//
//	wg.Add(1)
//	err := startWatcher(ctx, wg)
//	if err != nil {
//		return
//	}
//}
//
//func startWatcher(ctx context.Context, wg *sync.WaitGroup) error {
//	w := Watcher{ctx: ctx}
//	go func() {
//		defer wg.Done()
//		err := w.start()
//		if err != nil {
//			log.Printf("watcher error: %v", err)
//		}
//		log.Printf("watcher done")
//	}()
//	log.Printf("Watcher started")
//	return nil
//}
//
//type Watcher struct {
//	ctx context.Context
//}
//
//func (w *Watcher) start() error {
//	for {
//		select {
//		case <-w.ctx.Done():
//			log.Printf("context is done")
//			w.close()
//			return w.ctx.Err()
//		default:
//			log.Printf("watching...")
//			time.Sleep(1 * time.Second)
//		}
//	}
//}
//
//func (w *Watcher) close() {
//	log.Printf("watcher closing...")
//	time.Sleep(3 * time.Second)
//	log.Printf("watcher closed")
//}
