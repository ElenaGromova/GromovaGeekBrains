package lesson4HomeWorkNeed;

public class Dog extends Animal {

        public Dog(String name){
            super("Dog " + name);
            this.runLimit = 500;
            this.jumpLimit = 0.5;
            this.swimLimit = 10;
        }

}
