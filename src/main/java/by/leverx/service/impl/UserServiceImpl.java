package by.leverx.service.impl;

import by.leverx.entity.User;
import by.leverx.service.registration.VerificationToken;
import by.leverx.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import by.leverx.service.UserService;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private Map<String,VerificationToken> unverifiedUsers = new HashMap<>();

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User codePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void saveRegisteredUser(User user){
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", user);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);

        if (result == null) {
            log.warn("IN findByEmail - no user found by Email: {}", email);
            return null;
        }

        log.info("IN findByEmail - user: {} found by email: {}", result, email);
        return result;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        user = this.codePassword(user);
        VerificationToken verTok = new VerificationToken(user,token);
        unverifiedUsers.put(token,verTok);
        log.info("IN UserServiceImpl in createVerificationToken - user: {} awaits email verification", user.getEmail());
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return unverifiedUsers.remove(VerificationToken);
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }


        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }
}
