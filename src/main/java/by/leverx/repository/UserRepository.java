package by.leverx.repository;

import by.leverx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String email);
    }


