package eu.teama.drdaycare.Login.jsonData;

import eu.teama.drdaycare.UserTypes.User;

public class LoginResponse {
    private final boolean valid;
    private final User user;

    public LoginResponse(boolean valid, User user) {
        this.valid = valid;
        this.user = user;
    }

    public boolean isValid() {
        return valid;
    }

    public User getUser(){return user;}
}
