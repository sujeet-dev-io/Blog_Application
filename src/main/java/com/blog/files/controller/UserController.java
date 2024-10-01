package com.blog.files.controller;

import com.blog.files.constant.Message;
import com.blog.files.payloads.UserDto;
import com.blog.files.response.BaseResponse;
import com.blog.files.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "User Controller", description = Message.USER_CONTROLLER) // Replaces @Api
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Create a new user", description = Message.ADD_USER) // Replaces @ApiOperation
    public ResponseEntity<BaseResponse<Object>> create(@Valid @RequestBody UserDto userDto) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("User Added")
                .data(userService.saveUser(userDto))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    @Operation(summary = "Get user by ID", description = Message.GET_USER) // Replaces @ApiOperation
    public ResponseEntity<BaseResponse<Object>> getUserById(@PathVariable String userId) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Student Fetched")
                .data(userService.getUserById(userId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getPagination")
    @Operation(summary = "Get all categories", description = "Endpoint to fetch all categories")
    public ResponseEntity<BaseResponse<Object>> getCategoriesByPaginationAndSorting(@RequestParam int offset, @RequestParam int pageSize, @RequestParam String field, @RequestParam String order) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("All Categories Fetched")
                .data(userService.getUserByPaginationAndSorting(offset, pageSize, field, order))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all users", description = Message.GET_ALL_USER) // Replaces @ApiOperation
    public ResponseEntity<BaseResponse<Object>> getAllUser() throws JsonProcessingException {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Student Fetched")
                .data(userService.getAllUser())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    @Operation(summary = "Update user", description = Message.UPDATE_USER) // Replaces @ApiOperation
    public ResponseEntity<BaseResponse<Object>> updateUserById(@PathVariable String userId, @RequestBody UserDto userDto) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Student Fetched")
                .data(userService.updateUserById(userId, userDto))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(summary = "Delete user by ID", description = Message.DELETE_USER) // Replaces @ApiOperation
    public ResponseEntity<BaseResponse<Object>> deleteUserById(@PathVariable String userId) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Student Deleted")
                .data(userService.deleteUserById(userId)).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/hard-delete/{userId}")
    @Operation(summary = "Hard delete category by ID", description = "Endpoint to hard delete a category by ID")
    public ResponseEntity<BaseResponse<Object>> hardDeleteCategoryById(@PathVariable String userId) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Category Hard Deleted")
                .data(userService.HardDeleteUserById(userId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


