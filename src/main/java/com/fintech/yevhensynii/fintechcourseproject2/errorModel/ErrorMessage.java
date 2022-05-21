package com.fintech.yevhensynii.fintechcourseproject2.errorModel;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorMessage {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorMessage(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
