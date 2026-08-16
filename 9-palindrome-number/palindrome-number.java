class Solution {
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        String rev = new StringBuilder(s).reverse().toString();
        return s.equals(rev);
        
    }
}