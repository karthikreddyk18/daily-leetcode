// class Solution {
//     public int helper(int [] b,int mid,int m,int k){
//         // this helper function traverse from start of array
//         //till mid and returns 
//         int adj_flower=0;
//         int bouq_made=0;
//         for(int i=0;i<b.length;i++){
//             if(b[i]<=mid){
//                 adj_flower++;
//             }
//             if(adj_flower==k){
//                 bouq_made++;
//                 adj_flower = 0;//reset to start from once again
//             }
//             if(b[i]>mid){
//                 //flower not bloomed 
//                 adj_flower=0;
//             }

//         }
//         if(bouq_made>=m){
//             return 1;
//         }
//         else{
//             return 0;
//         }
//     }
//     public int minDays(int[] b, int m, int k) {
//         // edge case is that when total number of flower is less than required
//         // long long  req=m*k;
//         // if(req>b.length){
//         //     return -1;

//         // }// 
//         // Correct way: Cast 'm' to long first, so the multiplication happens in 64-bit space
// if ((long) m * k > b.length) {
//     return -1;
// }
//         int min = Arrays.stream(b).min().getAsInt();
//         int max = Arrays.stream(b).max().getAsInt();
//         int low=min;
//         int high=max;
//         int ans=0;
//         while(low<high){
//             int mid=low+(high-low)/2;
//             int res=helper(b,mid,m,k);
//             if(res==1)
//             {
//                 // ans=mid;
//                 high=mid;
             




//             }
//             else if(res==0)
//             {
//                 low = mid+1;
//             }

//         }
//         return low;


//     }
// }
























class Solution {
    
        
    boolean possible(int[] bloomDay, int m, int k, int mid)
    {
        int flowers=0;
        int bouquets=0;
        int n=bloomDay.length;
        for(int i=0; i<n; i++)
        {
            if(bloomDay[i] <= mid)
            {
                flowers++;
                if(flowers == k)
                {
                    bouquets++;
                    flowers = 0;
                }
            }
            else
            {
                flowers = 0;
                if ((m - bouquets) * k > n - i - 1)
                    break;
            }
            if (bouquets >= m)
                break;
        }

        return bouquets >= m;

    }
    public int minDays(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        if ((long) m * k > n)
            return -1;
        
        int r = 0;
        for (int i=0; i < n; i++) {
            if (bloomDay[i] > r) {
                r = bloomDay[i];
            }
        }

        int l=1;
        int result = -1;
        while(l <= r)
        {
            int mid = l + (r - l)/2;
            if(possible(bloomDay, m, k, mid))
            {
                result = mid;
                r = mid  - 1;
            }

            else
            {
                l = mid + 1;
            }
        }
        
        return result;
    }
}