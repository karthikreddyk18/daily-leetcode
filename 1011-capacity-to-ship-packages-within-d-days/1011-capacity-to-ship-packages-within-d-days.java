class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;

        for (int w : weights) {
            low = Math.max(low, w); // Must at least fit the largest single item
            high += w;              // Maximum possible capacity needed (1 day)
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canShip(weights, mid, days)) {
                ans = mid;
                high = mid - 1; // Try to find a smaller feasible capacity
            } else {
                low = mid + 1;  // Capacity too small, increase it
            }
        }

        return ans;
    }

    private boolean canShip(int[] weights, int capacity, int days) {
        int daysNeeded = 1;
        int currentLoad = 0;

        for (int w : weights) {
            if (currentLoad + w > capacity) {
                daysNeeded++;
                currentLoad = 0;
            }
            currentLoad += w;

            if (daysNeeded > days) {
                return false; // Early exit
            }
        }

        return daysNeeded <= days;
    }
}