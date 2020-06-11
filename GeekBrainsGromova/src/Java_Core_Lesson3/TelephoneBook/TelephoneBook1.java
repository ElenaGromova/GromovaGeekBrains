package Java_Core_Lesson3.TelephoneBook;

import java.util.ArrayList;
import java.util.Arrays;

public class TelephoneBook1 {
    static ArrayList<HumanTelephone1> humanTelephones = new ArrayList<HumanTelephone1>();

    static void addPhone(){
        ArrayList<String> phone1 = new ArrayList<>(Arrays.asList("88000000001", "88000000002"));
        HumanTelephone1 ivanov = new HumanTelephone1("Ivanov", phone1);
        humanTelephones.add(ivanov);

        ArrayList<String> phone2 = new ArrayList<>(Arrays.asList("88000000003", "88000000004", "88000000002"));
        HumanTelephone1 petrov = new HumanTelephone1("Petrov", phone2);
        humanTelephones.add(petrov);

        ArrayList<String> phone3 = new ArrayList<>(Arrays.asList("88000000004", "88000000005"));
        HumanTelephone1 sidorov = new HumanTelephone1("Sidorov", phone3);
        humanTelephones.add(sidorov);
    }

    static ArrayList getPhone(String name){
        ArrayList<String> noTelephone = new ArrayList<>(Arrays.asList("Такого человека в справочнике нет"));
        for (int i=0; i < humanTelephones.size(); i++) {
            if (humanTelephones.get(i).getName().equals(name)){
                return humanTelephones.get(i).getNumbers();
            }
        }
        return noTelephone;
    }

    public static void main(String[] args) {
        addPhone();
        for (int i=0; i < humanTelephones.size(); i++)
            System.out.println("Запись телефонной книги: " + humanTelephones.get(i).info());

        System.out.println("номера телефонов человека по фамилии Ivanov: " + getPhone("Ivanov"));
        System.out.println("номера телефонов человека по фамилии Sidorov: " + getPhone("Sidorov"));
        System.out.println("номера телефонов человека по фамилии Petrov: " + getPhone("Petrov"));
        System.out.println("номера телефонов человека по фамилии Petrov1: " + getPhone("Petrov1"));
    }
}
