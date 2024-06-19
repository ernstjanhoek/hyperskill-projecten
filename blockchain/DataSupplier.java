package blockchain;

import blockchain.transactions.TextMessage;

import java.security.Signature;

import static java.lang.Thread.sleep;

public class DataSupplier implements Runnable {
    private final Validator validator;

    static String[] stringArray = {
            "We commit to being supportive and inclusive.",
            "We actively listen to each other with respect.",
            "We provide an open and safe space to all, and respect each other's boundaries.",
            "We provide the opportunity for everyone to be heard.",
            "We proactively communicate with our clients about the status of the product.",
            "We aim to create outcomes that benefit everyone.",
            "We utilize each other's strength to improve the product.",
            "We set identifiable goals within set deadlines.",
            "We openly communicate about the progress in our work, even when things don't go as planned.",
            "We prioritize quality over trends.",
            "We meet the expectations of our clients."
    };

    public DataSupplier(Validator validator) {
        this.validator = validator;
    }


    @Override
    public void run() {
        try {
            KeyPairCGA kp = new KeyPairCGA();
            Signature sig = Signature.getInstance("SHA256withRSA");
            while (true) {
                String random = getRandomString();
                validator.addMessage(new TextMessage(random, kp.getKeyPair(), sig));
                sleep(1500L);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String getRandomString() {
        return stringArray[(int) (Math.random() * (stringArray.length - 1))];
    }
}
