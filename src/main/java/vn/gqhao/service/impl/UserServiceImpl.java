package vn.gqhao.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.gqhao.dto.request.address.AddressRequestDTO;
import vn.gqhao.dto.request.user.UserRequestDTO;
import vn.gqhao.dto.request.user.UserUpdateDTO;
import vn.gqhao.dto.response.PageResponse;
import vn.gqhao.dto.response.UserDetailResponse;
import vn.gqhao.exception.ResourceNotFoundException;
import vn.gqhao.model.Address;
import vn.gqhao.model.User;
import vn.gqhao.repository.UserRepository;
import vn.gqhao.service.UserService;
import vn.gqhao.util.UserStatus;
import vn.gqhao.util.UserType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found !"));
    }

    @Transactional
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
                .status(userRequestDTO.getStatus())
                .type(UserType.valueOf(userRequestDTO.getUserType().toUpperCase()))
                //.addresses(convertToAddress(userRequestDTO.getAddresses()))
                .build();
        userRequestDTO.getAddresses().forEach(a ->
                user.saveAddress(Address.builder()
                        .apartmentNumber(a.getApartmentNumber())
                        .floor(a.getFloor())
                        .building(a.getBuilding())
                        .streetNumber(a.getStreetNumber())
                        .street(a.getStreet())
                        .city(a.getCity())
                        .country(a.getCountry())
                        .addressType(a.getAddressType())
                        .build()));
        userRepository.save(user);
        log.info("User has saved !");
        return user.getId();
    }

    @Transactional
    @Override
    public void updateUser(long userId, UserUpdateDTO userUpdateDTO) {
        User user = getUserById(userId);
        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setPassWord(userUpdateDTO.getPassword());
        user.setPhone(userUpdateDTO.getPhone());
        user.setDateOfBirth(userUpdateDTO.getDateOfBirth());
        user.setGender(userUpdateDTO.getGender());
        user.setStatus(userUpdateDTO.getStatus());
        user.setType(UserType.valueOf(userUpdateDTO.getType()));
        user.setAddresses(convertToAddress(userUpdateDTO.getAddresses()));
        userRepository.save(user);
        log.info("User updated successfully");
    }

    @Transactional
    @Override
    public void changeStatus(long userId, UserStatus status) {
        User user = getUserById(userId);
        user.setStatus(status);
        userRepository.save(user);
        log.info("Status changed !");
    }

    @Transactional
    @Override
    public void deleteUser(long userId) {
        User user = getUserById(userId);
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserDetailResponse getUser(long userId) {
        User user = getUserById(userId);
        return UserDetailResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy) {
        int pageTemp = 0;
        if (pageNo > 0) {
            pageTemp = pageNo - 1;
        }

        List<Sort.Order> sorts = new ArrayList<>();
        // Neu co gia tri
        if (StringUtils.hasLength(sortBy)) {
            // field:asc|desc
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sortBy);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                } else if (matcher.group(3).equalsIgnoreCase("desc")) {
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }

        Pageable pageable = PageRequest.of(pageTemp, pageSize, Sort.by(sorts));
        Page<User> users = this.userRepository.findAll(pageable);
        List<UserDetailResponse> userDetailResponeseList = users.stream().map(user -> UserDetailResponse.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .phone(user.getPhone())
                        .build())
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(users.getTotalPages())
                .items(userDetailResponeseList)
                .build();
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
