class Solution {
    public boolean uniformArray(int[] nums1) {
        int min=Integer.MAX_VALUE;
        boolean Odd=false;
        for(int i:nums1){
            if(i<min){
                min=i;
            }
            if(i%2==1){
                Odd=true;
            }
        }
        return (min%2==1)|| !Odd;
    }
}