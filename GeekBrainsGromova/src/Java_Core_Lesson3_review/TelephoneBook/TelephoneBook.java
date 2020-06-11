package Java_Core_Lesson3_review.TelephoneBook;

import java.util.*;

public class TelephoneBook {
    Map<String, Contact> contacts;

    TelephoneBook() {
        this.contacts = new HashMap<String, Contact>();
    }

    public void infoContactsTelephoneBook() {
        for (Contact c : contacts.values()) {
            System.out.println("Запись телефонной книги: " + c.showContact());
        }
    }

    public void addContactObject(Contact contact) {
        if (!contacts.containsKey(contact.getNameContact())) {
            contacts.put(contact.getNameContact(), contact);
        } else {
            System.out.println("Такой контакт уже существует!");
        }
    }

    public void addContactWithData(String name, List<String> phones) {
        if (!contacts.containsKey(name)) {
            contacts.put(name, new Contact(name, phones));
        } else {
            System.out.println("Такой контакт уже существует!");
        }
    }

    void addContactTelephoneByObject(Contact contact, String phone) {
        contact.addPhone(phone);
    }

    public void addContactTelephoneByName(String name, String phone) {
        if (contacts.containsKey(name)) {
            contacts.get(name).addPhone(phone);
        } else {
            System.out.println("Такой контакт не существует!");
        }

    }

    void deleteContactByName(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
        } else {
            System.out.println("Такой контакт не существует!");
        }
    }

    void deleteContactPhoneByObject(Contact contact, String phone) {
        if (contact.getPhonesContact().contains(phone)) {
            contact.deletePhone(phone);
        }
    }

    void deleteContactPhoneByName(String name, String phone) {
        if (contacts.containsKey(name)) {
            contacts.get(name).deletePhone(phone);
        }
    }

    void changeContactNameByName(String lastName, String newName) {
        if (contacts.containsKey(lastName)) {
            Contact newContact = new Contact(newName, contacts.get(lastName).getPhonesContact());
            contacts.put(newContact.getNameContact(), newContact);
            contacts.remove(lastName);
        } else {
            System.out.println("Такой контакт не существует!");
        }
        }



    void changeContactPhoneByObject(Contact contact, String lastPhone, String newPhone) {
        if (contacts.containsKey(contact.getNameContact())) {
            contact.changePhone(lastPhone, newPhone);
        } else {
            System.out.println("Такой контакт не существует!");
        }
    }

    void changeContactPhoneByName(String name, String lastPhone, String newPhone) {
        if (contacts.containsKey(name)) {
            contacts.get(name).changePhone(lastPhone, newPhone);
        } else {
            System.out.println("Такой контакт не существует!");
        }
    }
}




