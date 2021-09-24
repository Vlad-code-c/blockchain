package miner;

import blockchain.Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CryptoMine {
    private static final int BLOCKS_TO_MINE = 10;
    private static final int MINERS_HIRED = 4;

    public void mining() {
        ExecutorService executor = Executors.newFixedThreadPool(MINERS_HIRED);
        IntStream.rangeClosed(1, MINERS_HIRED).mapToObj(MinerWorker::new)
                .limit(BLOCKS_TO_MINE).forEach(Thread::start);


        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        Main.running = false;
    }
}
