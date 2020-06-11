package Java_Core_Lesson3_review.TelephoneBook;

import java.util.List;

public class Contact {
    private static String nameContact;
    private static List<String> phonesContact;

    Contact(String nameContact, List<String> phonesContact){
        this.nameContact = nameContact;
        this.phonesContact = phonesContact;
    }

    String getNameContact(){
        return nameContact;
    }

    List<String> getPhonesContact(){
        return phonesContact;
    }

    void addPhone(String str){
        if (!phonesContact.contains(str)) {
            phonesContact.add(str);
        }
        else {
            System.out.println("Такой телефон уже существует!");
        }
    }

    void changePhone(String lastPhone, String newPhone){
        if(phonesContact.contains(lastPhone)) {
            phonesContact.set(phonesContact.indexOf(lastPhone), newPhone);
        } else {
            System.out.println("Такой телефон не существует!");
        }
    }

    void deletePhone(String str){
        if(phonesContact.contains(str)) {
            phonesContact.remove(str);
        } else {
            System.out.println("Такой телефон не существует!");
        }
    }

    String showContact(){
        return ("Запись телефонной книги: " + nameContact + ": номера телефонов " + phonesContact);
    }

    void changeNameContact(String str){
        this.nameContact = str;
    }

}
