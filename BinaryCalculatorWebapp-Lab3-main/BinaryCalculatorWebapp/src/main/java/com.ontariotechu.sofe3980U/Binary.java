package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 */
public class Binary {
    private String number = "0"; // String containing the binary value '0' or '1'

    /**
     * Constructor that initializes a binary number.
     *
     * @param number a String of binary values. It should only contain zeros or ones. 
     *               If invalid input is given, the default value is "0".
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0"; // Default to "0" for null or empty input
            return;
        }

        // Validate the binary string (only '0' or '1' allowed)
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0"; // Default to "0" for invalid input
                return;
            }
        }

        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        // If all digits are '0', ensure number is "0"
        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        // Ensure empty strings are replaced with "0"
        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    /**
     * Returns the binary value.
     *
     * @return the binary value as a string.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Adds two binary numbers.
     *
     * @param num1 First binary number
     * @param num2 Second binary number
     * @return A Binary object representing the sum.
     */
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        String num3 = ""; 

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }
            carry = sum / 2;
            sum = sum % 2;
            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    /**
     * Performs a bitwise OR operation between two binary numbers.
     *
     * @param num1 First binary number
     * @param num2 Second binary number
     * @return A new Binary object representing the OR result.
     */
    public static Binary or(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        String num3 = ""; 

        while (ind1 >= 0 || ind2 >= 0) {
            int bit1 = (ind1 >= 0 && num1.number.charAt(ind1) == '1') ? 1 : 0;
            int bit2 = (ind2 >= 0 && num2.number.charAt(ind2) == '1') ? 1 : 0;

            num3 = ((bit1 | bit2) == 1 ? "1" : "0") + num3;

            if (ind1 >= 0) ind1--;
            if (ind2 >= 0) ind2--;
        }

        return new Binary(num3);
    }

    /**
     * Performs a bitwise AND operation between two binary numbers.
     *
     * @param num1 First binary number
     * @param num2 Second binary number
     * @return A new Binary object representing the AND result.
     */
    public static Binary and(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        String num3 = "";

        while (ind1 >= 0 && ind2 >= 0) {
            int bit1 = (num1.number.charAt(ind1) == '1') ? 1 : 0;
            int bit2 = (num2.number.charAt(ind2) == '1') ? 1 : 0;

            num3 = ((bit1 & bit2) == 1 ? "1" : "0") + num3;

            ind1--;
            ind2--;
        }

        return new Binary(num3.isEmpty() ? "0" : num3);
    }

    /**
     * Multiplies two binary numbers using bitwise addition and shifting.
     *
     * @param num1 First binary number
     * @param num2 Second binary number
     * @return A new Binary object representing the multiplication result.
     */
    public static Binary multiply(Binary num1, Binary num2) {
        String num1Str = num1.number;
        String num2Str = num2.number;
        String result = "0"; 

        for (int i = num1Str.length() - 1; i >= 0; i--) {
            if (num1Str.charAt(i) == '1') {
                String shiftedNum2 = num2Str + "0".repeat(num1Str.length() - 1 - i);
                result = add(new Binary(result), new Binary(shiftedNum2)).getValue();
            }
        }

        return new Binary(result);
    }
}