import java.util.*;

public class WordSum {

    // Returns the sum of the digits represented by a word
    private static int getWordSum(String word, Map<Character, Integer> digitMap) {
        int sum = 0;
        for (char c : word.toCharArray()) {
            sum = sum * 10 + digitMap.get(c);
        }
        return sum;
    }

    // Returns true if the sum of the words equals the sum of the result
    public static boolean isWordSumEqual(String[] words, String result) {
        Map<Character, Integer> digitMap = new HashMap<>();
        int[] digitCount = new int[10];
        int count = 0;

        // Count the number of occurrences of each digit and map each character to a digit
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!digitMap.containsKey(c)) {
                    digitMap.put(c, count);
                    digitCount[count]++;
                    count++;
                }
            }
        }
        for (char c : result.toCharArray()) {
            if (!digitMap.containsKey(c)) {
                digitMap.put(c, count);
                digitCount[count]++;
                count++;
            }
        }

        // Check all possible combinations of digits
        int[] digits = new int[count];
        return isWordSumEqual(words, result, digitMap, digits, digitCount, 0);
    }

    private static boolean isWordSumEqual(String[] words, String result, Map<Character, Integer> digitMap, int[] digits, int[] digitCount, int pos) {
        if (pos == digits.length) {
            // Check if the sum of the words equals the sum of the result
            int wordSum = 0;
            for (String word : words) {
                wordSum += getWordSum(word, digitMap);
            }
            int resultSum = getWordSum(result, digitMap);
            return wordSum == resultSum;
        }
        for (int i = 0; i < 10; i++) {
            if (digitCount[i] == 0) continue;
            digitCount[i]--;
            digits[pos] = i;
            if (isWordSumEqual(words, result, digitMap, digits, digitCount, pos + 1)) {
                return true;
            }
            digitCount[i]++;
        }
        return false;
    }

    // Test the implementation
    public static void main(String[] args) {
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        System.out.println(isWordSumEqual(words, result)); // true

        String[] words2 = {"ONE", "TWO", "THREE", "FOUR"};
        String result2 = "NINE";
        System.out.println(isWordSumEqual(words2, result2)); // true

        String[] words3 = {"ONE", "TWO", "THREE", "FOUR"};
        String result3 = "TEN";
        System.out.println(isWordSumEqual(words3, result3)); // false
    }
}
