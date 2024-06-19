package blockchain.transactions;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.Signature;

public class Transaction implements Signable {
    private final String fromPublicKey;
    private final String toPublicKey;
    private final long value;
    private final PublicKey publicKey;
    private final byte[] signatureBytes;

    public Transaction(String fromPublicKey, String toPublicKey, long value, KeyPair keyPair, Signature signature) throws Exception {
        this.fromPublicKey = fromPublicKey;
        this.toPublicKey = toPublicKey;
        this.value = value;
        this.publicKey = keyPair.getPublic();
        signatureBytes = signMessage((fromPublicKey + toPublicKey + value).getBytes(),
                keyPair.getPrivate(),
                signature);
    }

    public String getFromPublicKey() {
        return fromPublicKey;
    }

    public String getToPublicKey() {
        return toPublicKey;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return fromPublicKey + " sent " + value + " to " + toPublicKey;
    }

    public boolean verify(Signature signature) throws Exception {
        return this.verifySignature(
                signatureBytes,
                (fromPublicKey + toPublicKey + value).getBytes(),
                this.publicKey,
                signature
        );
    }
}