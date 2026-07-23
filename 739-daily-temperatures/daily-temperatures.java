class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n= temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!stack.isEmpty() && temperatures[i]>temperatures[stack.peek()]){
                int prevDay = stack.pop();
                ans[prevDay] = i-prevDay;
            }
            stack.push(i);
        }
        return ans;
        
    }
}