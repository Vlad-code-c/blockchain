package miner;

import repo.BlockRepository;

public class MinerWorker extends Thread {
    private final Miner miner;

    public MinerWorker(long minerId) {
        this.miner = new Miner(minerId);
        Thread.currentThread().setName("thread-miner-" + miner.getId());
    }

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
