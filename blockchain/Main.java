package blockchain;

import blockchain.repo.BlockRepository;
import miner.Miner;
import miner.MinerWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockRepository blockRepo = BlockRepository.getInstance();
        blockRepo.deserialize();

//        System.out.print("Enter how many zeros the hash must start with: ");
//        BlockRepository.numOfZero = new Scanner(System.in).nextInt();



//        for (int i = 0; i < 5; i++) {
//            Thread thread = new MinerWorker(new Miner(i));
//            thread.start();
//        }

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.submit(() -> {
            new MinerWorker(new Miner()).start();
        });

        executor.awaitTermination(10, TimeUnit.SECONDS);


    }

}