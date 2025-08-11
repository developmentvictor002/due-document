package com.devictor.duedocument.exception;

import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends DueDocumentException {

    private String detail;
    public UserNotFoundException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(404);
        pd.setTitle("User not found");
        pd.setDetail(detail);
        return pd;
    }
}
