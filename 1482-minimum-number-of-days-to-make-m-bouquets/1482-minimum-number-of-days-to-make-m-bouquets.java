class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if((long)m*k>bloomDay.length)
        return -1;
        int low=1;
        int high=Integer.MAX_VALUE;
        for(int i:bloomDay){
            low=Math.min(i,low);
            high=Math.max(i,high);
        }
        int ans=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(ispos(bloomDay,m,k,mid)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
    static boolean ispos(int []arr,int bouq,int flowr,int day){
        int bouqc=0;
        int freq=0;
        for(int i:arr){
            if(i<=day){
                freq++;
                if(freq==flowr){
                    bouqc++;
                    freq=0;
                }
            }else{
                freq=0;
            }
            if(bouqc>=bouq){
                return true;
            }
        }
        return bouqc>=bouq;
    }
}