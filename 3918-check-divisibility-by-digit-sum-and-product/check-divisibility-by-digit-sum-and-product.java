class Solution {
    public boolean checkDivisibility(int n) {
        int digitSum = 0;
        int digitProduct = 1;
        int temp = n;
        
        // Extract digits mathematically
        while (temp > 0) {
            int digit = temp % 10;
            digitSum += digit;
            digitProduct *= digit;
            temp /= 10;
        }
        
        // Sum of the digit sum and digit product
        int combinedSum = digitSum + digitProduct;
        
        // Return true if n is evenly divisible by combinedSum
        return n % combinedSum == 0;
    }
}
