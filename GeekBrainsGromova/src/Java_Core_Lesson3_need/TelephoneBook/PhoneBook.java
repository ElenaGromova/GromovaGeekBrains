package Java_Core_Lesson3_need.TelephoneBook;

import java.util.*;

public class PhoneBook {
    HashMap<String, HashSet<String>> hm;

    PhoneBook(){
        this.hm = new HashMap<>();
    }

    public void add(String name, String phone){
        HashSet<String> hs = hm.getOrDefault(name, new HashSet<>());
        hs.add(phone);
        hm.put(name, hs);
    }

    public void findString(String name){
        if (hm.containsKey(name)){
            System.out.println(hm.get(name));
        } else {
            System.out.println("no such contact");
        }
    }

}




