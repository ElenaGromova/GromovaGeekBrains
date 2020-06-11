package Java_Core_Lesson3_need.phonebook;

public class MainClass {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.createContact("Ivanov");
        phoneBook.addPhone("Ivanov", "12345");
        phoneBook.createContact("Sergeev", "343535");
        phoneBook.printContacts();
        System.out.println("============================\n");
        phoneBook.changeContactName("Ivanov", "Petrov");
        phoneBook.printContacts();
        System.out.println("============================\n");
        phoneBook.deleteContact("Petrov");
        phoneBook.printContacts();
        System.out.println("============================\n");
        phoneBook.addPhones("Sergeev", "12345", "5643563", "132342");
        phoneBook.showContact("Sergeev");
        phoneBook.deletePhone("Sergeev", "12345");
        phoneBook.showContact("Sergeev");
        System.out.println("============================\n");
        phoneBook.showContact("Sergeev");
        phoneBook.changePhone("Sergeev", "5643563", "12345");
        phoneBook.showContact("Sergeev");
    }
}
