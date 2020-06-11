package Java_Core_Lesson3_need;

import java.util.*;

class WordSet {

    public static void main(String[] args) {
        String[] arr = {"fds", "saw", "cfds", "cfds"};
        HashMap<String, Integer> hm = new HashMap<>();
        for(String o: arr){
            hm.put(o, hm.getOrDefault(o, 0) + 1);
        }
        System.out.println(hm);
    }


}