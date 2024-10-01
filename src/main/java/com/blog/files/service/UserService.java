package com.blog.files.service;

import com.blog.files.payloads.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> getAllUser();

    Page<UserDto> getUserByPaginationAndSorting(int offset, int pageSize, String field, String order);

    UserDto updateUserById(String userId, UserDto userDto);

    String deleteUserById(String userId);

    public String HardDeleteUserById(String userId);

}
