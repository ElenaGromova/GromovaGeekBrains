package Java_Core_Lesson4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPassword {
    private static Scanner scanner = new Scanner(System.in);
    private static String userPassword;
    private static boolean result;

    public static void main(String[] args) {
        System.out.println("Требования к паролю:" + "\n" +
                            "1. Пароль должен быть не менее 8ми символов" + "\n" +
                            "2. В пароле должно быть число" + "\n" +
                            "3. В пароле должна быть хотя бы 1 строчная буква" + "\n" +
                            "4. В пароле должна быть хотя бы 1 заглавная буква" + "\n" +
                            "5. Не должно быть пробелов");

        do {
            System.out.println("Введите ваш пароль:");
            userPassword = scanner.nextLine(); // Считывание пароля пользователя

            result = checkPassword(userPassword);
        }
        while (!result);
    }

    private static boolean checkPassword(String str){
        Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(\\S){8,16}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
