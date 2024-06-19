package blockchain;

import blockchain.transactions.Signable;
import java.util.ArrayList;
import java.util.List;

public class Block {

    private final long id;
    private final String minedBy;
    private final String previousHash;
    private final String currentHash;
    private final long timestamp;
    private final int magicNumber;
    private final List<Signable> blockData = new ArrayList<>();

    public Block(long id, String minedBy, String previousHash, String currentHash, long timestamp, int magicNumber) {
        this.id = id;
        this.minedBy = minedBy;
        this.previousHash = previousHash;
        this.currentHash = currentHash;
        this.timestamp = timestamp;
        this.magicNumber = magicNumber;
    }


    public String getMinedBy() {
        return minedBy;
    }

    public long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getMagicNumber() {
        return this.magicNumber;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    @Override
    public String toString() {
        return "Block:" +
                "\nCreated by miner: " + getMinedBy() +
                "\nMiner " + getMinedBy() + " gets 100 VC" +
                "\nId: " + id +
                "\nTimestamp: " + timestamp +
                "\nMagic number: " + this.magicNumber +
                "\nHash of the previous block:\n" + this.previousHash +
                "\nHash of the block:\n" + this.currentHash +
                "\nBlock data:\n" + blockDataString();
    }

    public void addBlockData(List<Signable> blockData) {
        this.blockData.addAll(blockData);
    }

    public List<Signable> getBlockData() {
        return blockData;
    }

    private String blockDataString() {
        return this.blockData.isEmpty() ?
                "no message" :
                String.join("\n", blockData.stream().map(Object::toString).toList()
                );
    }
}