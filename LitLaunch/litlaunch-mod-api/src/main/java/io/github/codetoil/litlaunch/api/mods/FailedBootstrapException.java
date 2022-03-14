/*
 * Copyright Codetoil (c) 2019
 */

package io.github.codetoil.litlaunch.api.mods;

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
