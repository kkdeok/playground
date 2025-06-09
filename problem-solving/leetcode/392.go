package leetcode

func isSubsequence(s string, t string) bool {
	if len(s) == 0 {
		return true
	}
	if len(t) == 0 {
		return false
	}

	sRightIdx := 0
	sEndIdx := len(s) - 1

	tStartIndex := 0
	tEndIndex := len(t) - 1
}
