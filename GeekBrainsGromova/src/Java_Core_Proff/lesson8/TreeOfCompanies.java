package Java_Core_Proff.lesson8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeOfCompanies {
    static List<Integer> idList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    static List<Integer> parentIdList = Arrays.asList(0, 6, 1, 0, 3, 5, 2, 1, 10, 4, 4, 3, 0, 1, 3);
    static List<String> companiesList = Arrays.asList("company1", "company2", "company3", "company4", "company5", "company6", "company7", "company8",
                                                      "company9", "company10", "company11", "company12", "company13", "company14", "company15");

    public static ArrayList<Integer> chooseChild(int parent){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < parentIdList.size(); i++){
            if (parentIdList.get(i) == parent){
                list.add(idList.get(i));
            }
        }
        return list;
    }

    public static ArrayList<Integer> buildBranch(int parent, ArrayList<Integer> done){
        ArrayList<Integer> list = new ArrayList<>();
        list = chooseChild(parent);
        System.out.print(" " + companiesList.get(parent-1) + "---ветка прямых дочек----");
        for (int i = 0; i < list.size(); i++){
            System.out.print("  " + companiesList.get(list.get(i) - 1));
        }
        System.out.println("");
        for (int i = 0; i < list.size(); i++){
            buildBranch(list.get(i), done);
        }

        return done;
    }

    public static void main(String[] args) {
        int parent, id;
        ArrayList<Integer> done = new ArrayList<>(); //список перебранных элементов
        ArrayList<Integer> toDo = new ArrayList<>();

        //ищем верхний элементы дерева
        parent = 0;
        while (!parentIdList.contains(parent)){
            parent++;
        }

        for (int i = 0; i < parentIdList.size(); i++){
            if (parentIdList.get(i) == parent) {
                toDo.add(i+1);
                done.add(i+1);
            }
        }

        for (int i = 0; i < toDo.size(); i++){
            System.out.println("===========================================================================");
            System.out.println("Следующая ветка первого уровня");
            buildBranch(toDo.get(i), done);
        }

    }
}
