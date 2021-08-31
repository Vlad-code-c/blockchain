package blockchain;

import blockchain.repo.BlockRepository;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Block implements Serializable {
    private long id;
    private long timeStamp;
    private String prevHash;
    private String hash;
    private int magicNum;
    private int generationTime;

    private static int numbersOfZero = 5;

    private static final BlockRepository blockRepository = BlockRepository.getInstance();
    private static final Random random = new Random();

    private Block() {
        this.timeStamp = new Date().getTime();
    }

    public static Block generateNext() {
        final Block block = new Block();

        block.setId(blockRepository.getSize());

        blockRepository.getLast().ifPresentOrElse(
                (lastBlock) -> block.setPrevHash(lastBlock.getHash()),
                () -> block.setPrevHash("0")
        );

        block.setHash(block.generateHash());

        while (!block.checkIfHaveNumbersOfZero()){
            block.generateNextMagicalNum();
        }
        block.setGenerationTime(
                (int) ((new Date().getTime() - block.timeStamp) / 1000));

        blockRepository.add(block);
        return block;
    }

    public boolean checkIfHaveNumbersOfZero() {
        this.hash = generateHash();
        return hash.matches("0{" + numbersOfZero + "}\\w*");
    }

    public void generateNextMagicalNum() {
        magicNum = random.nextInt();
    }

    public static boolean validateAll() {
        return blockRepository.validateAll();
    }

    public String toString() {
        return String.format("Block:\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block:\n%s\n" +
                        "Hash of the block:\n%s\n" +
                        "Block was generating for %d seconds",
                this.id, this.timeStamp, this.magicNum, this.prevHash,
                this.hash, this.generationTime);
    }


    public void show() {
        System.out.println(this.toString() + "\n");
    }

    public String generateHash() {
        return StringUtil.applySha256(
                "" + this.id + this.timeStamp + this.prevHash + this.magicNum);
    }

    public long getId() {
        return this.id;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public String getPrevHash() {
        return this.prevHash;
    }

    public String getHash() {
        return this.hash;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getMagicNum() {
        return magicNum;
    }

    public void setMagicNum(int magicNum) {
        this.magicNum = magicNum;
    }

    public int getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(int generationTime) {
        this.generationTime = generationTime;
    }

    public static int getNumbersOfZero() {
        return numbersOfZero;
    }

    public static void setNumbersOfZero(int numbersOfZero) {
        Block.numbersOfZero = numbersOfZero;
    }
}
