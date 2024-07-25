package vn.gqhao.service;

import org.springframework.stereotype.Service;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.model.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUser();

    User getUserByUsername(String userName);

    User createUser(UserRequestDTO userRequestDTO);

    void deleteUserByUserName(String userName);

    User updateUser(String id, UserUpdateDTO userUpdateDTO);
}
