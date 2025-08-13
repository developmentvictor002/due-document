package com.devictor.duedocument.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DocumentNotFoundException extends DueDocumentException {
    private final String detail;
    public DocumentNotFoundException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Document not found");
        pd.setDetail(detail);
        return pd;
    }
}
