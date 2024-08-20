package mascot_test

import (
	"testing"

	"github.com/kkdeok/playground/go/demo/mascot"
)

func TestMascot(t *testing.T) {
	if mascot.BestMascot() != "Go Gopher" {
		t.Fatal("Wrong mascort :()")
	}
}
