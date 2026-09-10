class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canEat(piles, h, mid)) {
                right = mid; // try smaller speed
            } else {
                left = mid + 1; // need bigger speed
            }
        }
        return left;
    }
    
    private boolean canEat(int[] piles, int h, int speed) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed; // ceil(pile/speed)
        }
        return hours <= h;
    }
}
