package blockchain;

import blockchain.repo.BlockRepository;
import miner.Miner;
import miner.MinerWorker;

public class Main {
    public static void main(String[] args) {
        BlockRepository blockRepo = BlockRepository.getInstance();
        blockRepo.deserialize();

//        System.out.print("Enter how many zeros the hash must start with: ");
//        BlockRepository.numOfZero = new Scanner(System.in).nextInt();



        for (int i = 0; i < 2; i++) {
            Thread thread = new MinerWorker(new Miner(i));
            thread.start();
        }


    }

}