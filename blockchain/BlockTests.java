package blockchain;

/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.Signature;
import java.util.Date;

public class BlockTests {
    @Test
    void testBlockHash() {
        Assertions.assertTrue(
                StringUtil.isValidHash(
                        "00000a3fe20573b5bb358d2291165e15662a5b057240e954c573fb1f2a6d0cb8",
                        5
                ));
        Assertions.assertFalse(
                StringUtil.isValidHash(
                        "00000a3fe20573b5bb358d2291165e15662a5b057240e954c573fb1f2a6d0cb8",
                        6
                ));
    }

    @Test
    void testCreationTime() {
        long time = new Date().getTime();
        Assertions.assertEquals(
                TimeUtil.calculateCreationTime(time - 1000L, time),
                1000L
        );
    }

    @Test
    void testMessage() throws Exception {
        KeyPairCGA kpGen = new KeyPairCGA();
        KeyPair kp = kpGen.getKeyPair();
        Signature signature = Signature.getInstance("SHA256withRSA");
        Signable msg = new Signable("Hello cGAChain!", kp.getPrivate(), signature);

        Assertions.assertTrue(msg.verify(kp.getPublic(), signature));
        Assertions.assertEquals(new String(msg.getMessage()), "Hello cGAChain!");
    }

    static String example1 = """
                    Block:
                    Created by miner # 9
                    Id: 1
                    Timestamp: 1539866031047
                    Magic number: 23462876
                    Hash of the previous block:
                    0
                    Hash of the block:
                    1d12cbbb5bfa278734285d261051f5484807120032cf6adcca5b9a3dbf0e7bb3
                    Block was generating for 0 seconds
                    N was increased to 1
                                   \s
                    Block:
                    Created by miner # 7
                    Id: 2
                    Timestamp: 1539866031062
                    Magic number: 63576287
                    Hash of the previous block:
                    1d12cbbb5bfa278734285d261051f5484807120032cf6adcca5b9a3dbf0e7bb3
                    Hash of the block:
                    04a6735424357bf9af5a1467f8335e9427af714c0fb138595226d53beca5a05e
                    Block was generating for 0 seconds
                    N was increased to 2
                                   \s
                    Block:
                    Created by miner # 1
                    Id: 3
                    Timestamp: 1539866031063
                    Magic number: 57875299
                    Hash of the previous block:
                    04a6735424357bf9af5a1467f8335e9427af714c0fb138595226d53beca5a05e
                    Hash of the block:
                    0061924d48d5ce30e97cfc4297f3a40bc94dfac6af42d7bf366d236007c0b9d3
                    Block was generating for 0 seconds
                    N was increased to 3
                                   \s
                    Block:
                    Created by miner # 2
                    Id: 4
                    Timestamp: 1539866256729
                    Magic number: 23468237
                    Hash of the previous block:
                    0061924d48d5ce30e97cfc4297f3a40bc94dfac6af42d7bf366d236007c0b9d3
                    Hash of the block:
                    000856a20d767fbbc38e0569354400c1750381100984a09a5d8b1cdf09b0bab6
                    Block was generating for 5 seconds
                    N was increased to 4
                                   \s
                    Block:
                    Created by miner # 9
                    Id: 5
                    Timestamp: 1539866256749
                    Magic number: 18748749
                    Hash of the previous block:
                    000856a20d767fbbc38e0569354400c1750381100984a09a5d8b1cdf09b0bab6
                    Hash of the block:
                    000031e22049646ca25c5f63fcc070e8c76319a050a7d1d5ca402090a30e9612
                    Block was generating for 15 seconds
                    N stays the same
                    ""\");
            """;
}
 */