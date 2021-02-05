package lesson2;

import java.util.ArrayList;

public class methodsOfArrayList {
    public static void printList(ArrayList<String> arrayList){
        System.out.println("Музыкальные инструменты: ");
        for (String instrument: arrayList){
            System.out.println(instrument);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> instruments = new ArrayList<String>();
        ArrayList<String> instrumentsToAdd = new ArrayList<String>();

        instruments.add("Saxophone");
        instruments.add("Piano");
        instruments.add(2, "Guitar");

        printList(instruments);

        instrumentsToAdd.add("Trumpet");
        instrumentsToAdd.add("Organ");
        instrumentsToAdd.add("Flute");
        instrumentsToAdd.add("Accordion");
        instrumentsToAdd.add("Drums");
        instrumentsToAdd.add("Violin");
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

        printList(instruments);
    }
}
