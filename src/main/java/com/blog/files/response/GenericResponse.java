//package com.blog.files.response;
//
//import com.blog.files.enums.Status;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@JsonInclude(value = Include.NON_EMPTY)
//@Builder
//public class GenericResponse {
//
//    @Builder.Default
//    Status status = Status.SUCCESS;
//
//    String error;
//    String errorMsg;
//    String token;
//    String successMsg;
//
//}