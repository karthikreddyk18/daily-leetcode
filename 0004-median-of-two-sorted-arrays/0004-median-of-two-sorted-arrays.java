class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        if( nums1.length > nums2.length ) return findMedianSortedArrays( nums2, nums1);

        int x = nums1.length;
        int y = nums2.length;
        int total = x + y;

        int left =0, right = x;


        while( left<= right){


            
            int midX = (left + right) / 2;

            // go with total /2 - midX
            int midY = (total + 1)/2 - midX;

            // --- HANDLE EXTREME EDGE CASES (OUT OF BOUNDS) ---
            
            // 1. If midX or midY is 0, no elements from that array are on the left side.
            //    We use Integer.MIN_VALUE (-∞) because any real number is larger than it
            // 2. If midX or midY equals the array length, all elements are on the left side.
            //    We use Integer.MAX_VALUE (+∞) because any real number is smaller than it.


            int xLeftMax = (midX ==0)? Integer.MIN_VALUE : nums1[midX - 1];
            int xRightMin = (midX == x)? Integer.MAX_VALUE : nums1[midX];

            int yLeftMax = (midY ==0)? Integer.MIN_VALUE : nums2[midY - 1];
            int yRightMin = (midY == y)? Integer.MAX_VALUE : nums2[midY];

            if( xLeftMax <= yRightMin && yLeftMax <= xRightMin) {

                if( total%2 == 0){
                    return (Math.max(xLeftMax, yLeftMax) + Math.min(xRightMin, yRightMin)) / 2.0;
                }

                return Math.max(xLeftMax, yLeftMax);
            }


            if( xLeftMax > yRightMin){
                right = midX - 1;
            }else {
                left = midX + 1;
            }
        }

        return 0;
    }
}