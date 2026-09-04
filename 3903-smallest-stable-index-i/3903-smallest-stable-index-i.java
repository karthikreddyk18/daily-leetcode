class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n=nums.length;
        int sufix[]=new int [n];
        sufix[n-1]=nums[n-1];
        for(int i=n-2;i>=0;i--){
            sufix[i]=Math.min(nums[i],sufix[i+1]);
        }
        int prefix=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            prefix=Math.max(prefix,nums[i]);
            int ins=prefix-sufix[i];
            if(ins<=k){
                return i;
            }
        }
        return -1;
    }
}