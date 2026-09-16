class Solution {
    public int splitArray(int[] nums, int k) {
        long low = 0;
        long high = 0;

        for (int num : nums) {
            low = Math.max(low, num); // Subarray sum must at least fit the largest single element
            high += num;              // Subarray sum cannot exceed the sum of all elements
        }

        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                ans = mid;
                high = mid - 1; // Try to find a smaller maximum sum
            } else {
                low = mid + 1;  // Limit is too small; need a larger maximum sum
            }
        }

        return (int) ans;
    }

    private boolean canSplit(int[] nums, int k, long maxAllowedSum) {
        int subarrays = 1;
        long currentSum = 0;

        for (int num : nums) {
            if (currentSum + num > maxAllowedSum) {
                subarrays++;
                currentSum = 0;
            }
            currentSum += num;

            if (subarrays > k) {
                return false; // Exceeded the allowed k partitions
            }
        }

        return subarrays <= k;
    }
}