package blockchain.repo;

import blockchain.Block;
import blockchain.StringUtil;

import java.util.*;

public class BlockRepository {
//    public Map<Block> blockchain = new HashMap();
    public ArrayList<Block> blockchain = new ArrayList<>();

    public Block add(Block block) {
        block.setId(blockchain.size());

        if (blockchain.size() == 0)
            block.setPrevHash("0");
        else{
            Block prevBlock = blockchain.get(blockchain.size() - 1);
            block.setPrevHash(prevBlock.generateHash());
        }

        block.setHash(block.generateHash());

        blockchain.add(block);
        return block;
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



}
