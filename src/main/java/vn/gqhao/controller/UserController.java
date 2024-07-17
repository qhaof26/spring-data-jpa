package vn.gqhao.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.gqhao.dto.request.UserRequestDTO;
import vn.gqhao.dto.response.ResponseData;
import vn.gqhao.dto.response.ResponseSuccess;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{userId}")
    public ResponseData<Object> getUserById(@Min (1) @PathVariable("userId") int id){
        System.out.println("user id: " + id);
        return new ResponseData<>(HttpStatus.OK.value(), "get user", new UserRequestDTO("Giang", "Hao", "0939434", "hg@gmail.com"));
    }

    @GetMapping(value = "/list" /*, headers = "apiKey=v1.0" */)
    public ResponseData<List<UserRequestDTO>> getListUser(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize){
        System.out.println("Get list success !");
        return new ResponseData<>(HttpStatus.OK.value(), "Get List User",
                                    List.of(new UserRequestDTO("Giang", "Hao", "0939434", "hg@gmail.com"),
                                        new UserRequestDTO("Giang", "Hao", "0939434", "hg@gmail.com")));
    }
    @PostMapping("/")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO userDTO){
        return new ResponseData<>(HttpStatus.CREATED.value(), "Created user !", 1);
    }

    @PutMapping("/{userId}") //update full
    public ResponseData<?> updateUser(@PathVariable("userId") int id, @RequestBody UserRequestDTO userDTO){
        System.out.println("Update user id: " + id);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User updated successfully !");
    }

    @PatchMapping("/{userId}") //update 1 phan
    public ResponseData<?> changeStatus(@PathVariable("userId") int id, @RequestParam(required = false) boolean status){
        System.out.println("User id: " + id + " is changed status " + status);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "User changed status !");
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable("userId") int id){
        System.out.println("delete user: " + id);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "User deleted !");
    }
}
