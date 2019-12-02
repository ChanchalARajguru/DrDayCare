package eu.teama.drdaycare.admin;

import java.util.ArrayList;

import eu.teama.drdaycare.UserTypes.User;

public class UserListResponse {

	private final ArrayList<User> list;
    private final boolean valid;
    public UserListResponse(boolean valid , ArrayList<User> list) {
        this.valid = valid;
        this.list = list;
    }
    public boolean isValid() {
        return valid;
    }
    public ArrayList getList() {
        return list;
    }
}
