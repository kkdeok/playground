package leetcode

import (
	"strings"
)

func romanToInt(s string) int {
	karr := []string{
		"M",  // 1000
		"CM", // 900
		"D",  // 500
		"CD", // 400
		"C",  // 100
		"XC", // 90
		"L",  // 50
		"XL", // 40
		"X",  // 10
		"IX", // 9
		"V",  // 5
		"IV", // 4
		"I",  // 1
	}

	varr := []int{
		1000,
		900,
		500,
		400,
		100,
		90,
		50,
		40,
		10,
		9,
		5,
		4,
		1,
	}

	res := 0
	for len(s) > 0 {
		for i := 0; i < len(karr); i++ {
			if strings.HasPrefix(s, karr[i]) {
				s = strings.TrimPrefix(s, karr[i])
				res = res + varr[i]
			}
		}
	}
	return res
}
