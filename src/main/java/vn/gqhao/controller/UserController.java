package vn.gqhao.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import vn.gqhao.dto.mapper.UserMapper;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.dto.response.ResponseData;
import vn.gqhao.dto.response.ResponseError;
import vn.gqhao.model.User;
import vn.gqhao.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
@Tag(name = "User Controller")
@Service
public class UserController {
    private final UserService userService;

//    @Operation(summary = "Get user", description = "API get a user by username")
//    @GetMapping("/{userName}")
//    public ResponseData<UserRequestDTO> getUserById(@PathVariable("userName") String userName) {
//        User user = userService.getUserByUsername(userName);
//        return new ResponseData<>(HttpStatus.OK.value(), "Get user by username", UserMapper.toUserRequestDTO(user));
//    }

//    @Operation(summary = "Get user list per page", description = "Return user by pageNo and pageSize")
//    @GetMapping(value = "/" /*, headers = "apiKey=v1.0" */)
//    public ResponseData<List<UserRequestDTO>> getListUser(
//            @RequestParam(defaultValue = "0") int pageNo,
//            @Min(5) @RequestParam(defaultValue = "10") int pageSize) {
//        List<UserRequestDTO> userDtoList = new ArrayList<>();
//        //userService.getAllUser().forEach(user -> userDtoList.add(UserMapper.toUserRequestDTO(user)));
//        return new ResponseData<>(HttpStatus.OK.value(), "Get List User", userDtoList);
//    }

//    @Operation(summary = "Add user", description = "API add new user")
//    @PostMapping("/")
//    public ResponseData<Long> addUser(@Valid @RequestBody UserRequestDTO userDTO) {
//        try {
//            long id = this.userService.saveUser(userDTO);
//            return new ResponseData<>(HttpStatus.CREATED.value(), "Created user !", id);
//        } catch (Exception e) {
//            log.error("Error Message={}", e.getMessage());
//            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Save user fail !");
//        }
//
//    }

//    @Operation(summary = "Update user", description = "API update user")
//    @PutMapping("/{userId}") //update full
//    public ResponseData<?> updateUser(@PathVariable("userId") String id, @Valid @RequestBody UserUpdateDTO userDTO) {
//        //User user = this.userService.updateUser(id, userDTO);
//        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully !", UserMapper.toUserRequestDTO(user));
//    }

//    @Operation(summary = "Change status of user", description = "API change status of user")
//    @PatchMapping("/{userId}") //update 1 phan
//    public ResponseData<?> changeStatus(@PathVariable("userId") int id, @RequestParam(required = false) boolean status) {
//        System.out.println("User id: " + id + " is changed status " + status);
//        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User changed status !");
//    }

//    @Operation(summary = "Delete user", description = "API delete user by username")
//    @DeleteMapping("/{userName}")
//    public ResponseData<?> deleteUser(@PathVariable("userName") String userName) {
//        this.userService.deleteUserByUserName(userName);
//        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted !");
//    }
}
