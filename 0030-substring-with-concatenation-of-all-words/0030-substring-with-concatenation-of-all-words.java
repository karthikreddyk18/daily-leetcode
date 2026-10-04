class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        if (sLen < totalLen) return result;

        // Frequency map of the target words
        Map<String, Integer> targetCount = new HashMap<>();
        for (String w : words) {
            targetCount.put(w, targetCount.getOrDefault(w, 0) + 1);
        }

        // Iterate over all possible word-alignment offsets
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> windowCount = new HashMap<>();
            int matchedWords = 0;

            while (right + wordLen <= sLen) {
                // Extract current word
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (targetCount.containsKey(word)) {
                    windowCount.put(word, windowCount.getOrDefault(word, 0) + 1);
                    matchedWords++;

                    // If word frequency exceeds required count, shrink from left
                    while (windowCount.get(word) > targetCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowCount.put(leftWord, windowCount.get(leftWord) - 1);
                        matchedWords--;
                        left += wordLen;
                    }

                    // Found a valid substring
                    if (matchedWords == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Invalid word encountered; reset window
                    windowCount.clear();
                    matchedWords = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}