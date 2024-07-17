package vn.gqhao.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;
import vn.gqhao.dto.request.UserRequestDTO;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{userId}")
    public UserRequestDTO getUserById(@Min (1) @PathVariable("userId") int id){
        System.out.println("user id: " + id);
        return new UserRequestDTO("Giang", "Hao", "0939434", "hg@gmail.com");
    }

    @GetMapping(value = "/list" /*, headers = "apiKey=v1.0" */)
    public List<UserRequestDTO> getListUser(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize){
        System.out.println("Get list success !");
        return List.of(new UserRequestDTO("Giang", "Hao", "0939434", "hg@gmail.com")
                        ,new UserRequestDTO("Giang", "Hao", "0939434", "hg@gmail.com"));
    }
    @PostMapping("/")
    public String addUser(@Valid @RequestBody UserRequestDTO userDTO){
        return "user added !";
    }

    @PutMapping("/{userId}")
    public String updateUser(@PathVariable("userId") int id, @RequestBody UserRequestDTO userDTO){
        System.out.println("Update user id: " + id);
        return "updated !";
    }

    @PatchMapping("/{userId}")
    public String changeStatus(@PathVariable("userId") int id, @RequestParam(required = false) boolean status){
        System.out.println("User id: " + id + " is changed status " + status);
        return "status changed !";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") int id){
        System.out.println("delete user: " + id);
        return "deleted user !";
    }
}
