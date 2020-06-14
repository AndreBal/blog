package by.leverx.service.registration;

import by.leverx.entity.User;
import by.leverx.service.UserService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    private String token;

    private User user;

    private Date expiryDate;

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
