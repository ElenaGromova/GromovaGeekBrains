package Java_Core_Lesson3_review.TelephoneBook;

import java.util.*;

public class MainTelephoneBook {

    public static void main(String[] args) {
          TelephoneBook telephoneBook = new TelephoneBook();
          Contact contact1 = new Contact("Ivanov", new ArrayList<String>(Arrays.asList("88000000001", "88000000002")));
          Contact contact2 = new Contact("Petrov", new ArrayList<String>(Arrays.asList("88000000003", "88000000004", "88000000002")));
          Contact contact3 = new Contact("Sidorov", new ArrayList<String>(Arrays.asList("88000000004", "88000000005")));
          telephoneBook.addContactObject(contact1);
          telephoneBook.addContactObject(contact2);
          telephoneBook.addContactObject(contact3);
          //telephoneBook.infoContactsTelephoneBook();
          telephoneBook.addContactWithData("Gromova", new ArrayList<String>(Arrays.asList("88000000004", "88000000005")));
          telephoneBook.infoContactsTelephoneBook();
          telephoneBook.addContactTelephoneByName("Gromova", "88000000006");
          telephoneBook.addContactTelephoneByObject(telephoneBook.contacts.get("Gromova"), "88000000007");
          telephoneBook.infoContactsTelephoneBook();

        telephoneBook.addContactTelephoneByName("Gromova1", "88000000006");
        //telephoneBook.addContactTelephoneByObject(telephoneBook.contacts.get("Gromova1"), "88000000007");
        telephoneBook.infoContactsTelephoneBook();

        telephoneBook.deleteContactByName("Ivanov");
        telephoneBook.infoContactsTelephoneBook();

        telephoneBook.deleteContactPhoneByObject(telephoneBook.contacts.get("Gromova"), "88000000006");
        telephoneBook.infoContactsTelephoneBook();

        telephoneBook.deleteContactPhoneByName("Gromova", "88000000007");
        telephoneBook.infoContactsTelephoneBook();

        telephoneBook.changeContactNameByName("Gromova", "GromovaElena");
        telephoneBook.infoContactsTelephoneBook();

        telephoneBook.changeContactPhoneByObject(telephoneBook.contacts.get("GromovaElena"), "88000000004", "88000000009");
        telephoneBook.infoContactsTelephoneBook();

        telephoneBook.changeContactPhoneByName("GromovaElena", "88000000005", "88000000009");
        telephoneBook.infoContactsTelephoneBook();
    }
}
