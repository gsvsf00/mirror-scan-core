package tech.bielsen.mirror_scan_api.exceptions;

import java.io.Serial;

//this is a custom exception that is thrown when a user tries to register with an email or username that is already taken
public class EmailOrUserAlreadyTakenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailOrUserAlreadyTakenException() {
        super("The email or username is already taken");
    }
}
