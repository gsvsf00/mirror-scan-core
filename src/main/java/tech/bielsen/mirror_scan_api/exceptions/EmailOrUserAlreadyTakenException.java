package tech.bielsen.mirror_scan_api.exceptions;

import java.io.Serial;

public class EmailOrUserAlreadyTakenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailOrUserAlreadyTakenException() {
        super("The email or username is already taken");
    }
}
