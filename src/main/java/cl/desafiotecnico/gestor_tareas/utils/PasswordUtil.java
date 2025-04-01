package cl.desafiotecnico.gestor_tareas.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Generates a BCrypt hash for the provided raw password.
     *
     * @param rawPassword the plain text password to hash.
     * @return the BCrypt hashed password.
     */
    public static String encrypt(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verifies if the raw password matches the BCrypt hashed password.
     *
     * @param rawPassword the plain text password.
     * @param hashedPassword the BCrypt hashed password.
     * @return true if the passwords match, false otherwise.
     */
    public static boolean matches(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
