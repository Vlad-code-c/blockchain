package blockchain.repo;

import blockchain.Block;
import persistence.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class BlockRepository implements Serializable {

    private static final long serialVersionUID = 116L;
    private static BlockRepository instance;
    public static int numOfZero = 0;

    private ArrayList<Block> blockchain = new ArrayList<>();

    private BlockRepository() {}

    public static BlockRepository getInstance() {
        if (instance == null)
            instance = new BlockRepository();

        return instance;
    }

    public int getSize() {
        return blockchain.size();
    }

    public Optional<Block> getLast() {
        if (blockchain.isEmpty()) return Optional.empty();
        return Optional.of(blockchain.get(blockchain.size() - 1));
    }

    public synchronized void add(Block block) throws Exception {
        //Validate block
        if (blockchain.size() == 0 && block.getPrevHash().equals("0") ||
                (block.getPrevHash().equals(getLast().get().generateHash()) &&
                 checkIfHaveNumbersOfZero(block))) {

            blockchain.add(block);

            regulateNumberOfZero(block.getGenerationTime());

            //TODO: Serialize one by one
            serialize();
        } else {
            throw new Exception("Invalid block");
        }
    }


    private void regulateNumberOfZero(int seconds) {
        if (seconds <= 0) {
            numOfZero++;
        } else if (seconds >= 60) {
            numOfZero--;
        }
    }

    public boolean checkIfHaveNumbersOfZero(Block block) {
        return block.getHash().matches("0{" + numOfZero + "}\\w*");
    }

    public Optional<Block> getById(int id) {
        return blockchain.stream().filter(block -> block.getId() == id).findFirst();
    }

    public static boolean validateAll(BlockRepository blockRepository) {
        for (int i = 0; i < blockRepository.blockchain.size(); i++) {
            if (i == 0 && !blockRepository.blockchain.get(i).getHash().equals("0"))
                return false;
            else {
                if (!blockRepository.blockchain.get(i).getHash().equals(
                        blockRepository.blockchain.get(i - 1).generateHash()))
                    return false;
            }
        }

        return true;
    }



    //TODO: Move Serialization logic to super class

    private String getSerializationFileName() {
        return this.getClass().getName() + "_" + serialVersionUID;
    }

    public boolean serialize() {
        try {
            SerializationUtils.serialize(this, getSerializationFileName());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deserialize() {
        try {
            BlockRepository blockRepo = (BlockRepository) SerializationUtils.deserialize(getSerializationFileName());
//            this.blockchain = blockRepo.blockchain;

            if (validateAll(blockRepo)) {
                BlockRepository.instance = blockRepo;
                return true;
            } else {
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
        }

        return false;
    }


    public static void setInstance(BlockRepository instance) {
        BlockRepository.instance = instance;
    }

    public static int getNumOfZero() {
        return numOfZero;
    }

    public static void setNumOfZero(int numOfZero) {
        BlockRepository.numOfZero = numOfZero;
    }

    public ArrayList<Block> getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(ArrayList<Block> blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public String toString() {
        return "BlockRepository{" +
                "blockchain=" + blockchain +
                '}';
    }
}
