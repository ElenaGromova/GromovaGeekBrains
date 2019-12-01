import java.net.SocketPermission;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] map; //карта игры
    private static int SIZE = 3; //размерность поля

    private static final char DOT_EMPTY = '*'; //пустой символ
    private static final char DOT_X = 'X'; //крестик
    private static final char DOT_O = 'O'; //нолик

    private static final boolean SILLY_MODE = false;

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        //byte kol = 0;
        initMap();
        printMap();

        while (true){
            humanTurn(); //ход человека
            if (isEndGame(DOT_X))
                    break;

           computerTurn();// ход компьютера
            if (isEndGame(DOT_O))
                break;
        }

        System.out.println("Игра закончена");
    }

    //Метод подготовки игрового поля
    private static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    //вывод пустого поля игры
    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //ход человека
    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты ячейки (X Y)");
            y = scanner.nextInt() - 1; // Считывание номера строки
            x = scanner.nextInt() - 1; // Считывание номера столбца
        }
        while(!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    //проверка принадлежности ячейки к диагонали главной
    private static boolean diagonalMainCell(int x, int y){
        return(x==y) ? true : false;
    }

    //проверка принадлежности ячейки к диагонали побочной
    private static boolean diagonalElseCell(int x, int y){
        return(y == (SIZE - x - 1)) ? true : false;
    }

    // проверка центарльной ячейки
    private static int sumReitingEmpty(int i, int j){
        int score = 0;
        score = (map[i - 1][j] == DOT_EMPTY) ? (score + 1) : score;
        score = (map[i][j + 1] == DOT_EMPTY) ? (score + 1) : score;
        score = (map[i - 1][j + 1] == DOT_EMPTY) ? (score + 1) : score;
        score = (map[i + 1][j] == DOT_EMPTY) ? (score + 1) : score;
        score = (map[i][j - 1] == DOT_EMPTY) ? (score + 1) : score;
        score = (map[i + 1][j + 1] == DOT_EMPTY) ? (score + 1) : score;
        score = (map[i - 1][j - 1] == DOT_EMPTY) ? (score + 1) : score;
        score = (map[i + 1][j - 1] == DOT_EMPTY) ? (score + 1) : score;
        System.out.println("score " + score);

        return score;
    }

    //запись рейтинга ячейки
    private static int[] writeReiting(int score, int i, int j){
        int[] array = new int [3];
        array[0] = score;
        array[1] = i;
        array[2] = j;
        return array;
    }

    //поиск элемента с максимальным рейтингом и возврат номера строки
    private static int maxReiting(int [][] arr){
        int max = 0;
        int maxElementNumber = -1;
        for (int i = 0; i < SIZE*SIZE; i++) {
            if (arr[i][1] > max) {
                max = arr[i][0];
                maxElementNumber = i;
            }
        }
        System.out.println(" maxElementNumber " + maxElementNumber);
        return maxElementNumber;
    }

    //проверка двух Х или О на направлениях победы
    private static int[] checkWinCombinations(char playerSymdol){
        int[] arr = new int[2];
        int[] arr1 = {-1, -1};
        int scoreEmpty = 0;
        int scoreSymbol = 0;

        //проверяем есть ли два символа + пустая клетка в строках
        for(int i = 0; i < SIZE; i++) {
            scoreSymbol = 0;
            scoreEmpty = 0;
            for (int j = 0; j < SIZE; j++) {
                scoreSymbol = map[i][j] == playerSymdol ? (scoreSymbol + 1) : scoreSymbol;
                scoreEmpty = map[i][j] == DOT_EMPTY ? (scoreEmpty + 1) : scoreEmpty;
                if (map[i][j] == DOT_EMPTY) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }
        if (scoreEmpty==1 && scoreSymbol==(SIZE-1)) {
            return arr;
        }

        //проверяем есть ли два символа + пустая клетка в столбцах
        for(int j = 0; j < SIZE; j++) {
            scoreSymbol = 0;
            scoreEmpty = 0;
            for (int i = 0; i < SIZE; i++) {
                scoreSymbol = map[i][j] == playerSymdol ? (scoreSymbol + 1) : scoreSymbol;
                scoreEmpty = map[i][j] == DOT_EMPTY ? (scoreEmpty + 1) : scoreEmpty;
                if (map[i][j] == DOT_EMPTY) {
                    arr[0] = i;
                    arr[1] = j;
                }
            }
        }
        if (scoreEmpty==1 && scoreSymbol==(SIZE-1)) {
            return arr;
        }

        //проверяем есть ли два символа + пустая клетка по главной диагонали
        for(int i = 0; i < SIZE; i++) {
            scoreSymbol = 0;
            scoreEmpty = 0;
            for (int j = 0; j < SIZE; j++) {
                if (i == j) {
                    scoreSymbol = map[i][j] == playerSymdol ? (scoreSymbol + 1) : scoreSymbol;
                    scoreEmpty = map[i][j] == DOT_EMPTY ? (scoreEmpty + 1) : scoreEmpty;
                    if (map[i][j] == DOT_EMPTY) {
                        arr[0] = i;
                        arr[1] = j;
                    }
                }
            }
        }
        if (scoreEmpty==1 && scoreSymbol==(SIZE-1)) {
            return arr;
        }

        //проверяем есть ли два символа + пустая клетка по побочной диагонали
        for(int i = 0; i < SIZE; i++) {
            scoreSymbol = 0;
            scoreEmpty = 0;
            for (int j = 0; j < SIZE; j++) {
                if (j == (SIZE - i - 1)) {
                    scoreSymbol = map[i][j] == playerSymdol ? (scoreSymbol + 1) : scoreSymbol;
                    scoreEmpty = map[i][j] == DOT_EMPTY ? (scoreEmpty + 1) : scoreEmpty;
                    if (map[i][j] == DOT_EMPTY) {
                        arr[0] = i;
                        arr[1] = j;
                    }
                }
            }
        }
        if (scoreEmpty==1 && scoreSymbol==(SIZE-1)) {
            return arr;
        }
        return arr1;
    }

    //проверка количества ответов и пустых ячеек в направлении победы
    private static int[][] reitingCell(){
        int score = 0;
        byte n = 0;
        int[] array = new int[2];
        int[][] reiting = new int[SIZE*SIZE][3];

        array = checkWinCombinations(DOT_O);
        if ((array[0] != -1) && (array[1] != -1)) {
            reiting[0][0] = SIZE;
            reiting[0][1] = array[0];
            reiting[0][2] = array[1];
            return reiting;
        }

        array = checkWinCombinations(DOT_X);
        if ((array[0] != -1) && (array[1] != -1)) {
            reiting[0][0] = SIZE;
            reiting[0][1] = array[0];
            reiting[0][2] = array[1];
            return reiting;
        }

        //проверяем рейтинг пустых ячеек
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++) {
                score = 0;
                //System.out.println("Строка " + i + "Столбец " + j);
                if (map[i][j] == DOT_EMPTY) {
                    //System.out.println("i " + i + "j " + j + " empty");
                    if (i == 0) {
                        if (j == 0) {
                            score = (map[i + 1][j] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i][j + 1] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i + 1][j + 1] == DOT_EMPTY) ? (score + 1) : score;
                            //System.out.println("i=" + i + "j= " + j + "score " + score);
                        } else if (j == (SIZE - 1)) {
                            score = (map[i + 1][j] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i][j - 1] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i + 1][j - 1] == DOT_EMPTY) ? (score + 1) : score;
                            //System.out.println("i=" + i + "j= " + j + "score " + score);
                        } else {
                            score = (map[i + 1][j] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i][j - 1] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i][j + 1] == DOT_EMPTY) ? (score + 1) : score;
                            //System.out.println("i=" + i + "j= " + j + "score " + score);
                        }
                    }
                    else
                        if (i == (SIZE - 1)) {
                            if (j == 0) {
                                score = (map[i][j + 1] == DOT_EMPTY) ? (score + 1) : score;
                                score = (map[i - 1][j] == DOT_EMPTY) ? (score + 1) : score;
                                score = (map[i - 1][j + 1] == DOT_EMPTY) ? (score + 1) : score;
                                //System.out.println("i=" + i + "j= " + j + "score " + score);
                            } else if (j == (SIZE - 1)) {
                                score = (map[i][j - 1] == DOT_EMPTY) ? (score + 1) : score;
                                score = (map[i - 1][j] == DOT_EMPTY) ? (score + 1) : score;
                                score = (map[i - 1][j - 1] == DOT_EMPTY) ? (score + 1) : score;
                                //System.out.println("i=" + i + "j= " + j + "score " + score);
                            }  else {
                                score = (map[i - 1][j] == DOT_EMPTY) ? (score + 1) : score;
                                score = (map[i][j - 1] == DOT_EMPTY) ? (score + 1) : score;
                                score = (map[i][j + 1] == DOT_EMPTY) ? (score + 1) : score;
                                //System.out.println("i=" + i + "j= " + j + "score " + score);
                            }
                    }
                    else
                        if ((i != 0) && (i != (SIZE-1)) && (j == 0)) {
                            score = (map[i][j + 1] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i - 1][j] == DOT_EMPTY) ? (score + 1) : score;
                            score = (map[i + 1][j] == DOT_EMPTY) ? (score + 1) : score;
                            //System.out.println("i=" + i + "j= " + j + "score " + score);
                        }
                            else
                                if ((i != 0) && (i != (SIZE - 1)) && (j ==(SIZE - 1))) {
                                    score = (map[i][j - 1] == DOT_EMPTY) ? (score + 1) : score;
                                    score = (map[i - 1][j] == DOT_EMPTY) ? (score + 1) : score;
                                    score = (map[i + 1][j] == DOT_EMPTY) ? (score + 1) : score;
                                    //System.out.println("i=" + i + "j= " + j + "score " + score);
                                }
                                else {
                                score = sumReitingEmpty(i, j);
                                //System.out.println("i=" + i + "j= " + j + "score " + score);
                                }
                }
               reiting[n] = writeReiting(score, i , j);
                n++;
            }
        }

        for (int i = 0; i < SIZE*SIZE; i++){
            for (int j= 0; j < 3; j++)
                System.out.print(reiting[i][j] + " ");
            System.out.println();
        }
        return reiting;
        }

    //ход компьютера
    private static void computerTurn(){
        //int x = -1;
        //int y = -1;
        int x, y;
        int[][] reitingRes = new int[SIZE*2][3];
        if(SILLY_MODE){
            do {
                x = random.nextInt(SIZE - 1);
                y = random.nextInt(SIZE - 1);
            } while(!isCellValid(x, y));
        }
        else{
            int etalonEmpty = 0;//счетчик соседних ячеек с 0
            int nextEtalonEmpty;

            reitingRes = reitingCell();
            int n = maxReiting(reitingCell());
            System.out.println("y = " + reitingRes[n][1]);
            System.out.println("x = " + reitingRes[n][2]);
            x = reitingRes[n][2];
            y = reitingRes[n][1];

            /*for(int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
        // Проверяем клетки по направлениям
               //добавить проверку приоритетной ветки
                    if(map[i][j] == DOT_EMPTY){
                        //sumReitingEmpty(i, j);
                        reitingRes = reitingCell();
                        int n = maxReiting(reitingCell());
                        System.out.println("y = reitingRes[n][1] - 1; " + (reitingRes[n][1] - 1));
                        System.out.println("x = reitingRes[n][2] - 1; " + (reitingRes[n][2] - 1));
                        x = reitingRes[n][2] - 1;
                        y = reitingRes[n][1] - 1;
                    }
                }
            }*/
        }
        System.out.println("Компьютер выбрал ячейку " + (y + 1) + " " + (x + 1));
        map[y][x] = DOT_O;
    }

    //Метод валидации запрашиваемой ячейки на корректность
    public static boolean isCellValid(int x, int y){
        boolean result = true;
        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }
        if(map[y][x] != DOT_EMPTY){
            result = false;
        }
        return result;
    }

    //Метод проверки игры на завершение
    private static boolean isEndGame(char playerSymbol){
       boolean result = false;

       printMap();

       //проверяем необходимость следующего хода
        if (checkWin(playerSymbol)){
            System.out.println("Победили: " + playerSymbol);
            result = true;
        }

        if (isMapFull()){
            System.out.println("Ничья");
            result = true;
        }

       return result;
    }

    //Проверка 100% заполненнности поля
    private static boolean isMapFull(){
        boolean result = true;

        for (int i = 0; i<SIZE; i++){
            for (int j = 0; j<SIZE; j++){
                if (map[i][j] == DOT_EMPTY)
                    result = false;
            }
        }

        return result;
    }

    private static boolean checkWin(char playerSymbol) {
        int kol = 0;
        boolean res = false;

        while (true){
            // проверка строк на победу
            for (int i = 0; i < SIZE; i++){
                kol = 0;
                for (int j = 0; j < SIZE; j++) {
                    kol = (map[i][j] == playerSymbol) ? (kol + 1) : kol;
                }
                System.out.println("проверка строк на победу kol1 " + kol);
                if (kol==SIZE) {
                    res = true;
                    break;
                }
            }

            // проверка столбцов на победу
            kol = 0;
            for (int i = 0; i < SIZE; i++){
                kol = 0;
                for (int j = 0; j < SIZE; j++) {
                    kol = (map[j][i] == playerSymbol) ? (kol + 1) : kol;
                }
                System.out.println("проверка столбцов на победу kol2 " + kol);
                if (kol==SIZE) {
                    res = true;
                    break;
                }
            }

            //главная диагональ
            kol = 0;
            for (int i = 0; i < SIZE; i++){
                 kol = (map[i][i]==playerSymbol) ? (kol + 1) : kol;
            }
            System.out.println("главная диагональ kol3 " + kol);
            if (kol==SIZE) {
                res = true;
                break;
            }

            //побочная диагональ
            kol = 0;
            for (int i = 0; i < SIZE; i++) {
                kol = (map[i][SIZE - i - 1] == playerSymbol) ? (kol + 1) : kol;
            } System.out.println("побочная диагональ kol4 " + kol);
            if (kol==SIZE) {
                res = true;
                break;
            }

        break;
        }
        return res;
    }

}
