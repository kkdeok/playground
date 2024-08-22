package variableshadow

import "fmt"

func Process() {
	i := 0
	if true {
		i := 1 // 해당 변수는 가려진다.
		fmt.Println(i)
	}
	fmt.Println(i)
}
