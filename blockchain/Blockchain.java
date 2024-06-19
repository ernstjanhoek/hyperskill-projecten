package blockchain;

import blockchain.transactions.TextMessage;
import blockchain.transactions.Transaction;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Blockchain {
    private int requiredZeros;
    private int compValue;
    private long creationTime;
    private final List<Block> blockChain;
    private final ConcurrentHashMap<String, Long> ledger;
    private final List<String> messages;
    private final int blockTime;
    private final BlockingQueue<Boolean> notificationQueue;

    public Blockchain(int blockTime) {
        this.ledger = new ConcurrentHashMap<>();
        blockChain = Collections.synchronizedList(new ArrayList<>());
        messages = Collections.synchronizedList(new ArrayList<>());
        notificationQueue = new LinkedBlockingQueue<>();
        this.blockTime = blockTime;
    }

    public void addBlock(Block block, long creationTime) {
        this.creationTime = creationTime;
        updateZeros();
        blockChain.add(block);
        Long balance = ledger.get(block.getMinedBy());
        if (balance == null) {
            ledger.put(block.getMinedBy(), 200L);
        } else {
            ledger.put(block.getMinedBy(), balance + 100L);
        }
        block.getBlockData().forEach(e -> {
            if (e instanceof Transaction tx) {
                processTransaction(tx);
            } else if (e instanceof TextMessage msg) {
               messages.add(msg.getMessage());
            }
        });
        notificationQueue.add(true);
    }

    public BlockingQueue<Boolean> getNotificationQueue() {
        return notificationQueue;
    }

    public void createNewAccount(String pubkey) {
        if (!ledger.containsKey(pubkey)) {
            ledger.put(pubkey, 100L);
        }
    }

    public void processTransaction(Transaction transaction) {
       Long from = ledger.get(transaction.getFromPublicKey());
       Long to = ledger.get(transaction.getToPublicKey());
       Long amount = transaction.getValue();
       if (from != null && amount <= from) {
           ledger.put(transaction.getToPublicKey(), to + amount);
           ledger.put(transaction.getFromPublicKey(), from - amount);
       }
    }

    public List<Block> getBlockChain() {
        return blockChain;
    }

    public Optional<Block> getLast() {
        if (blockChain.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(blockChain.get(blockChain.size() - 1));
        }
    }

    public int getRequiredZeros() {
        return this.requiredZeros;
    }

    private void updateZeros() {
        Optional<Block> lastBlock = getLast();
        if (lastBlock.isPresent()) {
            compValue = Long.compare(blockTime, this.creationTime / 1000);
            requiredZeros = Math.max(0, requiredZeros + compValue);
        } else {
            requiredZeros++;
        }
    }

    public ConcurrentHashMap<String, Long> getLedger() {
        return ledger;
    }

    @Override
    public String toString() {
        if (getLast().isPresent()) {
            return getLast().get() + "\n"
                    + "Block was generating for " + this.creationTime/ 1000 + " seconds\n"
                    + returnCompValue();
        } else {
            return "Something wrong, no block mined yet!";
        }
    }

    private String returnCompValue() {
        return switch (compValue) {
            case -1 -> "N was decreased by 1";
            case 0 -> "N stays the same";
            case 1 -> "N was increased to " + requiredZeros;
            default -> "N was increased to 1";
        };
    }

    public String getStrSnapshot() {
        StringBuilder sb = new StringBuilder();
        ledger.forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
        return sb.toString();
    }
}