package lesson3.pingPong;

public class SyncQueue {
    boolean flag = false;
    synchronized void pong() {
        while (!flag){
            try{
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        System.out.println("pong");
        flag = false;
        notify();
    }

    synchronized void ping() {
        while (flag) {
            try {
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ping");
        flag = true;
        notify();
    }
}


