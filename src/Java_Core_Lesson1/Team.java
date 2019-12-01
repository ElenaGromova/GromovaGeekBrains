package Java_Core_Lesson1;

import Java_Core_Lesson1.Members.Cat;
import Java_Core_Lesson1.Members.Competitor;
import Java_Core_Lesson1.Members.Human;
import Java_Core_Lesson1.Members.Robot;

import static Java_Core_Lesson1.MainTest.count;
import static Java_Core_Lesson1.MainTest.jumpMax;
import static Java_Core_Lesson1.MainTest.runMax;

public class Team {
    Competitor[] competitors = new Competitor[count];;

    public Team(){
        for (int i = 0; i < count; i = i + 3) {
            this.competitors[i] = new Human("Human" + i, (int) (Math.random() * (runMax * 2)), (int) (Math.random() * (jumpMax * 2)));
            if((i+1)<count) this.competitors[i + 1] = new Robot("Robot" + i, (int) (Math.random() * (runMax * 2)), (int) (Math.random() * (jumpMax * 2)));
            if ((i+2)<count) this.competitors[i + 2] = new Cat("Cat" + i, (int) (Math.random() * (runMax * 2)), (int) (Math.random() * (jumpMax * 2)));
        }
    }
}
