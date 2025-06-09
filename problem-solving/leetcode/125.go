package leetcode

func isPalindrome(s string) bool {
	// condition:D
	// 1. covert all uppercase letter into lowercase letters
	// 2. removing all non-alphanumeric characters, which is except letters and numbers.

	var runes []rune
	for _, c := range s {
		if c >= 'A' && c <= 'Z' {
			c = c - 'A' + 'a'
		}
		if (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') {
			runes = append(runes, c)
		}
	}
	parsedString := string(runes)
	length := len(parsedString)
	for i := 0; i < length; i++ {
		if i > length-1-i {
			break
		}
		if parsedString[i] != parsedString[length-i-1] {
			return false
		}
	}
	return true
}
