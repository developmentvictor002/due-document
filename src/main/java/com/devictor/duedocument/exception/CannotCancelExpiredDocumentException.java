package com.devictor.duedocument.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CannotCancelExpiredDocumentException extends DueDocumentException {
    private final String detail;

    public CannotCancelExpiredDocumentException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pd.setTitle("Cannot cancel expired document");
        pd.setDetail(detail);
        return pd;
    }
}
