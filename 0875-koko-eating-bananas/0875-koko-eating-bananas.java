class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l=1;
        int r=0;
        for(int i:piles){
            if(i>r){
                r=i;
            }
        }
        int ans=r;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(canEat(piles,mid,h)){
                ans=mid;
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return ans;
    }
    private boolean canEat(int[] piles, int k, int h) {
        long totalHours = 0;
        for (int pile : piles) {
            // Equivalent to Math.ceil((double) pile / k)
            totalHours += (pile + k - 1L) / k;
            if (totalHours > h) {
                return false; // Early pruning
            }
        }
        return totalHours <= h;
    }
}