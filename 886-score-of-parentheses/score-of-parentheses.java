class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                st.push(0);
            } 
            else {
                int top = st.pop();

                if (top == 0) {
                    top = 1;
                } 
                else {
                    top = 2 * top;
                }

                st.push(st.pop() + top);
            }
        }

        return st.peek();
    }
}