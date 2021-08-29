package blockchain;

import blockchain.repo.BlockRepository;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            blocks.add(Block.generateNext());
            showLast(blocks);
        }
    }
    public static void showLast(ArrayList<Block> blocks) {
        blocks.get(blocks.size() - 1).show();
    }
}
