package Java_Core_Lesson3_need.TelephoneBook;

import java.util.*;

public class MainPhoneBook {

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        book.add("injn", "456");
        book.add("edw", "456");
        book.add("injn", "425869");
        book.add("jnuy", "456");

        book.findString("injn");
        book.findString("injn11");
    }
}
