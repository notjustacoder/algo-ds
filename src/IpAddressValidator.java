import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class IpAddressValidator {

    private final static String IPV4_REGEX = "^(\\d{1,3})(\\.(\\d{1,3}))?(\\.(\\d{1,3}))?(\\.(\\d{1,3})$)?";

    private final static Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

    private final static int IPV4_SEGMENT_MAX_VALUE = 255;

    /**
     * This validates if given String is an IPv4 address as per the dotted-quad format. Returns true if valid.
     * Note - This IPv4 validation doesn't consider segments having octal/hex representations.
     *
     * @param ipAddress the IPv4 address to validate
     * @return true if the argument contains a valid IPv4 address
     */
    public static boolean isValidIpv4Address(String ipAddress) {
        if(ipAddress == null || ipAddress.isEmpty()) {
            return false;
        }
        Matcher matcher = IPV4_PATTERN.matcher(ipAddress);
        String[] groups = null;
        int groupCount = 0;
        if(matcher.matches()) {
            int count = matcher.groupCount();
            groups = new String[count];
            for (int j=0; j<count; j++) {
                if(matcher.group(j+1) != null) {
                    groups[groupCount++] = matcher.group(j+1);
                }
            }
        }
        if(groups == null) {
            return false;
        }
        for (int i=0; i<(groupCount+1)/2; i++) {
            String segment = groups[2*i];
            if(segment == null || segment.isEmpty()) {
                return false;
            }
            int ipSegment = 0;
            try {
                ipSegment = Integer.parseInt(segment);
            } catch (NumberFormatException e) {
                return false;
            }
            if(ipSegment > IPV4_SEGMENT_MAX_VALUE) {
                return false;
            }
            if(segment.length() > 1 && segment.startsWith("0")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValidIpv4Address("192.168.0.1"));
        System.out.println(isValidIpv4Address("192.168.0.100"));
        System.out.println(isValidIpv4Address("192.168.100.255"));
        System.out.println(isValidIpv4Address("192.168.0"));
        System.out.println(isValidIpv4Address("192.168.0."));
        System.out.println(isValidIpv4Address("192.168"));
        System.out.println(isValidIpv4Address("192"));
        System.out.println(isValidIpv4Address("192."));
        System.out.println(isValidIpv4Address("500"));
        System.out.println(isValidIpv4Address("127.0.0.255."));
        System.out.println(isValidIpv4Address("127.0.65"));
        System.out.println(isValidIpv4Address("127.0.0.255.1"));
    }
}
