package blockchain;

import java.security.KeyPair;

public class Miner extends Account implements Runnable {
    final Validator validator;
    final String minerId;

    public Miner(Validator validator, KeyPair minerKey, String minerId) {
        super(minerKey);
        this.validator = validator;
        this.minerId = minerId;
    }

    @Override
    public void run() {
        BlockGenerator blockGenerator = new BlockGenerator();
        //noinspection InfiniteLoopStatement
        while (true) {
            Block block = blockGenerator.generateBlock(
                    validator.getLastBlock(),
                    validator.getRequiredZeros(),
                    minerId
            );
            this.validator.addBlock(block);
        }
    }

    public String getMinerId() {
        return minerId;
    }
}