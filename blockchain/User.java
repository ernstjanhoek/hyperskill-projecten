package blockchain;

import java.security.KeyPair;

public class User extends Account {
    private final String userID;

    public User(KeyPair keyPair, String userID) {
        super(keyPair);
        this.userID = userID;
    }
    public String getID() {
        return userID;
    }
}