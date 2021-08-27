package blockchain;

import blockchain.repo.BlockRepository;

import java.util.Date;
import java.util.Random;

public class Block {
    private long id;
    private long timeStamp;
    private String prevHash;
    private String hash;
    private int magicNum;

    private static int numbersOfZero = 2;

    private static final BlockRepository blockRepository = new BlockRepository();
    private static final Random random = new Random();

    private Block() {
        this.timeStamp = new Date().getTime();
    }


    public static Block generateNext() {
        Block block = new Block();
        block = blockRepository.add(block);
        while (!block.checkIfHaveNumbersOfZero()){
            block.generateNextMagicalNum();
        }
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
        return String.format("Block:\nId: %d\nTimestamp: %d\nHash of the previous block:\n%s\nHash of the block:\n%s\n",
                this.id, this.timeStamp, this.prevHash, this.hash);
    }

    public String generateHash() {
        return StringUtil.applySha256("" + this.id + this.timeStamp + this.prevHash + this.magicNum);
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
}
