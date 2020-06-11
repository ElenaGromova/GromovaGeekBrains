package Java_Core_Lesson3_need.phonebook;

import java.util.*;

class PhoneBook {
    private Map<String, Contact> contacts;

    PhoneBook() {
        this.contacts = new TreeMap<>();
    }

    Contact createContact(String name) {
        if (contacts.containsKey(name)) {
            throw new IllegalArgumentException("Contact exists");
        }
        Contact contact = new Contact(name);
        contacts.putIfAbsent(name, contact);
        return contact;
    }

    void createContact(String name, String phone) {
        Contact contact = createContact(name);
        contact.addPhone(phone);
    }

    void addPhones(String name, String... phones) {
        contacts.computeIfPresent(name, (key, value) -> {
            for (String phone : phones) {
                value.addPhone(phone);
            }
            return value;
        });
    }

    void addPhone(String name, String phone) {
        Contact contact = contacts.get(name);
        if (contact != null) {
            contact.addPhone(phone);
        } else {
            System.out.println("Контакт не найден");
        }
    }

    void deleteContact(String name) {
        contacts.remove(name);
    }

    void deletePhone(String name, String phone) {
        Contact contact = contacts.get(name);
        if (contact != null) {
            contact.deletePhone(phone);
        }
    }

    void changeContactName(String oldName, String newName) {
        Contact contact = contacts.remove(oldName);
        if (contact != null) {
            contact.changeContactName(newName);
            contacts.put(newName, contact);
        }
    }

    void changePhone(String name, String oldPhone, String newPhone) {
        Contact contact = contacts.get(name);
        if (contact != null) {
            contact.deletePhone(oldPhone);
            contact.addPhone(newPhone);
        }
    }

    void showContact(String name) {
        Contact contact = contacts.get(name);
        if (contact != null) {
            contact.showContact();
        }
    }

    void printContacts() {
        for (Contact contact : contacts.values()) {
            contact.showContact();
        }
    }
}