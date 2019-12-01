package Java_Core_Lesson1.Members;


public class Robot implements Competitor {
    String name;

    int maxRunDistance;
    int maxJumpHeight;

    boolean active;

    @Override
    public boolean isOnDistance() {return active;}

    @Override
    public void info() {
        String result = isOnDistance() ? " справился со всеми спортивными испытаниями" : " не справился с нагрузкой";
        System.out.println(name + " с пробегом " + maxRunDistance + " и пропрыгом " + maxJumpHeight + " - " + result);
    }

    public Robot(String name, int maxRunDistance, int maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.active = true;
    }

    @Override
    public void run(int dist){
        if ((dist<=maxRunDistance)) {
            System.out.println(name + "  хорошо справился с кроссом");
        }
        else {
            System.out.println(name + "  не справился с кроссом");
            active = false;
        }
    }

    @Override
    public void jump(int dist){
        if ((dist<=maxJumpHeight)) {
            System.out.println(name + "  хорошо справился со стеной");
        }
        else {
            System.out.println(name + "  не справился со стеной");
            active = false;
        }
    }
}
