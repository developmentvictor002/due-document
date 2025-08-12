package com.devictor.duedocument.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidDueDateException extends DueDocumentException {

    private final String detail;

    public InvalidDueDateException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setTitle("Invalid due date");
        pd.setDetail(detail);
        return pd;
    }
}
