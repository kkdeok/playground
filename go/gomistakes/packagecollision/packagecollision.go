package packagecollision

import (
	"fmt"

	"github.com/kkdeok/playground/go/gomistakes/packagecollision/redis"
)

func Process() {
	// 문법에 어긋나지는 않지만 이렇게 사용하면 안된다. 변수 스코프 안에서 redis 패키지를 사용할 수 없기 때문이다.
	redis := redis.NewClient()
	v, _ := redis.Get("foo")
	fmt.Println(v)
}
