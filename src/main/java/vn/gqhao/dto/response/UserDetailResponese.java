package vn.gqhao.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserDetailResponese implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
}
