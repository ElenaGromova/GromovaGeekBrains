package Java_Core_Lesson2_enum;

import java.util.Date;

public class DayOfWeek {
    public enum DaysOfWeek {

        MONDAY(40),
        TUESDAY(32),
        WEDNESDAY(24),
        THURSDAY(16),
        FRIDAY(8),
        SATURDAY(0),
        SUNDAY(0);

        int hours;

        DaysOfWeek(int hours) {
            this.hours = hours;
        }

        int getWorkingHours(DaysOfWeek d) {
            return d.hours;
        }
    }

    public static void main(String[] args) {
        for(DaysOfWeek d : DaysOfWeek.values())
            System.out.println(d + " " + "до конца рабочей недели осталось " + d.getWorkingHours(d) + " рабочих часов");
    }
}
