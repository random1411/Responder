package com.rescuereach.responder.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        if (TextUtils.isEmpty(password) || password.length() < 8) {
            return false;
        }
        // Check for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // Check for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        // Check for at least one number
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        // Check for at least one special character
        return password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }
}