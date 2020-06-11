package lesson4HomeWork;

public class MainClass {
    public static void main(String[] arg){
        Employee[] empArray = new Employee[5];
        empArray[0] = new Employee("Ivanov Ivan", "Engineer", " ivivan@mailbox.com ", "892312312", 30000, 30);
        empArray[1] = new Employee("Petrov Petr", "Driver", " petrov@mailbox.com ", "89507777777", 20000, 21);
        empArray[2] = new Employee("Sidorov Roman", "Developer", " sidorov@mailbox.com ", "89508888888", 90000, 41);
        empArray[3] = new Employee("Ivanova Anna", "Manager", " ivanovaa@mailbox.com ", "89505555555", 27000, 42);
        empArray[4] = new Employee("Petrova Sveta", "Operator", " petrova@mailbox.com ", "89504444444", 20000, 22);

        for (Employee empArr : empArray){
            if (empArr.age > 40) {
                empArr.employeeInfo();
                System.out.println("--------------------");
            }
        }

        Cat[] catArray = new Cat[5];
        catArray[0] = new Cat("Barsik", 200, 0, 2);
        catArray[1] = new Cat("Mudwesia", 300, 0, 3);
        catArray[2] = new Cat("Tasia", 100, 0, 1);
        catArray[3] = new Cat("Lusia", 250, 0, 0.7);
        catArray[4] = new Cat("Kisa", 10, 0, 2.1);

        for (Cat catArr : catArray){
            catArr.catInfo();
        }

        Dog[] dogArray = new Dog[5];
        dogArray[0] = new Dog("Bobik", 500, 10, 0.5, "гончая");
        dogArray[1] = new Dog("Tuzik", 600, 15, 1, "гончая");
        dogArray[2] = new Dog("Druzhok", 400, 3, 0.1, "декоративная");
        dogArray[3] = new Dog("Timmi", 550, 0, 0.7, "декоративная");
        dogArray[4] = new Dog("Chapa", 650, 5, 0.3, "гончая");

        for (Dog dogArr : dogArray){
            dogArr.dogInfo();
        }

        Cat yourCat = new Cat(0);
        yourCat.catInfo();

        Dog yourDog = new Dog();
        yourDog.dogInfo();
    }
}
