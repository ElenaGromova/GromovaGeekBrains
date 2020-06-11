package lesson3.чужое;

import java.util.Random;
import java.util.Scanner;

public class bn{

    /* Р±Р»РѕРє РЅР°СЃС‚СЂРѕРµРє*/

    private static char[][] map;
    private static int SIZE = 3;
    private final static char DOT_X = 'x';
    private final static char DOT_O = '0';
    private final static char DOT_EMPTY = '-';
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static final boolean SILLY_MODE = false;
    private static int [][] ratingArray = new int[SIZE][SIZE];

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();// С…РѕРґ С‡РµР»РѕРІРµРєР°
            if (isEndGame(DOT_X)) {
                break;
            }

            computerTurn();//С…РѕРґ РєРѕРјРїСЊСЋС‚РµСЂР°
            if (isEndGame(DOT_O)) {
                break;
            }

        }
        System.out.println("РРіСЂР° Р·Р°РєРѕРЅС‡РµРЅР°");

    }

    String Test(char ... arr){
      return "or";
    }
    //РѕРїСЂРµРґРµР»РёРј СЃРѕСЃС‚РѕСЏРЅРёРµ РїРѕР»СЏ
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    //РїРµС‡Р°С‚СЊ РїРѕР»СЏ РЅР° СЌРєСЂР°РЅ
    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " "); //РІС‹РІРѕРґ РЅРѕРјРµСЂР° СЃС‚РѕР»Р±С†Р°
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // РІС‹РІРѕРґ РЅРѕРјРµСЂР° СЂСЏРґР°
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    // С…РѕРґ С‡РµР»РѕРІРµРєР°
    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Р’РІРµРґРёС‚Рµ РєРѕРѕСЂРґРёРЅР°С‚С‹ С…РѕРґР° С‡РµСЂРµР· РїСЂРѕР±РµР» РѕС‚ 1 РґРѕ 3");
            y = scanner.nextInt() - 1; // РЅРѕРјРµСЂ СЃС‚СЂРѕРєРё
            x = scanner.nextInt() - 1; //РЅРѕРјРµСЂ СЃС‚РѕР»Р±С†Р°
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;

    }

    private static void computerTurn(){
        int x = 0;
        int y = 0;
        if (SILLY_MODE) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
            map[y][x] = DOT_O;
            System.out.println("РљРѕРјРїСЊСЋС‚РµСЂ СЃРґРµР»Р°Р» С…РѕРґ СЃ РєРѕРѕСЂРґРёРЅР°С‚РѕР№ " + (y + 1) + " " + (x + 1));
        } else{  //логика умного ПК
            int count = 0;
            String str = " ";

            for (int i = 0; i < SIZE ; i++) {
                if (str.equals("Победа компьютера"))
                    break;
                for (int j = 0; j < SIZE ; j++) {

                    if (map[i][j] == DOT_EMPTY) {
                        count = 0;
                        if (isWinComputer(i,j)) { //проверка победы ПК
                            y = i;
                            x = j;
                            str = "Победа компьютера";
                            break;
                        } else  if (isWinHuman(i,j)){   //проверка побуды человека в точке
                            y = i;
                            x = j;
                            count = 10;               //ставим рейтинг клетки 10
                        } else{    /* вычислим рейтинг клетки. Максимальное значение = 8.
                        создадим массив-окружение клетки createsRatingArray(i,j)*/



                            int[][] round = {{i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1},

                                    {i, j - 1},                  {i, j + 1},

                                    {i + 1, j - 1}, {i + 1, j}, {i + 1, j + 1}};
                            for (int[] check : round) {
                                y = check[0];
                                x = check[1];
                                if (isCellValidForRating(x, y) && map[y][x] == DOT_O)//подсчет 0 в соседних клетках
                                    count++;
                            }
                        }

                    }else {//если клетка не пустая, то рейтинг  -1
                        count = -1;
                    }
                    ratingArray[i][j] = count; //заполняем массив рейтинг каждой клетки
                }
            }
            if(str == "Победа компьютера" ){
                map[y][x] = DOT_O;
                System.out.println("Компьютер сделал ход с кординатой " + (y + 1) + " " + (x + 1));
            } else {
                //поиск максимального значения в массиве - рейтинг к
                int ratingMax = 0;
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (ratingArray[i][j] >= ratingMax) {
                            ratingMax = ratingArray[i][j];
                            y = i;
                            x = j;
                        }
                    }
                }
                map[y][x] = DOT_O;
                System.out.println("Компьютер сделал ход с кординатой " + (y + 1) + " " + (x + 1));
            }
        }
    }


    private static boolean isCellValidForRating(int x, int y) {
        boolean result = true;
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }
        return result;
    }


    private static boolean isCellValid(int x, int y) {
        boolean result = true;
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            System.err.println("Неверные координаты");
            result = false;
        } else if (map[y][x] != DOT_EMPTY) {
            result = false;

        }
        return result;
    }


    private static boolean isEndGame(char playerSymbol) {
        boolean result = false;
        printMap();
        if (isWin(playerSymbol)) {
            System.out.println("Победили " + playerSymbol);
            result = true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            result = true;
        }
        return result;
    }
    private static boolean isWinComputer(int i, int j){
        boolean result = false;
        map[i][j] = DOT_O;
        if(isWin(DOT_O)){
            result = true;
        }
        map[i][j] = DOT_EMPTY;
        return result;
    }

    private static boolean isWinHuman(int i, int j){
        boolean result = false;
        map[i][j] = DOT_X;
        if(isWin(DOT_X)){
            result = true;
        }
        map[i][j] = DOT_EMPTY;
        return result;
    }

    private static boolean isWin(char playerSymbol){
        boolean result1 = false;
        /*if (    map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol ||
                map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol ||
                map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol ||
                map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol ||
                map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol ||
                map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol ||
                map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol ||
                map[0][2] == playerSymbol && map[1][1] == playerSymbol && map[2][0] == playerSymbol){

            result1 = true;
        }*/
        int winSting = 0;
        int winColumn = 0;
        int windDiagonal1 = 0;
        int windDiagonal2 = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                winSting += (map[i][j] == playerSymbol)? 1:0;
                winColumn += (map[j][i] == playerSymbol)? 1:0;
                windDiagonal1 += (map[j][j] == playerSymbol)? 1:0;
                windDiagonal2 += (map[j][SIZE - 1 - j] == playerSymbol)? 1:0;
            }
            if (winSting == SIZE || winColumn == SIZE || windDiagonal1 == SIZE || windDiagonal2 == SIZE) {
                result1 = true;
                return result1;
            }
            winSting = 0;
            winColumn = 0;
            windDiagonal1 = 0;
            windDiagonal2 = 0;

        }
        return result1;
    }
    private static boolean isMapFull(){
        boolean result = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE ; j++) {
                if(map[i][j] == DOT_EMPTY) {
                    result = false;
                    break;
                }

            }
        }
        return result;
    }


}
