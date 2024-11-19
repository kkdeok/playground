package main

import (
	"context"
	"errors"
	"golang.org/x/sync/errgroup"
)

type Circle struct{}

type Result struct{}

func foo(ctx context.Context, circle Circle) (Result, error) {
	return Result{}, errors.New("hello world")
}

func handler(ctx context.Context, circles []Circle) ([]Result, error) {
	results := make([]Result, len(circles))
	g, ctx := errgroup.WithContext(ctx)

	for i, circle := range circles {
		i := i
		circle := circle
		g.Go(func() error {
			result, err := foo(ctx, circle)
			if err != nil {
				return err
			}
			results[i] = result
			return nil
		})
	}

	if err := g.Wait(); err != nil {
		return nil, err
	}
	return results, nil
}

func main() {
	handler(context.Background(), make([]Circle, 3))
}
