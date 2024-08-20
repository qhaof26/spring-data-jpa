package vn.gqhao.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.gqhao.dto.mapper.UserMapper;
import vn.gqhao.dto.request.address.AddressRequestDTO;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.dto.response.UserDetailResponese;
import vn.gqhao.exception.AppException;
import vn.gqhao.exception.ErrorCode;
import vn.gqhao.exception.ResourceNotFoundException;
import vn.gqhao.model.Address;
import vn.gqhao.model.User;
import vn.gqhao.repository.UserRepository;
import vn.gqhao.service.UserService;
import vn.gqhao.util.UserStatus;
import vn.gqhao.util.UserType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public long saveUser(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .dateOfBirth(userRequestDTO.getDateOfBirth())
                .gender(userRequestDTO.getGender())
                .phone(userRequestDTO.getPhone())
                .userName(userRequestDTO.getUsername())
                .passWord(userRequestDTO.getPassword())
                .userStatus(userRequestDTO.getStatus())
                .userType(UserType.valueOf(userRequestDTO.getType().toUpperCase()))
                .addresses(convertToAddress(userRequestDTO.getAddresses()))
                .build();
        userRepository.save(user);
        log.info("User has save !");
        return user.getId();
    }

    @Override
    public void updateUser(long userId, UserRequestDTO userRequestDTO) {

    }

    @Override
    public void changeStatus(long userId, UserStatus status) {

    }

    @Override
    public void deleteUser(long userId) {

    }

    @Override
    public UserDetailResponese getUser(long userId) {
        return null;
    }

    @Override
    public List<UserDetailResponese> getAllUser(int pageNo, int pageSize) {
        return null;
    }

    private Set<Address> convertToAddress(Set<AddressRequestDTO> request) {
        Set<Address> result = new HashSet<>();
        request.forEach(address -> result.add(
                Address.builder()
                        .apartmentNumber(address.getApartmentNumber())
                        .floor(address.getFloor())
                        .building(address.getBuilding())
                        .street(address.getStreet())
                        .streetNumber(address.getStreetNumber())
                        .city(address.getCity())
                        .country(address.getCountry())
                        .addressType(address.getAddressType())
                        .build()
        ));
        return result;
    }
}
