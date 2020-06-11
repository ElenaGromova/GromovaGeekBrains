package Java_Core_Proff.lesson1.lesson1_dop;

public class Calculator{

    interface Operationable<T>{
        T calculate(T x, T y);
    }

    public static void main(String[] args) {
        Operationable<String> sum = (x, y)-> x + y;
        Operationable<Double> deduction = (x, y)-> x - y;
        Operationable<Integer> multiplication = (x, y)-> x * y;
        Operationable<Integer> division = (x, y)-> x / y;


        System.out.println(sum.calculate("Задание по ", "лямбде"));
        System.out.println(deduction.calculate(4.0, 2.0));
        System.out.println(multiplication.calculate(3, 5));
        System.out.println(division.calculate(100, 5));
    }
}
