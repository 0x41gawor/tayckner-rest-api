package pl.gawor.tayckner.taycknerbackend.web.response;

/**
 * Enum for response codes.
 *
 * <p>
 *     Rules for defining code names:<br>
 *     - Each code has a number at the end. <br>
 *     - Only positive (indicating no errors) code can have number 0.<br>
 *     - Codes associated with register start with `R`<br>
 *     - Codes associated with login start with 'L'<br>
 *     - Code associated with model has 3 chars distinguisher.<br>
 *     -- First 2 chars are model id<br>
 *     ---- Ac - Activity<br>
 *     ---- Ca - Category<br>
 *     ---- Ha - Habit<br>
 *     ---- He - Habit Event<br>
 *     ---- Sc - Schedule<br>
 *     ---- Xx - if code appears in more than 1 model<br>
 *     -- Last char indicates the CRUD method<br>
 *     ---- L - List<br>
 *     ---- C - Create<br>
 *     ---- R - Read<br>
 *     ---- U - Update<br>
 *     ---- D - Delete<br>
 *     ---- X - if code appears in more than 1 method<br>
 *
 * </p>
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
    // all models - all methods
    XxX0("XxX0", "OK"),
    // all models- list
    XxL1("XxL1", "List is empty"),
    // all models- read, update, delete
    XxX2("XxX2", "Object with given id not found for user"),
    // activity  - create, update
    AcX1("AcX1", "Category does not belong to user"),
    // habit-event -  create, update
    HeX1("HeX1", "Habit does not belong to user"),
    // category, habit - create, update
    XxX3("XxX3", "Invalid color"),
    // habit - create, update
    HaX1("HaX1", "User already has habit with the same name"),
    // activity, schedule - create, update
    XxX4("XxX4", "Start time is before end time"),
    // category - create, update
    CaX1("CaX1", "User already has a category with the same name"),
    // activity, schedule - create, update
    XxX5("XxX5", "Duration has to be greater than zero");


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
