package com.ethercamp.starter.ethereum;

import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.ethereum.sync.SyncPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


public class EthereumBean {

    Ethereum ethereum;

//    @Autowired
//    private SyncPool syncPool;
//
    public void start(){
        this.ethereum = EthereumFactory.createEthereum();
        this.ethereum.addListener(new EthereumListener(ethereum));

        this.ethereum.startPeerDiscovery();
    }


    public String getBestBlock(){
        return "" + ethereum.getBlockchain().getBestBlock().getNumber();
    }

    @Scheduled(fixedDelay = 30000)
    public void sometimes() {

//        if (syncPool != null) {
//            System.out.println("Sync pool active peers size" + syncPool.getActivePeers().size());
//        } else {
//            System.out.println("Syncpool is null");
//        }

        System.out.println("Active peers size = " + ethereum.getChannelManager().getActivePeers().size());
    }
}
