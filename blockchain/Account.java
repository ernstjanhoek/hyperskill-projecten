package blockchain;

import java.security.KeyPair;

public class Account {
    public final KeyPair keyPair;
    private long virtualCoins;

    public Account(KeyPair keyPair) {
        this.virtualCoins = 0L;
        this.keyPair = keyPair;
    }

    public void setVirtualCoins(long virtualCoins) {
        this.virtualCoins = virtualCoins;
    }

    public long getVirtualCoins() {
        return virtualCoins;
    }
}