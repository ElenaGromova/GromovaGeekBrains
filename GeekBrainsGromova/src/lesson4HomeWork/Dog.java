package lesson4HomeWork;

public class Dog extends Animal {
    protected String[] fast = {"гончая", "декоративная"};
    protected String faster;
    private String res;

    public Dog(String name, int run, int swim, double jump, String faster) {
        this.name = name ;
        this.type = "Собака";
        this.faster = faster;
        this.run = run;
        this.swim = swim;
        this.jump = jump;
    }

    public Dog(){
        this.type = "Собака";

        System.out.println("Как зовут вашего песика?");
        this.name = scanner.next();

        System.out.println("Какой ваш песик? (гончая или декоративная)");
        this.faster = scanner.next();

        System.out.println("Сколько может пробежать ваш песик? (целое число)");
        while (!scanner.hasNextInt()) {
            System.out.println("Сколько может пробежать ваш песик? (целое число)");
            res = scanner.next();
        }
        this.run = scanner.nextInt();

        System.out.println("Как высоко может прыгнуть ваш песик? (дробное число, например, 1,1)");
        while (!scanner.hasNextDouble()) {
            System.out.println("Как высоко может прыгнуть ваш песик? (дробное число, например, 1,1)");
            res = scanner.nextLine();
        }
        this.jump = scanner.nextDouble();

        System.out.println("Сколько может проплыть ваш песик? (целое число)");
        while (!scanner.hasNextInt()) {
            System.out.println("Сколько может проплыть ваш песик? (целое число)");
            res = scanner.next();
        }
        this.swim = scanner.nextInt();

        scanner.close();
    }

    public void dogInfo(){
        if (fast[0].equals(faster)) {
            animalInfo(runDog1, swimDog, jumpDog);
        } else {
            animalInfo(runDog2, swimDog, jumpDog);
        }
    }
}
