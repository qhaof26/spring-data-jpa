package vn.gqhao.service;

import org.springframework.stereotype.Service;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.dto.response.UserDetailResponese;
import vn.gqhao.model.User;
import vn.gqhao.util.UserStatus;

import java.util.List;

@Service
public interface UserService {
    long saveUser(UserRequestDTO userRequestDTO);

    void updateUser(long userId, UserRequestDTO userRequestDTO);

    void changeStatus(long userId, UserStatus status);

    void deleteUser(long userId);

    UserDetailResponese getUser(long userId);

    List<UserDetailResponese> getAllUser(int pageNo, int pageSize);
}
