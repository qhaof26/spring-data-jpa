package vn.gqhao.dto.request.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import vn.gqhao.dto.request.address.AddressRequestDTO;
import vn.gqhao.util.Gender;
import vn.gqhao.util.UserStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
public class UserUpdateDTO implements Serializable {

    @NotBlank(message = "firstName must be not blank") // Khong cho phep gia tri blank
    private String firstName;

    @NotNull(message = "lastName must be not null") // Khong cho phep gia tri null
    private String lastName;

    @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    private String phone;

    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^male|female|other$", message = "gender must be one in {male, female, other}")
    //@GenderSubset(anyOf = {MALE, FEMALE, OTHER})
    private Gender gender;

    @NotNull(message = "password must be not null")
    private String password;

    @NotNull(message = "type must be not null")
    //@EnumValue(name = "type", enumClass = UserType.class)
    private String type;

    @NotEmpty(message = "addresses can not empty")
    private Set<AddressRequestDTO> addresses;

    //@EnumPattern(name = "status", regexp = "ACTIVE|INACTIVE|NONE")
    private UserStatus status;
}
