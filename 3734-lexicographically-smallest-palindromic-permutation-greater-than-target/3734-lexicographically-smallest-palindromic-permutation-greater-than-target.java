class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Step 1: Check palindrome validity
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        if (oddCount > 1 || (oddCount == 1 && n % 2 == 0)) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Step 2: Option A - Check if exact match of first half works
        int[] tempHalf = halfCount.clone();
        boolean canMatchFirstHalf = true;
        for (int j = 0; j < m; j++) {
            int c = target.charAt(j) - 'a';
            if (--tempHalf[c] < 0) {
                canMatchFirstHalf = false;
                break;
            }
        }

        if (canMatchFirstHalf) {
            String candidate = buildPalindrome(target.substring(0, m), oddChar, n % 2 != 0);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Step 3: Option B - Try diverging at index i in the first half (from m - 1 down to 0)
        for (int i = m - 1; i >= 0; i--) {
            int[] curHalf = halfCount.clone();
            boolean validPrefix = true;

            // Use characters needed to match target[0 ... i - 1]
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (--curHalf[c] < 0) {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) {
                continue;
            }

            // Find smallest available character strictly greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            int chosenChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (curHalf[c] > 0) {
                    chosenChar = c;
                    break;
                }
            }

            if (chosenChar != -1) {
                StringBuilder firstHalf = new StringBuilder();
                firstHalf.append(target, 0, i);
                firstHalf.append((char) ('a' + chosenChar));
                curHalf[chosenChar]--;

                // Append remaining characters in ascending order
                for (int c = 0; c < 26; c++) {
                    while (curHalf[c] > 0) {
                        firstHalf.append((char) ('a' + c));
                        curHalf[c]--;
                    }
                }

                return buildPalindrome(firstHalf.toString(), oddChar, n % 2 != 0);
            }
        }

        return "";
    }

    private String buildPalindrome(String firstHalf, int oddChar, boolean isOddLength) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (isOddLength) {
            sb.append((char) ('a' + oddChar));
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}