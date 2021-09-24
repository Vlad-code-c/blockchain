package blockchain;

import miner.CryptoMine;
import repo.BlockRepository;

public class Main {
    public static boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        BlockRepository blockRepo = BlockRepository.getInstance();
        blockRepo.deserialize();


        CryptoMine cryptoMine = new CryptoMine();

        new Thread(new MessageGenerator()).start();
//        .run(500);

        cryptoMine.mining();
    }
}
