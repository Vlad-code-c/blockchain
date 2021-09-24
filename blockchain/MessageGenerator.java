package blockchain;

import entity.Message;
import persistence.MessageStorage;

import java.util.Random;

public class MessageGenerator implements Runnable {
    private static final String[] names = {"Adam", "Bob", "Cecile", "Damian", "Greg", "Mark", "Mike", "Rob"};
    private static final String[] words = {"will kill", "smoke with", "hate", "love", "looking for", "miss", "like"};

//    public static void creatingMessage() {
//        CountDownLatch latch = new CountDownLatch(new Random().nextInt(5));
//        while (latch.getCount() != 0) {
//
//        }
//    }

    public void run(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        run();
    }

    @Override
    public void run() {
        while (true) {
            MessageStorage.addMessage(new Message(
                    names[new Random().nextInt(names.length)]
                            + " " +
                            words[new Random().nextInt(words.length)]
            ));
            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
