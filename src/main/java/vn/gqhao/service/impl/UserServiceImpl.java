package vn.gqhao.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import vn.gqhao.dto.mapper.UserMapper;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.exception.AppException;
import vn.gqhao.exception.ErrorCode;
import vn.gqhao.exception.ResourceNotFoundException;
import vn.gqhao.model.User;
import vn.gqhao.repository.UserRepository;
import vn.gqhao.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        if (userRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No content !");
        }
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String userName) {
        if (!userRepository.existsUserByUserName(userName)) {
            throw new ResourceNotFoundException("User not found !");
        }
        return userRepository.findUserByUserName(userName);
    }

    @Transactional
    @Override
    public User createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsUserByUserName(userRequestDTO.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userRepository.save(UserMapper.toUser(userRequestDTO));
    }

    @Transactional
    @Override
    public void deleteUserByUserName(String userName) {
        if (!userRepository.existsUserByUserName(userName)) {
            throw new ResourceNotFoundException("user not exist !");
        }
        this.userRepository.removeUserByUserName(userName);
    }

    @Transactional
    @Override
    public User updateUser(String id, UserUpdateDTO userUpdateDTO) {
        if (!this.userRepository.existsUserById(id)) {
            throw new ResourceNotFoundException("user not exist !");
        }
        User user = this.userRepository.findUserById(id);
        user.setPassWord(userUpdateDTO.getPassWord());
        user.setFullName(userUpdateDTO.getFullName());
        user.setPhone(userUpdateDTO.getPhone());
        user.setDateOfBirth(userUpdateDTO.getDateOfBirth());
        return user;
    }
}
