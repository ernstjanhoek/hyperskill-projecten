package transactions;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.Signature;

public class TextMessage implements Signable {
    private final String message;
    private final byte[] signatureBytes;
    private final PublicKey publicKey;

    public TextMessage(String message, KeyPair keyPair, Signature signature) throws Exception {
        this.message = message;
        this.publicKey = keyPair.getPublic();
        this.signatureBytes = signMessage(message.getBytes(),
                keyPair.getPrivate(),
                signature);
    }

    public String getMessage() {
        return message;
    }

    public boolean verify(Signature signature) throws Exception {
        return verifySignature(signatureBytes, this.message.getBytes(), publicKey, signature);
    }

    @Override
    public String toString() {
        return message;
    }
}
