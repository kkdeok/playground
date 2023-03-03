package main

import (
	"bufio"
	"container/list"
	"fmt"
	"os"
)

var reader *bufio.Reader
var writer *bufio.Writer

// N 장의 카드가 있다. 카드는 차례로 1부터 N까지 번호가 붙어있다.
// 1번 카드가 제일 위에, N번 카드가 제일 아래에 있다.
// 카드가 한 장 남을 때까지 다음과 같은 동작을 받복하게 된다.
// 우선 제일 위에 있는 ㅏ드를 바닥에 버린다.
// 그 다음 제일 위에 있는 카들르 제일 아래에 있는 카드 밑으로 옮긴다.
// N=4, 1234 / 342 -> 24 -> 4
func main() {
	reader = bufio.NewReader(os.Stdin)
	writer = bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	var n int
	fmt.Fscanln(reader, &n)

	queue := list.New()
	for i := 1; i <= n; i++ {
		queue.PushBack(i)
	}
	for {
		front := queue.Front()
		queue.Remove(front)
		if queue.Len() == 0 {
			fmt.Fprintln(writer, front.Value)
			break
		}

		front = queue.Front()
		queue.PushBack(front.Value)
		queue.Remove(front)
	}
}
