package Java_Core_Lesson3;

import java.util.*;

class WordsSet2 {

    private static List<String> initList(){
        return Arrays.asList("A", "B", "C", "a", "b", "c", "D", "d", "E", "e", "A", "B", "C", "A", "B", "C", "D", "d", "E", "e");
    }

    private static Map<String, Integer> initMap(Collection<String> strings){
        Map<String, Integer> result = new HashMap<>();
        for (String s: strings) {
            result.merge(s, 1, Integer::sum);
        }
        return result;
    }

    private static void getCountOfElements(Map<String, Integer> map) {
        for (String s : map.keySet()) {
            System.out.println("Элемент исходного массива " + s + " встречается " + map.get(s) + " разa");
        }
    }

    public static void main(String[] args) {
        List<String> wordsList = initList();
        Map<String, Integer> wordsMap = initMap(wordsList);
        Set<String> uniqWords = wordsMap.keySet();
        System.out.println("Исходный массив слов: " + wordsList);
        System.out.println("Уникальные элементы исходного массива слов: " + uniqWords);
        getCountOfElements(wordsMap);
        }
    }

