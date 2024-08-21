package functionaloption

import (
	"errors"
	"net/http"
	"strconv"
)

type options struct {
	port *int
}

// 설정 구조체를 업데이트하는 함수
type Option func(options *options) error

const defaultHTTPPort = 8080

// 함수형 옵션 패턴을 사용하여 설정을 관리하는 방식
func WithPort(port int) Option {
	return func(options *options) error {
		if port < 0 {
			return errors.New("port must be positive value")
		}
		options.port = &port
		return nil
	}
}

func NewServer(addr string, opts ...Option) (*http.Server, error) {
	var options options
	for _, opt := range opts { // opt 는 Option 타입의 함수다. func(options *options) error
		err := opt(&options)
		if err != nil {
			return nil, err
		}
	}

	// 포트 검증 로직을 여기서 구현할 수 있다.
	var port int
	if options.port == nil {
		port = defaultHTTPPort
	} else {
		if *options.port <= 0 {
			// do something
			port = *options.port
		} else {
			port = *options.port
		}
	}

	// HTTP 핸들러 설정
	mux := http.NewServeMux()
	mux.HandleFunc("/hello", func(w http.ResponseWriter, req *http.Request) {
		w.Write([]byte("Hello World"))
	})

	// 서버 인스턴스 생성
	server := &http.Server{
		Addr:    addr + ":" + strconv.Itoa(port),
		Handler: mux,
	}

	return server, nil
}

func Process() {
	NewServer("localhost")
}
