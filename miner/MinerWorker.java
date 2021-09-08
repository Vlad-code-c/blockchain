package miner;

import blockchain.repo.BlockRepository;
import log.Logger;

public class MinerWorker extends Thread {
    private final Miner miner;

    public MinerWorker(Miner miner) {
        this.miner = miner;
        Thread.currentThread().setName("thread-miner-" + miner.getId());
    }

    @Override
    public void run() {
        while (BlockRepository.getInstance().getSize() < 5) {
            miner.generateNext();
        }
    }
}
