package lesson3.pingPong;

public class Ping implements Runnable{
    SyncQueue q;
public Ping(SyncQueue q){
    this.q = q;
    new Thread(this, "Ping").start();
}

    @Override
    public void run() {
        while (true){
            q.ping();
        }
    }
}

