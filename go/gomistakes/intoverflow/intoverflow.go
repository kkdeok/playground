package intoverflow

import (
	"fmt"
	"math"
)

func Process() {
	var counter int32 = math.MaxInt32
	counter++
	fmt.Printf("counter=%d\n", counter)
}
