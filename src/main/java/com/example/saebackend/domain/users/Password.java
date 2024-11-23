package com.example.saebackend.domain.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

/**
 * Utility class for password management.
 * <p>
 * Provides methods for generating, encrypting, and verifying passwords.
 * </p>
 */
public class Password {

    private static final int PASSWORD_LENGTH = 12;
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    /**
     * Generates a random password containing uppercase letters, lowercase letters, digits, and special characters.
     * The password length is fixed at 12 characters.
     *
     * @return a randomly generated password as a String.
     */
    public static String generatePassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";

        String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        // Ensure password contains at least one character from each category
        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        // Fill the remaining characters with random ones from the full character set
        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return shuffleString(password.toString());
    }

    /**
     * Shuffles the characters of a given string to randomize its order.
     *
     * @param input the string to shuffle.
     * @return the shuffled string.
     */
    private static String shuffleString(String input) {
        SecureRandom random = new SecureRandom();
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return new String(array);
    }

    /**
     * Encrypts a plain password using the BCrypt password encoder.
     *
     * @param plainPassword the plain password to encrypt.
     * @return the encrypted password.
     */
    public static String encryptPassword(String plainPassword) {
        return PASSWORD_ENCODER.encode(plainPassword);
    }

    /**
     * Verifies whether the given plain password matches the encrypted password.
     *
     * @param plainPasswordToCheck the plain password to check.
     * @param encryptedPassword the encrypted password to compare against.
     * @return {@code true} if the plain password matches the encrypted password, {@code false} otherwise.
     */
    public static boolean matches(String plainPasswordToCheck, String encryptedPassword) {
        return PASSWORD_ENCODER.matches(plainPasswordToCheck, encryptedPassword);
    }
}
