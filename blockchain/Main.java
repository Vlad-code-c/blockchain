package blockchain;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(Block.generateNext());
        blocks.add(Block.generateNext());
        blocks.add(Block.generateNext());
        blocks.add(Block.generateNext());
        blocks.add(Block.generateNext());

        blocks.forEach(System.out::println);


    }
}
