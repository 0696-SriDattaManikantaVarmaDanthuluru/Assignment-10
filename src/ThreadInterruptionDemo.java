public class ThreadInterruptionDemo {
    public static void main(String[] args) {
        Thread myThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Working...");
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
            }
        });

        myThread.start();

        try {
            Thread.sleep(100);
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
