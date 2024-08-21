package vn.gqhao.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.model.User;
import vn.gqhao.service.UserService;
import vn.gqhao.service.impl.UserServiceImpl;
import vn.gqhao.util.UserType;

@Component
public class UserMapper {
//    public static UserRequestDTO toUserRequestDTO(User user) {
//        return UserRequestDTO.builder()
//                .firstName(user.getFirstName())
//                .lastName(user.getLastName())
//                .
//                .build();
//    }

    public static User toUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setUserName(userRequestDTO.getUsername());
        user.setPassWord(userRequestDTO.getPassword());
        user.setPhone(userRequestDTO.getPhone());
        user.setDateOfBirth(userRequestDTO.getDateOfBirth());
        user.setGender(userRequestDTO.getGender());
        user.setStatus(userRequestDTO.getStatus());
        user.setType(UserType.valueOf(userRequestDTO.getUserType()));
        return user;
    }

}
