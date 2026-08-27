class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] globalCount = new int[26];
        for (char c : s.toCharArray()) {
            globalCount[c - 'a']++;
        }

        // Try every prefix length from n - 1 down to 0
        for (int i = n - 1; i >= 0; i--) {
            int[] count = globalCount.clone();
            boolean prefixValid = true;

            // Step 1: Use characters needed to match target[0 ... i - 1]
            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (--count[charIdx] < 0) {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) {
                continue;
            }

            // Step 2: Try to find the smallest char strictly greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            int chosenChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (count[c] > 0) {
                    chosenChar = c;
                    break;
                }
            }

            // Step 3: If a valid char is found, build the answer
            if (chosenChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + chosenChar));
                count[chosenChar]--;

                // Append remaining characters in ascending order
                for (int c = 0; c < 26; c++) {
                    while (count[c] > 0) {
                        sb.append((char) ('a' + c));
                        count[c]--;
                    }
                }

                return sb.toString();
            }
        }

        return "";
    }
}