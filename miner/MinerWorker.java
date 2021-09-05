package miner;

public class MinerWorker extends Thread {
    private final Miner miner;

    public MinerWorker(Miner miner) {
        this.miner = miner;
        Thread.currentThread().setName("thread-miner-" + miner.getId());
    }

    @Override
    public void run() {
        while (miner.getValue() <= 10) {
            miner.generateNext();
        }
    }
}
