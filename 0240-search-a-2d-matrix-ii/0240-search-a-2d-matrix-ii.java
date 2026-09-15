class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int c=matrix[0].length-1;
        int tr=matrix.length;
        int r=0;
        while(r<tr && c>=0){
            if(matrix[r][c]==target){
                return true;
            }else if(matrix[r][c]>target){
                c--;
            }else{
                r++;
            }
        }
        return false;
    }
}