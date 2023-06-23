package com.meta.model;

import com.meta.validation.Create;
import com.meta.validation.Delete;
import com.meta.validation.Get;
import com.meta.validation.Update;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * <p>
 * Communicates with the Controller to update the data.
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */

public class LinkedinProfile {
    @NotNull(groups = {Update.class, Delete.class, Get.class, Post.class})
    private Long id;

    @Pattern(regexp = "^[6-9][0-9]{9}", groups = Create.class, message = "Invalid mobile number")
    private String mobileNumber;

    @Pattern(regexp = "^(?=.*[a-z])[a-z][a-z0-9]{3,50}+(?:\\.[a-z0-9]+)*@(?:[a-z]{2,50}+\\.)+[a-z]{2,3}$", groups = {Create.class}, message = "Invalid email, You can use letters, numbers, and symbols")
    private String emailAddress;

    @NotBlank(message = "Please enter your name")
    @Pattern(regexp = "^[a-zA-Z\\s]{2,50}+$", groups = Create.class, message = "Invalid Name, Size should be between 2 to 120")
    private String name;

    @NotBlank(message = "Please enter your password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[*&^%$#@!()+=])(?=\\S+$).{8,50}$", groups = Create.class, message = "Invalid password, password must contain minimum 8 characters, small letters, capital letters and special character")
    private String password;

    @NotBlank(message = "Please enter your skill")
    @Pattern(regexp = "^[a-zA-Z0-9\\s!@#$%^&*()_+=-[{]}:]{2,50}+$", groups = Create.class,  message = "Invalid Skill")
    private String skill;

    @NotBlank(message = "Please enter your experience")
    @Pattern(regexp = "^(100|[1-9]?[0-9](\\.[0-9]*)?)$", groups = Create.class, message = "Invalid experience, Enter your experience in digits")
    private String experience;

    @NotBlank(message = "Please enter your education")
    @Pattern(regexp = "^[a-zA-Z_,.\\s]{2,100}+$", groups = Create.class, message = "Invalid education")
    private String education;

    public void setId(final Long id) {
        this.id = id;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setSkill(final String skill) {
        this.skill = skill;
    }

    public void setExperience(final String experience) {
        this.experience = experience;
    }

    public void setEducation(final String education) {
        this.education = education;
    }

    public Long getId() {
        return id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSkill() {
        return skill;
    }

    public String getExperience() {
        return experience;
    }

    public String getEducation() {
        return education;
    }

    public String toString() {
        if (Objects.isNull(getMobileNumber()) && Objects.nonNull(getEmailAddress())) {
            return String.format("Name : %s%nSkill : %s%nExperience : %s%nEducation : %s%nEmail Address : %s%n", name, skill, experience, education, emailAddress);
        } else if (Objects.isNull(getEmailAddress()) && Objects.nonNull(getMobileNumber())) {
            return String.format("Name : %s%nSkill : %s%nExperience : %s%nEducation : %s%nMobile Number : %s%n", name, skill, experience, education, mobileNumber);
        } else if (Objects.isNull(getMobileNumber()) && Objects.isNull(getEmailAddress()) && Objects.isNull(getExperience())) {
            return String.format("Name : %s%nEducation : %s%n", name, education);
        } else if (Objects.isNull(getMobileNumber()) && Objects.isNull(getEmailAddress())) {
            return String.format("Name : %s%nSkill : %s%nExperience : %s%nEducation : %s%n", name, skill, experience, education);
        } else {
            return String.format("Name : %s%nSkill : %s%nExperience : %s%nEducation : %s%nMobile Number : %s%nEmail Address : %s%n", name, skill, experience, education, mobileNumber, emailAddress);
        }
    }
}
