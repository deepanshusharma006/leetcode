class Solution {
    public int climbStairs(int n) {
        if(n==1){
            return 1;
        }
        int a =0;int b=1;
        for(int i=0;i<n;i++){
        int climb = a+b;
        a=b;
        b=climb;
        }
        return b;
    }
}