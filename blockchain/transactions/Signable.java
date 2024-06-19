package blockchain.transactions;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public interface Signable {
    default byte[] signMessage(byte[] message, PrivateKey privateKey, Signature signature) throws Exception {
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    default boolean verifySignature(byte[] signatureBytes, byte[] message, PublicKey publicKey, Signature signature) throws Exception {
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(signatureBytes);
    }
}