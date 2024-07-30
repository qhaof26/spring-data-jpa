package vn.gqhao.dto.request.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable {
    @NotBlank(message = "Username must be not blank !")
    private String userName;
    @NotBlank(message = "Password must be no blank !")
    private String passWord;
    @NotBlank(message = "Password must be no blank !")
    private String fullName;
    //@Pattern(regexp = "^\\\\d{10}$", message = "Phone invalid format !")
    private String phone;
    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;
}
