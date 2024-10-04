package main

import (
	"errors"
	"fmt"
	"net/http"
)

func getTransactionAmount(transactionID string) (float32, error) {
	amount, err := getTransactionAmountFromDB(transactionID)
	if err != nil {
		return 0, fmt.Errorf("failed to get transaction %s: %w", transactionID, err)
	}
	return amount, nil
}

func getTransactionAmountFromDB(transactionID string) (float32, error) {
	return 0, transientError{err: errors.New("TransactionAmount Error")}
}

type transientError struct {
	err error
}

func (t transientError) Error() string {
	return fmt.Sprintf("transient error: %v", t.err)
}

// 간단한 핸들러 함수
func helloHandler(w http.ResponseWriter, r *http.Request) {
	_, err := getTransactionAmount("1")

	if err != nil {
		if errors.As(err, &transientError{}) {
			http.Error(w, err.Error(), http.StatusServiceUnavailable)
		} else {
			http.Error(w, err.Error(), http.StatusBadRequest)
		}
		return
	}
}

func main() {
	http.HandleFunc("/hello", helloHandler)
	http.ListenAndServe(":8080", nil)
}
