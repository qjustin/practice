package com.coding.training.algorithmic.history.string;

/**
 * 字符串转数字
 * 你能不能想到会有哪些特殊情况或者边界处理，这才是本题的重点.
 */
public class Sample001 {
    public static void main(String[] args) throws Exception {
        /**
         * null
         * "-123"; -123
         * "+123"; 123
         * "-0123" -123
         * "0" 0
         * "000" 0
         * "    -123"; -123
         * "    -a123"; exception
         * "-1a23";    exception
         *
         "    010"
         "    +004500"
         "  -001+2a42"
         "   +0 123"
         "-2147483648"
         "2147483648"
         "   - 321"
         "      -11919730356x"
         "9223372036854775809"
         */
        System.out.println(stringToInteger("0"));
        System.out.println(stringToInteger("      +004500"));
        System.out.println(stringToInteger("-001+2a42"));
        System.out.println(stringToInteger("+0 123"));
        System.out.println(stringToInteger("-2147483648")); // MIN_INTEGER
        System.out.println(stringToInteger("2147483647")); // MAX_INTEGER

//        System.out.println(stringToInteger("-2147483649")); // MIN_INTEGER
//        System.out.println(stringToInteger("2147483648")); // MAX_INTEGER


//        System.out.println(stringToInteger("-2147483648"));
//        System.out.println(stringToInteger("2147483647"));
        System.out.println(stringToInteger("- 321"));
//        System.out.println(stringToInteger("-11919730356x"));
//        System.out.println(stringToInteger("9223372036854775809"));


        System.out.println(stringToInteger("010"));
        System.out.println(stringToInteger("000"));
        System.out.println(stringToInteger("21023 "));
        System.out.println(stringToInteger("-21023"));
        System.out.println(stringToInteger("a21023"));
        System.out.println(stringToInteger("-21a1023"));
        System.out.println(stringToInteger("+21023"));
        System.out.println(stringToInteger("-2a1023"));

        System.out.println(stringToInteger("+413"));
        System.out.println(stringToInteger("0-1"));
        System.out.println(stringToInteger("-234 "));
        System.out.println(stringToInteger("+1"));
    }


    /**
     *
     * @param str
     * @throws RuntimeException
     */
    public static int stringToInteger(String str) throws RuntimeException {
        if (str == null || str.trim().length() == 0) {
            throw new RuntimeException("格式错误");
        }

        str = str.trim();
        char c = str.charAt(0);
        int flag = (c == '-') ? -1 : 1;
        int startIndex = (c == '-' || c == '+') ? 1 : 0;
        long result = 0L;

        for (int i = startIndex; i < str.length(); i++) {
            c = str.charAt(i);

            if (c >= '0' && c <= '9') {
                result = result * 10 + (c - '0');

                if (result * flag > Integer.MAX_VALUE && result * flag < Integer.MIN_VALUE) {
                    throw new RuntimeException("溢出");
                }
            } else {
                break;
            }
        }

        return (int)(result * flag);
    }

















































    /*
    public static int stringToInteger(String str) throws RuntimeException {
        if (str == null || str.trim().length() == 0) {
            throw new RuntimeException("格式错误");
        }

        str = str.trim().toLowerCase();
        char c = str.charAt(0);
        int flag = (c == '-') ? -1 : 1;
        int startIndex = (c == '-' || c == '+') ? 1 : 0;
        long result = 0L;
        boolean isNumber = false;

        for (int i = startIndex; i < str.length(); i++) {
            c = str.charAt(i);

            if (c >= '0' && c <= '9') {
                result = result * 10 + (c - '0');
                isNumber = true;
            } else {
                isNumber = false;
            }

            if (isNumber) {
                if (result * flag > Integer.MAX_VALUE || result * flag < Integer.MIN_VALUE) {
                    throw new RuntimeException("溢出");
                }
            } else {
                break;
            }
        }

        return ((int) result) * flag;
    }
    */
}
