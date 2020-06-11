package Java_Core_Lesson3.TelephoneBook;

import java.util.ArrayList;

public class HumanTelephone1 {
    private String name;
    private ArrayList<String> telephoneNumbers = new ArrayList<>();

    HumanTelephone1(String name, ArrayList<String> telephoneNumbers){
        this.name = name;
        this.telephoneNumbers = telephoneNumbers;
    }

    ArrayList getNumbers(){
        return telephoneNumbers;
    }

    String getName(){
        return name;
    }

    String info(){
      return ("ФИО: " + name + " Номера телефонов: " + telephoneNumbers);
    }

}
