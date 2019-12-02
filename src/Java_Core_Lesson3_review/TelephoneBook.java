package Java_Core_Lesson3_review;

import java.util.*;

public class TelephoneBook {

    private static List<String> initList(){
        return Arrays.asList("Ivanov", "Petrov", "Petrov2", "Sidorov");
        //new ArrayList<>Arrays.asList(arr)
    }

    private static Map<String, List<String>> initBook() {
        Map<String, List<String>> map = new TreeMap<>();
        map.put("Ivanov", Arrays.asList("88000000001", "88000000002"));
        map.put("Petrov", Arrays.asList("88000000003", "88000000004", "88000000002"));
        map.put("Sidorov", Arrays.asList("88000000004", "88000000005"));
        return map;
    }

    private static void phoneBookInfo(Map<String, List<String>> map) {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println("Запись телефонной книги: " + entry.getKey() + ": номера телефонов " + entry.getValue());
        }
    }

    private static void getPhone(Map<String, List<String>> map, List<String> fio) {
        Iterator<String> iter = fio.iterator();
        while (iter.hasNext()) {
            String str = iter.next();

            //System.out.println( (map.containsKey(str)) ? ("Номера телефонов человека по фамилии: " + str + " " + map.get(str)) : ("Такого человека в справочнике нет"));
            if (map.containsKey(str)) {
                System.out.println("Номера телефонов человека по фамилии: " + str + " " + map.get(str));
            } else {
                System.out.println("Такого человека в справочнике нет");
            }
        }
    }

    public static void main(String[] args) {
        List<String> fioList = initList();
        Map<String, List<String>> phoneBook = initBook();
        phoneBookInfo(phoneBook);
        getPhone(phoneBook, fioList);
    }
}



