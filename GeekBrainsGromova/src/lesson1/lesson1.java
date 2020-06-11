package lesson1;

import java.util.Scanner;

public class lesson1 {
    public static void main(String[] args){
        byte t = 10;
        short s = 2404;
        int i = 123456;
        long l = 1500L;
        float f = 120.0f;
        double k = 15.72775;
        boolean bool = true;
        char r = 'A';

        int a = readIntArgument("Введите целое число a: ");
        int b = readIntArgument("Введите целое число b: ");
        int c = readIntArgument("Введите целое число c: ");
        int d = readIntArgument("Введите целое число d: ");

        System.out.println("Значение выражения a * (b + (c / d)) = " + getMath(a, b, c, d));

        String resultSum = (getResult(a, b)) ? "Сумма a и b входит в интервал [10, 20]" : "Сумма a и b не входит в интервал [10, 20]";
        System.out.println(resultSum);

        System.out.println("Результат анализа введенного вами часла а: " + checkChislo(a));

        String resultZnak = (checkZnak(a)) ? "Число а отрицательное!" : "Число а положительное!";
        System.out.println(resultZnak);

        String name = readStringArgument("Введите ваше имя: ");
        writePrivet(name);

        int year = readIntArgument("Введите год: ");
        checkYear(year);
    }

    static float getMath(int a, int b, int c, int d){
        return a*(b + Math.round(c/d));
    }

    static int readIntArgument(String userText){
        Scanner userInput = new Scanner(System.in);
        System.out.print(userText);
        int argument = userInput.nextInt();
        return argument;
    }

    static String readStringArgument(String userText){
        Scanner userInput = new Scanner(System.in);
        System.out.print(userText);
        String argument = userInput.nextLine();
        return argument;
    }

    static boolean getResult(int a, int b){
        boolean res = true;
        if ((a + b)<10 || (a + b)>20) res = false;
        return res;
    }

    static String checkChislo(int a){
        String chislo = (a>=0) ? "Число а положительное!" : "Число а отрицательное!";
        return chislo;
    }

    static boolean checkZnak(int a){
        return (a < 0);
    }

    static void writePrivet(String name){
        System.out.println("Привет, " + name + "!");
    }

    static void checkYear(int year){
        if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
            System.out.println("Год високосный");
        else System.out.println("Год не високосный");
    }

}
