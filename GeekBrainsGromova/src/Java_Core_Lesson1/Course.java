package Java_Core_Lesson1;

import Java_Core_Lesson1.Members.Competitor;
import Java_Core_Lesson1.Sport_Tests.Obstacle;
import Java_Core_Lesson1.Sport_Tests.Track;
import Java_Core_Lesson1.Sport_Tests.Wall;

import static Java_Core_Lesson1.MainTest.count;
import static Java_Core_Lesson1.MainTest.jumpMax;
import static Java_Core_Lesson1.MainTest.runMax;

public class Course {
    Obstacle[] obstacles = new Obstacle[count];

    public Course(){
        for (int i = 0; i < count; i=i+2){
            this.obstacles[i] = new Track((int) (Math.random() * runMax));
            if ((i+1)<count) this.obstacles[i+1] = new Wall((int) (Math.random() * jumpMax));}
        }

    public void runTournament(Team team){
       for (Competitor t: team.competitors){
            for (Obstacle c: this.obstacles){
                c.doIt(t);
                if (!t.isOnDistance()) break;
            }
        }

        for (Competitor t: team.competitors){
            t.info();
        }
    }
}
