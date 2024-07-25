package vn.gqhao.dto.mapper;

import org.springframework.stereotype.Component;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.model.User;

@Component
public class UserMapper {
    public static UserRequestDTO toUserRequestDTO(User user) {
        return new UserRequestDTO(user.getUserName(),
                user.getPassWord(),
                user.getFullName(),
                user.getPhone(),
                user.getDateOfBirth());
    }

    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUserName(userRequestDTO.getUserName());
        user.setPassWord(userRequestDTO.getPassWord());
        user.setFullName(userRequestDTO.getFullName());
        user.setPhone(userRequestDTO.getPhone());
        user.setDateOfBirth(userRequestDTO.getDateOfBirth());
        return user;
    }

}
