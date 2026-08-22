class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer>map = new HashMap<>();
      long sum =0;
      long maxsum =0;
      int left=0;
      for(int right =0;right<nums.length;right++){
        map.put(nums[right],map.getOrDefault(nums[right],0)+1);
        sum += nums[right];
        if(right - left + 1 > k ){
         int x = nums[left];
         sum -=x;
         map.put(x,map.get(x)-1);
         if(map.get(x)==0){
            map.remove(x);
         }
         left++;
        }
        if(right - left + 1 ==k && map.size()==k ){
            maxsum = Math.max(maxsum,sum);
        }
      }
     return maxsum;   
    }
}

