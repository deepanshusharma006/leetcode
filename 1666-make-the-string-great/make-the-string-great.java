class Solution {
    public String makeGood(String s) {
        Stack<Character> st = new Stack<>();

        for (char ch : s.toCharArray()) {

            if (!st.isEmpty() && Math.abs(st.peek() - ch) == 32) {
                st.pop();
            } else {
                st.push(ch);
            }
        }

        StringBuilder str = new StringBuilder();

        for (char ch : st) {
            str.append(ch);
        }

        return str.toString();
    }
}