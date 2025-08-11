package com.devictor.duedocument.exception;

import org.springframework.http.ProblemDetail;

public abstract class DueDocumentException extends RuntimeException {

    public DueDocumentException(String message) {
        super(message);
    }

    public DueDocumentException(Throwable cause) {
        super(cause);
    }

    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(500);
        pd.setTitle("DueDocument Internal Server Error");
        pd.setDetail("Contact DueDocument support");
        return pd;
    }
}
