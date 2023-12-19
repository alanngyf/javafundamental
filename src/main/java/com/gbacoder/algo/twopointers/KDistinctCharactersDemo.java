package com.gbacoder.algo.twopointers;

import java.util.HashMap;

/**
 * @author alanulog
 * @create 2023-12-19
 * https://www.lintcode.com/problem/1375/
 *
 * Lintcode 1375 · Substring With At Least K Distinct Characters
 *
 * Given a string S with only lowercase characters.
 * Return the number of substrings that contains at least k distinct characters.
 *
 * example:
 * Input: S = "abcabcabca", k = 4
 * Output: 0
 * Explanation: There are only three distinct characters in the string.
 *
 * Input: S = "abcabcabcabc", k = 3
 * Output: 55
 * Explanation: Any substring whose length is not smaller than 3 contains a, b, c.
 *     For example, there are 10 substrings whose length are 3, "abc", "bca", "cab" ... "abc"
 *     There are 9 substrings whose length are 4, "abca", "bcab", "cabc" ... "cabc"
 *     ...
 *     There is 1 substring whose length is 12, "abcabcabcabc"
 *     So the answer is 1 + 2 + ... + 10 = 55.
 */
public class KDistinctCharactersDemo {
    public static void main(String[] args) {

    }
}

class KDistinctCharacters {
    public long kDistinctCharacters(String s, int k) {
        long numOfSubstring = 0L;
        int distinctChars = 0;
        int len = s.length();
        HashMap<Character, Integer> charCounter = new HashMap<>();

        int j = 0;
        for (int i = 0; i < len; i++) {
            while (j < len && distinctChars < k) {
                int valOfChar = charCounter.getOrDefault(s.charAt(j), 0);
                charCounter.put(s.charAt(j), valOfChar + 1);
                if (charCounter.get(s.charAt(j)) == 1) {
                    distinctChars++;
                }
                j++;
            }

            if (distinctChars == k) {
                numOfSubstring += (len - j + 1);
            }
            charCounter.put(s.charAt(i), charCounter.get(s.charAt(i)) - 1);
            if (charCounter.get(s.charAt(i)) == 0) {
                distinctChars--;
            }
        }
        return numOfSubstring;

    }
}
