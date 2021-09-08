package blockchain;

import java.io.Serializable;
import java.util.Date;

public class Block implements Serializable {
    private long id;
    private long timeStamp;
    private String prevHash;
    private String hash;
    private int magicNum;
    private int generationTime;
    private long minerId;
    private String numOfZeroInfo = "N stay the same";

    private static int numbersOfZero = 5;

    public Block() {
        this.timeStamp = new Date().getTime();
    }


    public String toString() {
        return String.format("Block:\n" +
                        "Created by miner # %d\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block:\n%s\n" +
                        "Hash of the block:\n%s\n" +
                        "Block was generating for %d seconds\n" +
                        "%s",
                this.minerId,
                this.id, this.timeStamp, this.magicNum, this.prevHash,
                this.hash, this.generationTime, this.numOfZeroInfo);
    }


    public void show() {
        System.out.println(this.toString() + "\n");
    }

    public String generateHash() {
        return StringUtil.applySha256(
                "" + this.id + this.timeStamp + this.prevHash + this.magicNum);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }

    public String getHash() {
        return hash;
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

    public long getMinerId() {
        return minerId;
    }

    public void setMinerId(long minerId) {
        this.minerId = minerId;
    }

    public static int getNumbersOfZero() {
        return numbersOfZero;
    }

    public static void setNumbersOfZero(int numbersOfZero) {
        Block.numbersOfZero = numbersOfZero;
    }

    public String getNumOfZeroInfo() {
        return numOfZeroInfo;
    }

    public void setNumOfZeroInfo(String numOfZeroInfo) {
        this.numOfZeroInfo = numOfZeroInfo;
    }
}
