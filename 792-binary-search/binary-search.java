class Solution {
    public int search(int[] nums, int target) {
        int left =0;
        int right = nums.length-1;
        int i =-1;
        while(left<=right){
            int mid = left + (right-left)/2;
            if(target == nums[mid]){
                i = mid;
               return i;
             }else if(target < nums[mid]){
                right = mid-1;

             }else{
                left = mid+1;
             }
        }
        return -1;
        
    }
}