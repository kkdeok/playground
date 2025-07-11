package leetcode

const FAKE = 1000000

func removeElement(nums []int, val int) int {
	l := len(nums)
	var cnt int
	for i := 0; i < l; i++ {
		if nums[i] == val {
			nums[i] = FAKE
			for j := l - 1; j > i; j-- {
				if nums[j] != val {
					temp := nums[i]
					nums[i] = nums[j]
					nums[j] = temp
				}
			}
		} else {
			cnt++
		}
	}
	return cnt
}
