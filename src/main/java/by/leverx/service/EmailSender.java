package by.leverx.service;

import by.leverx.entity.User;

import java.util.Locale;

public interface EmailSender {
    public void confirmRegistration(User user, Locale locale, String appUrl);
}
