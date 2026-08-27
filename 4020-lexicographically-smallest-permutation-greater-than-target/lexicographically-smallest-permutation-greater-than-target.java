class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Frequency of characters in s
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder prefix = new StringBuilder();

        // Step 1: Match target as much as possible
        for (int i = 0; i < n; i++) {

            int cur = target.charAt(i) - 'a';

            // Same character available
            if (freq[cur] > 0) {

                freq[cur]--;
                prefix.append(target.charAt(i));

            } else {

                // Same character unavailable.
                // Try smallest character greater than target[i].
                for (int j = cur + 1; j < 26; j++) {

                    if (freq[j] > 0) {

                        StringBuilder ans = new StringBuilder(prefix);

                        ans.append((char) ('a' + j));
                        freq[j]--;

                        // Remaining characters in sorted order
                        for (int k = 0; k < 26; k++) {
                            while (freq[k] > 0) {
                                ans.append((char) ('a' + k));
                                freq[k]--;
                            }
                        }

                        return ans.toString();
                    }
                }

                // Cannot continue from here.
                // Need to backtrack.
                break;
            }
        }

        /*
         * Step 2:
         * Backtrack from the last matched position.
         *
         * We restore the character we had used,
         * then try to put a character greater than target[i].
         */
        for (int i = prefix.length() - 1; i >= 0; i--) {

            int cur = target.charAt(i) - 'a';

            // Put target[i] back into available characters
            freq[cur]++;

            // Try smallest character greater than target[i]
            for (int j = cur + 1; j < 26; j++) {

                if (freq[j] > 0) {

                    StringBuilder ans =
                        new StringBuilder(target.substring(0, i));

                    ans.append((char) ('a' + j));
                    freq[j]--;

                    // Fill remaining characters smallest first
                    for (int k = 0; k < 26; k++) {
                        while (freq[k] > 0) {
                            ans.append((char) ('a' + k));
                            freq[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}