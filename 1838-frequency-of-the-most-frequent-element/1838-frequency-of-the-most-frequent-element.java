class Solution {
    public int maxFrequency(int[] nums, int k) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) maxVal = num;
        }
        
      
        int[] counts = new int[maxVal + 1];
        for (int num : nums) {
            counts[num]++;
        }

        int idx = 0;
        for(int i=0; i<= maxVal; i++){
            while(counts[i] > 0){
                nums[idx++] = i;
                counts[i]--;
            }
        }

        int lef = 0;
        long currWin =0;
        
        for(int rig=0; rig < nums.length; rig++){
            currWin += nums[rig];

            if((long) nums[rig] * (rig - lef + 1) - currWin > k){
                currWin -= nums[lef];
                lef++;
            }
        }
        return nums.length - lef;
        
    }
}