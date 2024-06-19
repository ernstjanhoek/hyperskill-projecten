package blockchain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService threadPool0 = Executors.newFixedThreadPool(8);
        AtomicReference<ExecutorService> threadPool1 = new AtomicReference<>(Executors.newFixedThreadPool(8));
        Blockchain blockchain = new Blockchain(1);

        Validator validator = new Validator(blockchain);
        threadPool0.submit(validator);
        // threadPool0.submit(new DataSupplier(validator));
        threadPool0.submit(new UserPool(validator, blockchain));

        Miner miner0 = new Miner(validator, new KeyPairCGA().getKeyPair(), "miner1");
        Miner miner1 = new Miner(validator, new KeyPairCGA().getKeyPair(), "miner2");
        Miner miner2 = new Miner(validator, new KeyPairCGA().getKeyPair(), "miner3");
        Miner miner3 = new Miner(validator, new KeyPairCGA().getKeyPair(), "miner4");
        Miner miner4 = new Miner(validator, new KeyPairCGA().getKeyPair(), "miner5");
        Miner miner5 = new Miner(validator, new KeyPairCGA().getKeyPair(), "miner6");
        Miner miner6 = new Miner(validator, new KeyPairCGA().getKeyPair(), "miner7");
        threadPool1.get().submit(miner0);
        threadPool1.get().submit(miner1);
        threadPool1.get().submit(miner2);
        threadPool1.get().submit(miner3);
        threadPool1.get().submit(miner4);
        threadPool1.get().submit(miner5);
        threadPool1.get().submit(miner6);


        Thread watchThread = new Thread(() -> {
            while (true) {
                try {
                    blockchain.getNotificationQueue().take();
                    // System.out.println("New block mined. Restarting miners");
                    threadPool1.set(Executors.newFixedThreadPool(8));
                    threadPool1.get().submit(miner0);
                    threadPool1.get().submit(miner1);
                    threadPool1.get().submit(miner2);
                    threadPool1.get().submit(miner3);
                    threadPool1.get().submit(miner4);
                    threadPool1.get().submit(miner5);
                    threadPool1.get().submit(miner6);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        watchThread.start();

        Thread snapShotThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000L);
                    System.out.println("--------------------------------------------------------");
                    System.out.println(blockchain.getStrSnapshot());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        // snapShotThread.start();

        while (true) {
            if (blockchain.getBlockChain().size() >= 13) {
                System.out.println(blockchain.getStrSnapshot());
                threadPool0.shutdown();
                threadPool1.get().shutdown();
                sleep(30L);
                System.exit(0);
            }
        }
    }
}