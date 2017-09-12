public class RabinKarp {

    private static boolean compare(String text, String pattern, int start) {
        int length = pattern.length();
        for (int i = start; i < start+length; i++) {
            if (text.charAt(i) != pattern.charAt(i-start)) {
                return false;
            }
        }
        return true;
    }

    public static boolean match(String needle, String haystack) {
        if (needle == null || haystack == null || needle.length() > haystack.length()) {
            return false;
        }
        int mult = 1, base = 103, needleHash = 0, haystackHash = 0;
        for (int i = 1; i < needle.length(); i++) {
            mult *= base;
        }
        for (int i = 0; i < needle.length(); i++) {
            needleHash = needleHash * base + needle.charAt(i);
            haystackHash = haystackHash * base + haystack.charAt(i);
        }
        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            if (needleHash == haystackHash && compare(haystack, needle, i)) {
                return true;
            }
            haystackHash = base * (haystackHash - mult * haystack.charAt(i)) + haystack.charAt(i + needle.length());
        }
        if (needleHash == haystackHash && compare(haystack, needle, haystack.length() - needle.length())) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        System.out.println(match("abc", "abdcabdcabdcabdcab"));
    }
}