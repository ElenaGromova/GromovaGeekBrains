package Java_Core_Proff.lesson4.lettersOutput;

public class MFU {
    public static void main(String[] args) {
        Object mfu = new Object();
        String free;

        Thread employee1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Сотрудник1 сканирует документ с помощью МФУ");
            }
        });
        employee1.start();

        Thread employee2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Сотрудник2 хочет отсканировать и распечатать документ с помощью МФУ");
                synchronized (mfu) {
                    System.out.println("Сотрудник2 сканирует и распечатывает документ с помощью МФУ");
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сотрудник2 освободил МФУ");
                }
            }
        });
        employee2.start();

        Thread employee3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Сотрудник3 хочет распечатать документ с помощью МФУ");
                synchronized (mfu) {
                    System.out.println("Сотрудник3 распечатывает документ с помощью МФУ");
                    try {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сотрудник3 освободил МФУ");
                }
            }
        });
        employee3.start();

        Thread employee4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Сотрудник4 сканирует документ с помощью МФУ");
            }
        });
        employee4.start();

    }
}
