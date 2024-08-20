package vn.gqhao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.gqhao.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    boolean existsUserByUserName(String userName);

    boolean existsUserById(long id);

    User findUserByUserName(String userName);

    User findUserById(long id);

    void removeUserByUserName(String userName);
}
