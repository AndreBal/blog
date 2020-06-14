package by.leverx.rest;

import by.leverx.dto.UserDTO;
import by.leverx.entity.User;
import by.leverx.rest.exception.UserAlreadyExistException;
import by.leverx.service.EmailSender;
import by.leverx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/user/")
public class UserRestController {


    private final UserService userService;
    private final EmailSender emailSender;

    @Autowired
    public UserRestController( UserService userService, EmailSender emailSender) {
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @PostMapping("registration")
    public ResponseEntity registerUserAccount(@RequestBody @Valid UserDTO userDto, HttpServletRequest request) {
        try {
            if (userService.findByEmail(userDto.getEmail()) != null) {
                throw new UserAlreadyExistException("There is an account with that email address: "
                        + userDto.getEmail());
            }

            User user = userDto.toUser();


            String appUrl = request.getContextPath();
            emailSender.confirmRegistration(user, request.getLocale(), appUrl);


            Map<Object, Object> response = new HashMap<>();
            response.put("email", user.getEmail());
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistException uaeEx) {
            throw new BadCredentialsException("An account for that username/email already exists.");
        }
    }
}
