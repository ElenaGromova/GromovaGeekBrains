package Java_Core_Lesson1.Sport_Tests;

import Java_Core_Lesson1.Members.Competitor;

public class Track extends Obstacle {
    int length;

    public Track(int length){
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor){
        competitor.run(length);
    }
}
