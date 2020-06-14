package by.leverx.jwt;

import by.leverx.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getCreatedAt(),
                createUserAuthority()
        );
    }

    private static List<GrantedAuthority> createUserAuthority() {
        List<GrantedAuthority> userAuthority = new ArrayList<>();
        userAuthority.add(new SimpleGrantedAuthority("user"));
        return userAuthority;
    }

}
