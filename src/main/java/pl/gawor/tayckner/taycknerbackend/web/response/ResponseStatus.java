package pl.gawor.tayckner.taycknerbackend.web.response;

/**
 * Enum for response codes.
 */
public enum ResponseStatus {

    // register
    R0("R0", "OK, registered successfully!"),
    R1("R1", "Username already taken"),
    R2("R2", "Email already used"),
    R3("R3", "Invalid character in username"),
    R4("R4", "Password too short"),
    R5("R5", "Password does not contain any numbers"),
    R6("R6", "Password does not contain any uppercase letters"),
    R7("R7", "Password does not contain any lowercase letters"),
    R8("R8", "Password does not contain any special characters"),
    R9("R9", "Invalid character in password"),
    R10("R10", "Incorrect email address"),

    //login
    L0("L0", "OK, logged in successfully"),
    L1("L1", "No such username"),
    L2("L2", "Wrong password"),

    // model
    M0("M0", "OK"),
    // common - list
    MCL1("MCL1", "List is empty"),
    // all - common
    MAC1("MCC1", "Object with given id not found"),
    MAC2("MHC2", "Invalid color"),
    // habit - create
    MHC1("MHC1", "User already has habit with the same name"),

    // category create
    MCC1("MHC1", "User already has a category with the same name");

    // the rest of codes will appear here during development
    // the ones above are sample

    private final String code;
    private final String message;

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
