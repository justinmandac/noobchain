import com.google.gson.GsonBuilder;
import models.Block;

import java.util.ArrayList;

public class NoobChain {
    public static ArrayList<Block> blockChain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        blockChain.add(new Block("Hi I'm the first block", "0"));
        System.out.println("Attempting to mine Block 1");
        blockChain.get(0).mineBlock(difficulty);

        blockChain.add(new Block("Yo, I'm the second block", blockChain.get(blockChain.size() -1).hash));
        System.out.println("Attempting to mine Block 2");
        blockChain.get(1).mineBlock(difficulty);

        blockChain.add(new Block("Yo, I'm the second block", blockChain.get(blockChain.size() -1).hash));
        System.out.println("Attempting to mine Block 3");
        blockChain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is valid? : " + isChainValid());

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(blockChainJson);

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;

        int size = blockChain.size();

        for (int i = 1; i < size; i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal.");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal.");
                return false;
            }
        }

        return true;
    }
}
