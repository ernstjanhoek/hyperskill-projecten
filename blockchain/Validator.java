package blockchain;

import blockchain.transactions.TextMessage;
import blockchain.transactions.Transaction;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Validator implements Runnable {
    private final Blockchain blockchain;
    private final BlockingQueue<Block> blockQueue = new LinkedBlockingQueue<>();
    private final List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());
    private final List<TextMessage> messages = Collections.synchronizedList(new ArrayList<>());
    private final Signature sig = Signature.getInstance("SHA256withRSA");

    public Validator(Blockchain blockchain) throws NoSuchAlgorithmException {
        this.blockchain = blockchain;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Block block = blockQueue.take();
                if (validateBlock(block)) {
                    synchronized (transactions) {
                        block.addBlockData(List.copyOf(transactions));
                        transactions.clear();
                    }
                    synchronized (messages) {
                        block.addBlockData(List.copyOf(messages));
                        messages.clear();
                    }
                    long creationTime = calculateCreationTime(block);
                    blockchain.addBlock(
                            block,
                            creationTime
                    );
                    blockQueue.clear();
                    System.out.println(blockchain);
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean validateBlock(Block block) {
        String previousHash = blockchain.getLast().map(Block::getCurrentHash).orElse("0");
        long previousId = blockchain.getLast().map(Block::getId).orElse(0L);
        String genHash = StringUtil.applySha256(block.getId()
                + block.getTimestamp()
                + previousHash
                + block.getMagicNumber()
        );
        return (genHash.equals(block.getCurrentHash()) && block.getId() - 1L == previousId);
    }

    private long calculateCreationTime(Block block) {
        return TimeUtil.calculateCreationTime(
                blockchain.getLast().map(Block::getTimestamp).orElse(new Date().getTime()),
                block.getTimestamp()
        );
    }

    public void addBlock(Block block) {
        blockQueue.add(block);
    }

    public Optional<Block> getLastBlock() {
        return blockchain.getLast();
    }

    public int getRequiredZeros() {
        return blockchain.getRequiredZeros();
    }

    public void addMessage(TextMessage message) throws Exception {
        if (message.verify(sig)) {
            messages.add(message);
        }
    }

    public void addTransaction(Transaction transaction) throws Exception {
        if (transaction.verify(sig)) {
            transactions.add(transaction);
        }
    }
}