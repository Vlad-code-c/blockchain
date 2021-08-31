package blockchain;

import blockchain.repo.BlockRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BlockRepository blockRepo = BlockRepository.getInstance();
        blockRepo.deserialize();

//        for (int i = 0; i < blockRepo.getSize(); i++) {
//            blockRepo.getById(i).ifPresent((Block::show));
//        }

        System.out.print("Enter how many zeros the hash must start with: ");
        Block.setNumbersOfZero(new Scanner(System.in).nextInt());



        for (int i = 0; i <  5; i++) {
            Block block  = Block.generateNext();
            block.show();
        }

    }
}
