package vn.gqhao.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.gqhao.dto.mapper.UserMapper;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.dto.response.ResponseData;
import vn.gqhao.model.User;
import vn.gqhao.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    public ResponseData<UserRequestDTO> getUserById(@PathVariable("userName") String userName) {
        User user = userService.getUserByUsername(userName);
        return new ResponseData<>(HttpStatus.OK.value(), "Get user by username", UserMapper.toUserRequestDTO(user));
    }

    @GetMapping(value = "/" /*, headers = "apiKey=v1.0" */)
    public ResponseData<List<UserRequestDTO>> getListUser(
            @RequestParam(defaultValue = "0") int pageNo,
            @Min(5) @RequestParam(defaultValue = "10") int pageSize) {
        List<UserRequestDTO> userDtoList = new ArrayList<>();
        userService.getAllUser().forEach(user -> userDtoList.add(UserMapper.toUserRequestDTO(user)));
        return new ResponseData<>(HttpStatus.OK.value(), "Get List User", userDtoList);
    }

    @PostMapping("/")
    public ResponseData<UserRequestDTO> addUser(@Valid @RequestBody UserRequestDTO userDTO) {
        this.userService.createUser(userDTO);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Created user !", userDTO);
    }

    @PutMapping("/{userId}") //update full
    public ResponseData<?> updateUser(@PathVariable("userId") String id, @Valid @RequestBody UserUpdateDTO userDTO) {
        User user = this.userService.updateUser(id, userDTO);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully !", UserMapper.toUserRequestDTO(user));
    }

    @PatchMapping("/{userId}") //update 1 phan
    public ResponseData<?> changeStatus(@PathVariable("userId") int id, @RequestParam(required = false) boolean status) {
        System.out.println("User id: " + id + " is changed status " + status);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User changed status !");
    }

    @DeleteMapping("/{userName}")
    public ResponseData<?> deleteUser(@PathVariable("userName") String userName) {
        this.userService.deleteUserByUserName(userName);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted !");
    }
}
