package miner;

import blockchain.Block;
import blockchain.repo.BlockRepository;

import java.util.Date;
import java.util.Random;

public class Miner {
    private static final BlockRepository blockRepository = BlockRepository.getInstance();
    private static final Random random = new Random();

    private long id;
    private double value;


    public Miner(long id) {
        this.id = id;
    }

    public Block generateNext() {
        final Block block = new Block();

        block.setId(blockRepository.getSize());

        blockRepository.getLast().ifPresentOrElse(
                (lastBlock) -> block.setPrevHash(lastBlock.getHash()),
                () -> block.setPrevHash("0")
        );

        block.setHash(block.generateHash());

        while (!checkIfHaveNumbersOfZero(block)){
            block.setMagicNum(generateNextMagicalNum());
        }
        block.setGenerationTime(
                (int) ((new Date().getTime() - block.getTimeStamp()) / 1000));

        try {
            blockRepository.add(block);
            block.show();
            value++;

            return block;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static boolean checkIfHaveNumbersOfZero(Block block) {
        block.setHash(block.generateHash());
        return block.getHash().matches("0{" + BlockRepository.numOfZero + "}\\w*");
    }

    public static int generateNextMagicalNum() {
        return random.nextInt();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
