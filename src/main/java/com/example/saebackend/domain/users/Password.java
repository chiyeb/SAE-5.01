package com.example.saebackend.domain.users;

import java.security.SecureRandom;
import java.util.Base64;

//TODO: IMPLEMENT CRYPTED PASSWORD
public class Password {

    private static final int PASSWORD_LENGTH = 12;
    private final String encryptedPassword;
    private final String plainPassword;

    private Password(String encryptedPassword, String plainPassword) {
        this.encryptedPassword = encryptedPassword;
        this.plainPassword = plainPassword;
    }

    public static Password generate() {
        String plainPassword = generatePassword();
        String encryptedPassword = encryptPassword(plainPassword);
        return new Password(encryptedPassword, plainPassword);
    }

    private static String generatePassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";

        String allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters;

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        password.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        password.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 4; i < PASSWORD_LENGTH; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return shuffleString(password.toString());
    }

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

    private static String encryptPassword(String plainPassword) {
        //TODO: Replace this with a real encryption algorithm (?)
        return Base64.getEncoder().encodeToString(plainPassword.getBytes());
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

}
