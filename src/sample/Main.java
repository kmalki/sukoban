package sample;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            public void run() {
                new IHMFX().lance();
            }
        };
        thread.start();
    }
}
