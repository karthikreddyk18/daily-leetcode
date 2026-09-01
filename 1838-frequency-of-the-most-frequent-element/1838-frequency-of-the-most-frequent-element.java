class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        long actualSum = 0;
        int maxFreq = 0;
        for (int right = 0; right < nums.length; right++) {
            actualSum += nums[right];

            // If operations needed exceed k, shrink window from left
            while ((long) nums[right] * (right - left + 1) - actualSum > k) {
                actualSum -= nums[left];
                left++;
            }

            maxFreq = Math.max(maxFreq, right - left + 1);
        }

        return maxFreq;
    }
}