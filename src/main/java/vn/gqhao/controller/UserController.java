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
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.dto.response.ResponseData;
import vn.gqhao.dto.response.ResponseError;
import vn.gqhao.dto.response.UserDetailResponse;
import vn.gqhao.service.UserService;
import vn.gqhao.util.UserStatus;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
@Tag(name = "User Controller")
@Service
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get user", description = "API get a user by id")
    @GetMapping("/{userId}")
    public ResponseData<UserDetailResponse> getUserById(@PathVariable("userId") long userId) {
        UserDetailResponse user = userService.getUser(userId);
        return new ResponseData<>(HttpStatus.OK.value(), "Get user by id", user);
    }

    @Operation(summary = "Get user list per page", description = "Return user by pageNo and pageSize")
    @GetMapping(value = "/" /*, headers = "apiKey=v1.0" */)
    public ResponseData<?> getListUser(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @Min(10) @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(required = false) String sortBy) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get list user successfully !", userService.getAllUsersWithSortBy(pageNo, pageSize, sortBy));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Get list user fail !");
        }
    }

    @Operation(summary = "Add user", description = "API add new user")
    @PostMapping("/")
    public ResponseData<Long> addUser(@Valid @RequestBody UserRequestDTO userDTO) {
        try {
            long id = this.userService.saveUser(userDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Created user !", id);
        } catch (Exception e) {
            log.error("Error Message={}", e.getMessage());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Save user fail !");
        }
    }

    @Operation(summary = "Update user", description = "API update user")
    @PutMapping("/{userId}") //update full
    public ResponseData<Long> updateUser(@PathVariable("userId") long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        try {
            this.userService.updateUser(id, userUpdateDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully !");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Update fail !");
        }
    }

    @Operation(summary = "Change status of user", description = "API change status of user")
    @PatchMapping("/{userId}") //update 1 phan
    public ResponseData<Long> changeStatus(@PathVariable("userId") long id, @RequestParam UserStatus status) {
        try {
            this.userService.changeStatus(id, status);
            return new ResponseData<>(HttpStatus.OK.value(), "Changed status successfully !");
        } catch (Exception exception) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Changed status fail !");
        }
    }

    @Operation(summary = "Delete user", description = "API delete user by username")
    @DeleteMapping("/{userId}")
    public ResponseData<Long> deleteUser(@PathVariable("userId") long userId) {
        try {
            this.userService.deleteUser(userId);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Deleted user successfully !", userId);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.NO_CONTENT.value(), "Deleted user fail !");
        }
    }
}
