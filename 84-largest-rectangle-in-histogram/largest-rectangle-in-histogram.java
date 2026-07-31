class Solution {
    public int largestRectangleArea(int[] heights) {

        int maxArea =0;
        int nsl[] = new int[heights.length];
        int nsr[] = new int[heights.length];

        //Nest smaller right
        Stack<Integer>st = new Stack<>();
        for(int i = heights.length-1; i>=0;i--){
        while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
            st.pop();
        }
        if(st.isEmpty()){
            nsr[i] = heights.length;
        }
        else{
            nsr[i] = st.peek();
        }
        st.push(i);
        }
        //Next smaller left
        st = new Stack<>();
        for(int i=0;i<heights.length;i++){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i]){
                st.pop();

            }
            if(st.isEmpty()){
                nsl[i] = -1;

            }
            else{
                nsl[i] = st.peek();
            }
            st.push(i);
            
        }
        for(int i=0;i<heights.length;i++){
            int height = heights[i];
            int width = nsr[i] - nsl[i] - 1;
            int currArea = height * width;
            maxArea = Math.max(currArea,maxArea);
        }
        return maxArea;
    }
    
}