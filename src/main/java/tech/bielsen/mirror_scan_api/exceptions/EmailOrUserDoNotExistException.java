package tech.bielsen.mirror_scan_api.exceptions;

import java.io.Serial;

public class EmailOrUserDoNotExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailOrUserDoNotExistException() {
        super("The email or username do not Exist");
    }
}