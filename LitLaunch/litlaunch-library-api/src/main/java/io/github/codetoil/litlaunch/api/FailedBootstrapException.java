/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.api;

public class FailedBootstrapException extends RuntimeException {
    public FailedBootstrapException() {
        super();
    }

    public FailedBootstrapException(String messsge) {
        super(messsge);
    }

    public FailedBootstrapException(String message, Throwable cause) {
        super(message, cause);
    }
}
