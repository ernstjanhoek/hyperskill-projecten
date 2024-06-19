package blockchain;

import blockchain.transactions.Transaction;

import java.security.Signature;
import java.util.ArrayList;
import java.util.Random;

public class UserPool implements Runnable {
    private final Validator validator;
    private final Blockchain blockchain;

    public UserPool(Validator validator, Blockchain blockchain) {
        this.validator = validator;
        this.blockchain = blockchain;
    }

    @Override
    public void run() {
        try {
            ArrayList<User> users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user = new User(new KeyPairCGA().getKeyPair(), "u" + i);
                blockchain.createNewAccount(user.getID());
                users.add(user);
            }

            //noinspection InfiniteLoopStatement
            while (true) {
                Thread.sleep(new Random().nextLong(150L, 1000L));
                Random r = new Random();
                User user0 = users.get(r.nextInt(users.size()));
                User user1 = users.get(r.nextInt(users.size()));

                Transaction tx = new Transaction(
                        user0.getID(),
                        user1.getID(),
                        blockchain.getLedger().get(user0.getID()) / 4,
                        user0.keyPair,
                        Signature.getInstance("SHA256withRSA")
                );
                validator.addTransaction(tx);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}