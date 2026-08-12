class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(int i =0;i< s.length();i++){
            char ch = s.charAt(i);
            if(ch =='(' || ch =='{' || ch =='[' ){
                st.push(ch);
            }else{
                if(st.isEmpty()){
                    return false;
                }
                char top = st.peek();
                if((top== '(' && ch == ')') ||
                (top == '{' && ch == '}') ||
                (top == '[' && ch==']')){
                    st.pop();
                }
                else{
                    return false;
                }
            }

        }
        return st.isEmpty();
        
    }
}







// import java.util.Stack;

// class Solution {
//     public boolean isValid(String str) {
//         Stack<Character> st = new Stack<>();

//         for (int i = 0; i < str.length(); i++) {
//             char ch = str.charAt(i);

//             // If it's an opening bracket, push to stack
//             if (ch == '(' || ch == '{' || ch == '[') {
//                 st.push(ch);
//             } 
//             // If it's a closing bracket, check for match
//             else {
//                 if (st.isEmpty()) {
//                     return false;
//                 }

//                 char top = st.peek();

//                 if ((top == '(' && ch == ')') ||
//                     (top == '{' && ch == '}') ||
//                     (top == '[' && ch == ']')) {
//                     st.pop();
//                 } else {
//                     return false;
//                 }
//             }
//         }

//         // If stack is empty, all brackets matched
//         return st.isEmpty();
//     }
// }
