package blockchain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

public class KeyPairCGA {
    private final KeyPair keyPair;

    KeyPairCGA() throws NoSuchAlgorithmException {
        KeyPairGenerator keypairGenerator = KeyPairGenerator.getInstance("RSA");
        keypairGenerator.initialize(1024);
        this.keyPair = keypairGenerator.generateKeyPair();
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    public PublicKey getPublicKey() {
        return this.keyPair.getPublic();
    }
}