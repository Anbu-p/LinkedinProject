package com.meta.validation;

/**
 * <p>
 * Validates the user input.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
public class Validation {

    private static Validation validation;

    private Validation(){};

    /**
     * <p>
     * Gets the {@link Validation} instance.
     * </p>
     *
     * @return validation instance.
     */
    public static Validation getValidationInstance() {
        if(validation == null) {
            validation = new Validation();
        }

        return validation;
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param mobileNumber The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("^[6-9][0-9]{9}");
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param email The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidEmail(final String email) {
        return email.matches("^(?=.*[a-z])[a-z][a-z0-9]{3,50}+(?:\\.[a-z0-9]+)*@(?:[a-z]{2,50}+\\.)+[a-z]{2,3}$");
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param password The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidPassword(final String password) {
        return password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[*&^%$#@!()+=])(?=\\S+$).{8,50}$");
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param name The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidName(final String name) {
        return name.matches("^[a-zA-Z\\s]{2,50}+$");
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param skill The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidSkill(final String skill) {
        return skill.matches("^[a-zA-Z0-9\\s!@#$%^&*()_+=-[{]}:]{2,50}+$");
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param education The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidEducation(final String education) {
        return education.matches("^[a-zA-Z_,.\\s]{2,100}+$");
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param experience The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidExperience(final String experience) {
        return experience.matches("^(100|[1-9]?[0-9](\\.[0-9]*)?)$");
    }

    /**
     * <p>
     * Checks whether the given mobile number is valid or not.
     * </p>
     *
     * @param id The mobile number to be validated.
     * @return True if the mobile number is valid, false otherwise.
     */
    public boolean isValidId(final String id) {
        return id.matches( "^\\d+$");
    }
}
