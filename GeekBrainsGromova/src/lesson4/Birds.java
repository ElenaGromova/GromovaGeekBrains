package lesson4;

public class Birds extends Animal{
    protected boolean canFly = true;

    public void fly(){
        System.out.println(this.name + " is flying");
    }
}
