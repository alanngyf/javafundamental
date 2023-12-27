package com.gbacoder.algo.twopointers;

import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

import java.util.HashMap;

/**
 * @author alanulog
 * @create 2023-12-23
 *
 * Lintcode 32 · Minimum Window Substring
 * Given two strings source and target. Return the minimum substring of source which contains each char of target.
 *
 * Example 1:
 * Input:
 * source = "abc"
 * target = "ac"
 *
 * Output:
 * "abc"
 *
 * Example 2:
 * Input:
 * source = "adobecodebanc"
 * target = "abc"
 *
 * Output:
 * "banc"
 */
public class MinWindowSubstringDemo {
    public static void main(String[] args) {
        System.out.println(MinWindowSubstring.minWindow("abc", "ac"));
        System.out.println(MinWindowSubstring.minWindow("adobecodebanc", "abc"));
    }
}

class MinWindowSubstring {
    public static String minWindow(String source, String target) {
        // corner case
        if (source == null || source.length() == 0 || target == null || target.length() == 0) {
            return "";
        }
        int m = target.length(), n = source.length();
        HashMap<Character, Integer> targetCounter = MinWindowSubstring.getTargetMap(target);
        HashMap<Character, Integer> subCounter = new HashMap<>();

        int matchChars = 0;
        int j = 0;
        int start = 0, substringLength = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            while (j < n && matchChars < targetCounter.size()) {
                int valOfChar = subCounter.getOrDefault(source.charAt(j), 0);
                subCounter.put(source.charAt(j), valOfChar + 1);
                if (subCounter.get(source.charAt(j)).equals(targetCounter.get(source.charAt(j)))) { // 包裝類 最好用equals
                    matchChars++;
                }
                j++;
            }

            if (matchChars == targetCounter.size()) {
                if (substringLength > j - i) {
                    substringLength = j - i;
                    start = i;
                }
            }
            int valOfChar_i = subCounter.getOrDefault(source.charAt(i), 0);
            subCounter.put(source.charAt(i), valOfChar_i - 1);

            if (subCounter.get(source.charAt(i)).equals(targetCounter.getOrDefault(source.charAt(i), 0) - 1)) {
                matchChars--;
            }

        }
        if (substringLength == Integer.MAX_VALUE) {
            return "";
        }

        return source.substring(start, start + substringLength);
    }

    public static HashMap<Character, Integer> getTargetMap(String target) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            Character ch = target.charAt(i);
            Integer val = map.getOrDefault(ch, 0);
            map.put(ch, val + 1);
        }

        return map;
    }
}
