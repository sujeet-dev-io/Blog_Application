package com.blog.files.response;

import com.blog.files.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseResponse<T> {

    String successMsg;
    @Builder.Default
    Status status = Status.SUCCESS;

    T data;
    String error;
    String errorMsg;
    String token;

}

