package lesson4HomeWork;

public class Cat extends Animal {
    private String res;
    public Cat(String name, int run, int swim, double jump) {
        this.name = name ;
        this.type = "Кот";
        this.run = run;
        this.swim = swim;
        this.jump = jump;
    }

    public Cat(int swim) {
        System.out.println("Как зовут вашего котика?");
        this.name = scanner.nextLine();

        this.type = "Кот";


        //System.out.println("Сколько может пробежать ваш котик? (целое число)");
        //this.run = scanner.nextInt();
        System.out.println("Сколько может пробежать ваш котик? (целое число)");
        while (!scanner.hasNextInt()) {
            System.out.println("Сколько может пробежать ваш котик? (целое число)");
            res = scanner.nextLine();
        }
        this.run = scanner.nextInt();

        this.swim = swim;

        System.out.println("Как высоко может прыгнуть ваш котик? (дробное число, например, 1,1)");
        while (!scanner.hasNextDouble()) {
            System.out.println("Как высоко может прыгнуть ваш котик? (дробное число, например, 1,1)");
            res = scanner.next();
        }
        this.jump = scanner.nextDouble();
    }

    public void catInfo(){
        animalInfo(runCat, swimCat, jumpCat);
    }
}
