package org.rdutta.student.exception;

public class HostelWorkflowException extends RuntimeException {

    public HostelWorkflowException(String message) {
        super(message);
    }

    public HostelWorkflowException(String message, Throwable cause) {
        super(message, cause);
    }
}
