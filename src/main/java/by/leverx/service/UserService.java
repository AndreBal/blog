package by.leverx.service;

import by.leverx.entity.User;
import by.leverx.service.registration.VerificationToken;

import java.util.List;

public interface UserService {

    User codePassword(User user);

    List<User> getAll();

    User findByEmail(String email);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    void saveRegisteredUser(User user);

    User findById(Long id);

    //void delete(Long id);
}
