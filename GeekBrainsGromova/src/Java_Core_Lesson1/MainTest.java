package Java_Core_Lesson1;

import Java_Core_Lesson1.Members.Cat;
import Java_Core_Lesson1.Members.Competitor;
import Java_Core_Lesson1.Members.Human;
import Java_Core_Lesson1.Members.Robot;
import Java_Core_Lesson1.Sport_Tests.Obstacle;
import Java_Core_Lesson1.Sport_Tests.Track;
import Java_Core_Lesson1.Sport_Tests.Wall;

public class MainTest {

    static int count = 6;
    static int runMax = 300; //максимальная длина дорожки
    static int jumpMax = 30; //максимальная высота стены

    public static void main(String[] args){
        Course courses = new Course();
        Team team = new Team();
        courses.runTournament(team);
    }
}
