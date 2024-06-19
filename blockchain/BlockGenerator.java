package blockchain;

import java.util.*;

public class BlockGenerator {
    public Block generateBlock(Optional<Block> lastBlock, int zeros, String minerId) {
        String previousHash = lastBlock.map(Block::getCurrentHash).orElse("0");
        long blockId = lastBlock.map(Block::getId).orElse(0L) + 1L;

        Random r = new Random();
        int magicN;
        String currentHash;
        long timestamp;
        do {
            timestamp = new Date().getTime();
            magicN = r.nextInt(Integer.MAX_VALUE);
            currentHash = StringUtil.applySha256(blockId
                    + timestamp
                    + previousHash
                    + magicN
            );
        } while (!StringUtil.isValidHash(currentHash, zeros));
        return new Block(blockId, minerId, previousHash, currentHash, timestamp, magicN);
    }
}

