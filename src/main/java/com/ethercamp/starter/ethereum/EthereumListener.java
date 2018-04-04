package com.ethercamp.starter.ethereum;

import org.ethereum.core.Block;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.facade.Ethereum;
import org.ethereum.listener.EthereumListenerAdapter;
import org.ethereum.util.ByteUtil;
import org.spongycastle.util.encoders.Hex;

import java.util.List;

public class EthereumListener extends EthereumListenerAdapter {

    Ethereum ethereum;
    private boolean syncDone = false;

    public EthereumListener(Ethereum ethereum) {
        this.ethereum = ethereum;
    }

    @Override
    public void onBlock(Block block, List<TransactionReceipt> receipts) {

        if (syncDone) {
            // just 5 not to spam STDOUT
            block.getTransactionsList().stream().limit(5).forEach((t) -> {
                Long l = ByteUtil.byteArrayToLong(t.getValue());

                System.out.println("Tx " + Hex.toHexString(t.getSender()) + " -> " + Hex.toHexString(t.getReceiveAddress()) + " send " + l + " wei");
            });
        }
    }

    /**
     *  Mark the fact that you are touching
     *  the head of the chain
     */
    @Override
    public void onSyncDone(SyncState state) {
        if (!syncDone) {
            System.out.println(" ** SYNC DONE ** ");
            syncDone = true;
        }
    }



}
