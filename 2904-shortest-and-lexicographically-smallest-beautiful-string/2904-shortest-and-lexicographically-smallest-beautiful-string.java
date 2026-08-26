class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }
        if (ones.size() < k) {
            return "";
        }
        String result = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i + k - 1 < ones.size(); i++) {
            int start = ones.get(i);
            int end = ones.get(i + k - 1);
            int len = end - start + 1;
            String sub = s.substring(start, end + 1);

            if (len < minLen) {
                minLen = len;
                result = sub;
            } else if (len == minLen) {
                if (sub.compareTo(result) < 0) {
                    result = sub;
                }
            }
        }

        return result;
    }
}