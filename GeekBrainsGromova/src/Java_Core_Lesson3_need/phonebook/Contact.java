package Java_Core_Lesson3_need.phonebook;

import java.util.HashSet;
import java.util.Set;

class Contact {
    private String name;
    private Set<String> phonesContact;

    Contact(String name) {
        this.name = name;
        phonesContact = new HashSet<>();
    }

    Contact(String name, String phone) {
        this(name);
        phonesContact.add(phone);
    }

    String getName() {
        return name;
    }

    boolean addPhone(String phone) {
        boolean added = phonesContact.add(phone);
        if (!added) {
            System.out.println("Номер уже существует");
        }
        return added;
    }

    void changePhone(String oldPhone, String newPhone) {
        boolean phoneRemoved = deletePhone(oldPhone);
        addPhone(newPhone);
        if (!phoneRemoved) {
            System.out.printf("Номер %s не найден.%n", oldPhone);
        }
    }

    boolean deletePhone(String str) {
        return phonesContact.remove(str);
    }

    void showContact() {
        System.out.println(this);
    }

    void changeContactName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Контакт: ");
        sb.append(name);
        for (String phone : phonesContact) {
            sb.append("\n----> ");
            sb.append(phone);
        }
        sb.append("\n");
        return sb.toString();
    }
}
