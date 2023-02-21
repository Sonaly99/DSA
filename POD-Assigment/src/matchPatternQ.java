public class matchPatternQ {
    public static boolean matchPattern(String str, String pattern) {
        int s = 0, p = 0, starIdx = -1, match = 0;
        while (s < str.length()) {
            if (p < pattern.length() && (pattern.charAt(p) == str.charAt(s) || pattern.charAt(p) == '#')) {
                s++;
                p++;
                match++;
            } else if (p < pattern.length() && pattern.charAt(p) == '@') {
                starIdx = p;
                match = s;
                p++;
            } else if (starIdx != -1) {
                p = starIdx + 1;
                match++;
                s = match;
            } else {
                return false;
            }
        }
        while (p < pattern.length() && pattern.charAt(p) == '@') {
            p++;
        }
        return p == pattern.length();
    }
    public static void main(String[] args) {
        String a1 = "tt", p1 = "@";
        System.out.println(matchPattern(a1, p1)); // true

        String a2 = "ta", p2 = "t";
        System.out.println(matchPattern(a2, p2)); // false

        String a3 = "ta", p3 = "t#";
        System.out.println(matchPattern(a3, p3)); // true
    }
}
