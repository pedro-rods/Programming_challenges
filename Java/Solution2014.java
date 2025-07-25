/*
 * LeetCode Problem 2014: Longest Subsequence Repeated k Times
 * link:https://leetcode.com/problems/longest-subsequence-repeated-k-times/description/?envType=daily-question
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution2014 {
	public static void main(String[] args) {
		Solution(
				"letsleetcode",
				2);
	}

	public static String Solution(String s, int k) {
		Map<Character, Integer> freq = new HashMap<>();
		int maxLenght = s.length() / k;
		for (char c : s.toCharArray()) {
			freq.put(c, freq.getOrDefault(c, 0) + 1);
		}

		List<Character> validChars = new ArrayList<>();
		for (char c = 'z'; c >= 'a'; c--) {
			if (freq.getOrDefault(c, 0) >= k) {
				validChars.add(c);
			}
		}

		Queue<String> queue = new LinkedList<>();
		queue.offer("");

		String best = "";

		while (!queue.isEmpty()) {
			String cur = queue.poll();

			for (char c : validChars) {
				String next = cur + c;

				if (isRepeatedSubsequence(s, next, k)) {
					if (next.length() > best.length() || (next.length() == best.length() && next.compareTo(best) > 0)) {
						best = next;
					}
					if (next.length() <= maxLenght) { // 7 * k <= 2000 → seguro pelo enunciado
						queue.offer(next);
					}
				}
			}
		}

		System.out.println(best);
		return best;
	}

	private static boolean isRepeatedSubsequence(String s, String pattern, int k) {
		int i = 0, j = 0, count = 0;
		while (i < s.length()) {
			if (s.charAt(i) == pattern.charAt(j)) {
				j++;
				if (j == pattern.length()) {
					count++;
					if (count == k)
						return true;
					j = 0;
				}
			}
			i++;
		}
		return false;
	}
}