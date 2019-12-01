package Java_Core_Lesson1.Sport_Tests;

import Java_Core_Lesson1.Members.Competitor;

public class Wall extends Obstacle {
    int height;

    public Wall(int height){
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor){
        competitor.jump(height);
    }
}
