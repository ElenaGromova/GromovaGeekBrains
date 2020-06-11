package Java_Core_Proff.lesson4.lettersOutput;

public class LettersOutput {
    private int count = 5;
    private final Object mon = new Object();
    private volatile char currentLetter = 'А';

    public  void printA(){
        synchronized (mon){
            try {
                for (int i = 0; i < count; i ++){
                    if (currentLetter == 'B' || currentLetter == 'C'){
                        mon.wait();
                    }
                    System.out.print('A');
                    currentLetter = 'B';
                    mon.notifyAll();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void printB(){
        synchronized (mon){
            try {
                for (int i = 0; i < count; i ++){
                    while (currentLetter == 'A' || currentLetter == 'C'){
                        mon.wait();
                    }
                    System.out.print('B');
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public  void printC(){
        synchronized (mon){
            try {
                for (int i = 0; i < count; i ++){
                    while (currentLetter == 'B' || currentLetter == 'A'){
                        mon.wait();
                    }
                    System.out.print('C');
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        LettersOutput outPut = new LettersOutput();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                outPut.printA();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                outPut.printB();
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                outPut.printC();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
