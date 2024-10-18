package com.blog.files.service.Impl;

import com.blog.files.entites.User;
import com.blog.files.exception.NotFoundException;
import com.blog.files.payloads.UserDto;
import com.blog.files.repository.UserRepository;
import com.blog.files.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setCreated(LocalDateTime.now());
        User saveUser = userRepository.save(user);
        log.info("user details save successfully... {}", userDto);
        return modelMapper.map(saveUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user id not found"));
        UserDto userDto = modelMapper.map(user, UserDto.class);
        if (Boolean.TRUE.equals(user.getDeleted())) {
            throw new NotFoundException("This category Id is already soft deleted");
        }
        log.info("user details retrieved successfully... {}", userDto);
        return modelMapper.map(userDto, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> listOfUsers = userRepository.findAll();
        if (!CollectionUtils.isEmpty(listOfUsers)) {
            listOfUsers.forEach(user -> {
                UserDto userDto = modelMapper.map(user, UserDto.class);
                userDtoList.add(userDto);
            });
        }
        log.info("All users fetched from the database: {}", userDtoList);

        return userDtoList;
    }


    @Override
    public Page<UserDto> getUserByPaginationAndSorting(int offset, int pageSize, String field, String order) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize)
                .withSort(Sort.by(Sort.Direction.valueOf(order), field));
        Page<User> users = userRepository.findAll(pageRequest);
        List<UserDto> userDtoList = users.stream().map(e -> modelMapper.map(e, UserDto.class)).toList();

//        for (User user : users) {
//            UserDto userDto = modelMapper.map(user, UserDto.class);
//            userDtos.add(userDto);
//        }
        log.info("Fetched {} users from page {} with page size {} sorted by {} in {} order",
                users.getTotalElements(), offset, pageSize, field, order);
        return new PageImpl<>(userDtoList, pageRequest, users.getTotalElements());
    }

    @Override
    public UserDto updateUserById(String userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user id not found"));
        userDto.setUserId(userId);
        modelMapper.map(userDto, user);
        User updateUser = userRepository.save(user);
        log.info("user details update successfully... {}", userDto);
        return modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public String deleteUserById(String userId) {
        User user = userRepository.findByUserIdAndDeletedFalse(userId).orElseThrow(() -> new NotFoundException("user id not found"));
        user.setDeleted(true);
        userRepository.save(user);
        log.info("user details delete successfully... {}", user);
        return "user details deleted successfully...";
    }
    @Override
    public String HardDeleteUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User ID not found"));
        userRepository.delete(user);
        log.info("User details hard deleted successfully... {}", user);
        return "User details hard deleted successfully...";
    }

}
