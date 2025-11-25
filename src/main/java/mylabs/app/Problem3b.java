//Zachary Gallant 101272210
package mylabs.app;

import java.util.*;


public class Problem3b {

    private static final Set<String> COMMON_WEAK_PASSWORDS = new HashSet<>();

    static {
        //Initialize set of commonly used weak passwords to blacklist
        COMMON_WEAK_PASSWORDS.add("123456");
        COMMON_WEAK_PASSWORDS.add("password");
        COMMON_WEAK_PASSWORDS.add("123456789");
        COMMON_WEAK_PASSWORDS.add("qwerty123");
        COMMON_WEAK_PASSWORDS.add("qwerty1");
        COMMON_WEAK_PASSWORDS.add("111111");
        COMMON_WEAK_PASSWORDS.add("123123");
        COMMON_WEAK_PASSWORDS.add("abc123");
        COMMON_WEAK_PASSWORDS.add("password1");
        COMMON_WEAK_PASSWORDS.add("iloveyou");
        COMMON_WEAK_PASSWORDS.add("welcome");
        COMMON_WEAK_PASSWORDS.add("admin");
    }


    public static boolean isValidPassword(String password, String accountName) {
        if (password.length() < 8 || password.length() > 12) {
            System.out.println("Length must be 8-12 characters");
            return false;
        }

        if (password.equalsIgnoreCase(accountName)) {
            System.out.println("Password cannot be the same as username");
            return false;
        }

        if (COMMON_WEAK_PASSWORDS.contains(password.toLowerCase())) {
            System.out.println("This password is too common. Choose something stronger");
            return false;
        }
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        String allowedSpecialChars = "!@#$%*&";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
            if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (allowedSpecialChars.contains(String.valueOf(c))) {
                hasSpecialChar = true;
            }
        }

        if (!hasUppercase) {
            System.out.println("Missing uppercase letter");
        }
        if (!hasLowercase) {
            System.out.println("Missing lowercase letter");
        }
        if (!hasDigit) {
            System.out.println("Missing numeric digit");
        }
        if (!hasSpecialChar) {
            System.out.println("Missing special character");
        }

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }
}
