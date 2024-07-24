package vn.gqhao.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import vn.gqhao.exception.ResourceNotFoundException;
import vn.gqhao.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public int getUser(int id) {
        if (id != 9) {
            throw new ResourceNotFoundException("User not found !");
        }
        return 1;
    }
}
