package blockchain.repo;

import blockchain.Block;
import persistence.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class BlockRepository implements Serializable {

    private static final long serialVersionUID = 111L;
    private static BlockRepository instance;

//    public Map<Block> blockchain = new HashMap();
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

    public void add(Block block) {
        blockchain.add(block);
    }

    public Optional<Block> getById(int id) {
        return blockchain.stream().filter(block -> block.getId() == id).findFirst();
    }

    public boolean validateAll() {
        for (int i = 0; i < blockchain.size(); i++) {
            if (i == 0 && !blockchain.get(i).getHash().equals("0"))
                return false;
            else {
                if (!blockchain.get(i).getHash().equals(
                        blockchain.get(i - 1).generateHash()))
                    return false;
            }
        }

        return true;
    }

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
            this.blockchain = blockRepo.blockchain;

            boolean b = validateAll();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }



}
