package lesson2;

import java.util.ArrayList;
import java.util.LinkedList;

public class methodsOfLinkedList {
    public static void printList(LinkedList<String> linkedList){
        System.out.println("Музыкальные инструменты: ");
        for (String instrument: linkedList){
            System.out.println(instrument);
        }
    }

    public static void main(String[] args) {
        LinkedList<String> instruments = new LinkedList<String>();
        LinkedList<String> instrumentsToAdd = new LinkedList<String>();

        instruments.addFirst("Saxophone");
        instruments.add("Piano");
        instruments.add(2, "Guitar");

        printList(instruments);

        instrumentsToAdd.add("Trumpet");
        instrumentsToAdd.add("Organ");
        instrumentsToAdd.add("Flute");
        instrumentsToAdd.add("Accordion");
        instrumentsToAdd.add("Drums");
        instrumentsToAdd.addLast("Violin");
        instruments.addAll(instrumentsToAdd);

        printList(instruments);

        instruments.set(7, "Trombone");

        printList(instruments);

        if (instruments.contains("Saxophone")) {
            System.out.println("В списке присутствует инструмент: " + instruments.get(instruments.indexOf("Saxophone")));
        }

        if (instruments.indexOf("Piano")==1) {
            System.out.println("Piano - это номер 1 в списке");
        }

        instruments.remove(8);
        instruments.removeFirst();
        instruments.removeLast();

        printList(instruments);
    }
}
