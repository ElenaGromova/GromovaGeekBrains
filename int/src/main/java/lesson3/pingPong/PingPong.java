package lesson3.pingPong;

public class PingPong {
    public static void main(String[] args) {
        SyncQueue q = new SyncQueue();
        for (int i = 0; i < 10; i++) {
            new Ping(q);
            new Pong(q);
        }
    }
}
