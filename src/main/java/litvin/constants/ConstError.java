package litvin.constants;

public class ConstError {
    public static final String SESSION_FACTORY_ERROR = "Session Factory can't be created. ";
    public static final String METHOD_GET_ERROR = "Can't use get method with confidential information";
    public static final String DAO_ERROR = "Database error";
    public static final String EMPTY_EMAIL_OR_PASSWORD = "Email or password are empty";
    public static final String WRONG_EMAIL_OR_PASSWORD = "Wrong email or password";
    public static final String WRONG_EMAIL = "Wrong email";
    public static final String EMPTY_LINE = "All fields must be filled";
    public static final String ROLE_ERROR = "Wrong role";
    public static final String PASSWORDS_ARE_NOT_EQUALS = "Passwords are not equals";
    public static final String PATTERN_ERROR = "Email or login pattern error";
    public static final String OK = "ok";
    public static final String MD5_ERROR = "Wrong encryption algorithm ";
    public static final String EMAIL_ALREADY_TAKEN = "This email is already in use";
    public static final String USER_ALREADY_LOGGED = "You are already logged in from a different session. Please logout first.";
    public static final String NEW_STATUS_ERROR = "'New' status doesn't allow assigned user";
    public static final String ASSIGNED_STATUS_ERROR = "'Assigned' status means choosen assigned user";
    public static final String RESOLUTION_ERROR = "Resolution only allowed for 'Resolved' and 'Closed' statuses";

}
