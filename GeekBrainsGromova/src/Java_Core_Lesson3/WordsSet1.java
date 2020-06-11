package Java_Core_Lesson3;

import java.util.*;

class WordsSet1 {
    static ArrayList<String> wordsSet = new ArrayList<>(Arrays.asList("A", "B", "C", "a", "b", "c", "D", "d", "E", "e", "A", "B", "C", "A", "B", "C", "D", "d", "E", "e"));

    static Set getUnicElements(ArrayList<String> wordsSet) {
        return new HashSet<>(wordsSet);
    }

    static int getCountOfElement(Object str){
        int count = 0;
        for (String element: wordsSet)
            count = (element.equals(str)) ? (count+1): count;
        return count;
    }

    public static void main(String[] args) {
        Set unic = getUnicElements(wordsSet);
        System.out.println("Исходный массив слов: " + wordsSet);
        System.out.println("Уникальные элементы исходного массива слов: " + unic);
        for (Object element: unic)
            System.out.println("Элемент исходного массива " + element + " встречается " + getCountOfElement(element) + " разa");
    }
}