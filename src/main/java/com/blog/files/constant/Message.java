package com.blog.files.constant;

public interface Message {
    String TOKEN_GENERATED = "New authorization token has been generated.";
    String INVALID_USERNAME_PASSWORD = "Invalid username or password.";
    String USER_BLOCKED = "This user is blocked.";
    String USERSERVICE = "UserService";
    String GENERATE_NEW_TOKEN = "Generates a new authorization token.";
    String AUTHENTICATE_CONTROLLER = "Authentication controller provider.";
    String USER_CONTROLLER = "User controller provider.";
    String PING = "Ping successful.";
    String ADD_USER = "API to add user details.";
    String GET_ALL_USER = "API to fetch all user details.";
    String GET_USER = "API to fetch user details by ID.";
    String UPDATE_USER = "API to update user details.";
    String DELETE_USER = "API to delete user details by ID.";
    String GET_ALL_USER_PDF = "API to generate a PDF of all user details.";
    String FIRST_NAME_NOT_VALID = "First name is not valid.";
    String LAST_NAME_NOT_VALID = "Last name is not valid.";
    String FIRSTNAME_CANNOT_BE_BLANK = "First name cannot be blank.";
    String LASTNAME_CANNOT_BE_BLANK = "Last name cannot be blank.";
    String EMAIL_ALREADY_EXISTS = "Email already exists.";
    String EMAIL_MUST_BE_PROVIDED = "Email must be provided.";
    String PASSWORD_IS_REQUIRED = "Password is required.";
    String PASSWORD_LENGTH_NOT_CORRECT = "Password length must be at least 6 characters.";
    String SHOULD_BE_VALID_EMAIL = "Email must be valid.";
    String PHONE_NO_NOT_VALID = "Phone number is not valid; it must be 10 digits without a country code (e.g., '+91').";

}
