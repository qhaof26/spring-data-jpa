package vn.gqhao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.gqhao.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();

    boolean existsUserByUserName(String userName);

    boolean existsUserById(String id);

    User findUserByUserName(String userName);

    User findUserById(String id);

    void removeUserByUserName(String userName);
}
