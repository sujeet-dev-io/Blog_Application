package com.blog.files.exception;

import com.blog.files.enums.Status;
import com.blog.files.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleNotFoundException(NotFoundException ex) {
        BaseResponse<Object> response = BaseResponse.builder()
                .status(Status.FAILURE)
                .error("Resource Not Found")
                .errorMsg(ex.defaultMessage)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class) // Corrected to handle BadRequestException
    public ResponseEntity<BaseResponse<Object>> handleBadRequestException(BadRequestException ex) {
        BaseResponse<Object> response = BaseResponse.builder()
                .status(Status.FAILURE)
                .error("Bad Request")
                .errorMsg(ex.defaultMessage)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
