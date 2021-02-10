package lesson3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterTest {
    public static void main(String[] args) {
        Counter counter = new Counter(0);
        Lock lock = new ReentrantLock();

        for (int i = 0; i < 10; i++) {
        new Thread(() ->{
            lock.lock();
            try {
                counter.increment();
                System.out.println(counter.getCounter());
            }
            finally{
                lock.unlock();
            }
        }
        ).start();
        }
    }
}
