package vn.gqhao.service;

import org.springframework.stereotype.Service;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.dto.response.PageResponse;
import vn.gqhao.dto.response.UserDetailResponse;
import vn.gqhao.util.UserStatus;

@Service
public interface UserService {
    long saveUser(UserRequestDTO userRequestDTO);

    void updateUser(long userId, UserUpdateDTO userUpdateDTO);

    void changeStatus(long userId, UserStatus status);

    void deleteUser(long userId);

    UserDetailResponse getUser(long userId);

    PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy);
}
