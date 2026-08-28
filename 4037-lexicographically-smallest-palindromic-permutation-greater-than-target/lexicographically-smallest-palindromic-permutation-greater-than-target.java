class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // Frequency of characters
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Check whether palindrome is possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Characters available for left half
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        int halfLen = n / 2;

        StringBuilder left = new StringBuilder();

        // Build left half
        for (int pos = 0; pos < halfLen; pos++) {

            boolean found = false;

            // Try smallest character first
            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) {
                    continue;
                }

                // Take this character
                half[c]--;
                left.append((char) ('a' + c));

                // Build maximum possible palindrome
                String candidate = buildMaximumPalindrome(
                    left,
                    half,
                    middle,
                    n
                );

                // Is there some palindrome greater than target?
                if (candidate.compareTo(target) > 0) {
                    found = true;
                    break;
                }

                // Undo
                left.deleteCharAt(left.length() - 1);
                half[c]++;
            }

            if (!found) {
                return "";
            }
        }

        // Construct final answer
        String leftPart = left.toString();

        StringBuilder ans = new StringBuilder();

        ans.append(leftPart);

        if (n % 2 == 1) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(leftPart).reverse());

        String result = ans.toString();

        return result.compareTo(target) > 0 ? result : "";
    }


    private String buildMaximumPalindrome(
        StringBuilder left,
        int[] half,
        char middle,
        int n
    ) {

        StringBuilder temp = new StringBuilder(left);

        // Put remaining characters in DESCENDING order
        for (int c = 25; c >= 0; c--) {

            for (int count = 0; count < half[c]; count++) {
                temp.append((char) ('a' + c));
            }
        }

        String leftPart = temp.toString();

        StringBuilder palindrome = new StringBuilder();

        palindrome.append(leftPart);

        if (n % 2 == 1) {
            palindrome.append(middle);
        }

        palindrome.append(
            new StringBuilder(leftPart).reverse()
        );

        return palindrome.toString();
    }
}