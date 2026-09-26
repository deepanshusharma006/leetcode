class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> map = new HashMap<>();

        // Store knowledge in HashMap
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            // Normal character
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
            }

            // Bracket pair
            else {
                i++; // skip '('

                StringBuilder key = new StringBuilder();

                // Read key until ')'
                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                // Convert key to String
                String k = key.toString();

                // Check key in HashMap
                if (map.containsKey(k)) {
                    ans.append(map.get(k));
                } else {
                    ans.append("?");
                }

                i++; // skip ')'
            }
        }

        return ans.toString();
    }
}