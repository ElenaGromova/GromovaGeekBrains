package lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame{
    static int dimension = 3; //размерность
    static int cellSize = 150; //размер клетки
    private char[][] gameField; //матрица игры
    private GameButton[] gameButtons; //масиив кнопок

    private Game game; //ссылка на игру

    static char nullSymbol = '\u0000'; //null символ

    public GameBoard(Game currentGame){
        this.game = currentGame;
        initField();
    }

    // метод инициализации и отрисовки поля
    private void initField(){
        setBounds(cellSize*dimension, cellSize*dimension, 400, 300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel(); //панель управления игрой
        JButton newGameButton = new JButton("Новая игра");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize*dimension, 150);

        JPanel gameFieldPanel = new JPanel(); //панель самой игры
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize*dimension, cellSize*dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension*dimension];

        //инициализируем игровое поле
        for(int i = 0; i < (dimension*dimension); i++){
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    void emptyField(){
        for(int i=0; i <= (dimension*dimension); i++){
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;
        }
    }

    Game getGame(){
        return game;
    }

    //метод проверки доступности ход по горизонтали и вертикали
    boolean isTurnable(int x, int y){
        return (gameField[x][y]==nullSymbol);
    }

    //обновление матрицы игры после хода
    void updateGameField(int x, int y){
        gameField[x][y] = game.getCurrentPlayer().getPlayerSign();
    }

    private boolean checkLastWinStep(int i, int j, char playerSymbol){
        boolean result = false;
        gameField[i][j] = playerSymbol;
        if (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)){
            result = true;
        }
        gameField[i][j] = nullSymbol;
        return result;
    }

    //подсчитываем рейтинг ячейки
    private int cellRaiting(int i, int j, char playerSymbol) {
        int count = 0;
        int[][] roundCell = {{i - 1, j - 1},
                {i - 1, j},
                {i - 1, j + 1},
                {i, j - 1},
                {i, j + 1},
                {i + 1, j - 1},
                {i + 1, j},
                {i + 1, j + 1}};

        for (int[] check : roundCell) {
            count = (isCellValidForRating(check[0], check[1]) &&
                    (gameField[check[0]][check[1]] == playerSymbol || gameField[check[0]][check[1]] == nullSymbol)) ? (count + 1) : count;
        }
    return count;
    }

    //выбираем ячейку с максимальным рейтингом и возвращаем ее координаты
    private int[] maxCellRaiting() {
        int[] adressCell = {0, 0};
        int maxRating = 0;

        for (int i = 0; i < GameBoard.dimension; i++) {
            for (int j = 0; j < GameBoard.dimension; j++) {
                if (isTurnable(i,j)) {
                    if (cellRaiting(i, j, game.gamePlayers[1].getPlayerSign()) >= maxRating){
                        adressCell[0] = i;
                        adressCell[1] = j;
                    }
                }
            }
        }
    return adressCell;
    }

    //проверяем подходит ли ячейка для подчета рейтинга
    private boolean isCellValidForRating(int x, int y) {
        return  (x >= 0 && x < GameBoard.dimension && y >= 0 && y < GameBoard.dimension);
    }

    //ход: проверяем сперва если ли предвыигрышная комбинация ноликов, затем крестиков, если таких нет то ходим в рейтинг-ячейку
    //приоритеты: сперва найти во всем массиве комбинацию ноликов, потом крестиков
    int[] isWinCompHuman(){
        int[] maxScoreField = {-1, -1};

        for (int i = 0; i < GameBoard.dimension; i++)
            for (int j = 0; j < GameBoard.dimension; j++)
                if (isTurnable(i, j)){
                    if (checkLastWinStep(i, j, game.gamePlayers[1].getPlayerSign())) {
                        maxScoreField[0] = i;
                        maxScoreField[1] = j;
                        return maxScoreField;
                    }
            }

        for (int i = 0; i < GameBoard.dimension; i++)
            for (int j = 0; j < GameBoard.dimension; j++)
                if (isTurnable(i, j)){
                    if (checkLastWinStep(i, j, game.gamePlayers[0].getPlayerSign())) {
                        maxScoreField[0] = i;
                        maxScoreField[1] = j;
                        return maxScoreField;
                    }
                }

        maxScoreField = maxCellRaiting();

        return maxScoreField;
    }

    //проверка заполненности всех ячеек поля
    boolean isFull(){
        boolean result = true;

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                if (gameField[i][j]==nullSymbol) {
                    result = false;
                    break; // выходим из внутреннего цикла
                }
            }
            //выходим из внешнего цикла если найдена пустая клетка
            if (!result){
                break;
            }
        }
        return result;
    }

    //проверка победы
    boolean checkWin(){
        char playerSymbol = getGame().getCurrentPlayer().getPlayerSign();
        return (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol));
    }

    //проверка победы по столбцам и линиям
    private boolean checkWinLines(char playerSymbol) {
        int winRow = 0;
        int winCol = 0;
        for (int i = 0; i < GameBoard.dimension; i++) {
            for (int j = 0; j < GameBoard.dimension; j++) {
                winRow += (gameField[i][j] == playerSymbol) ? 1 : 0;
                winCol += (gameField[j][i] == playerSymbol) ? 1 : 0;
            }
            if (winRow == GameBoard.dimension || winCol == GameBoard.dimension) {
                break;
            }
            winRow = 0;
            winCol = 0;
        }
            return (winRow == GameBoard.dimension || winCol == GameBoard.dimension);
    }

    //проверка побуды по диагоналям
    private boolean checkWinDiagonals(char playerSymbol){
        int kol = 0;
        int kol1 = 0;

        //главная диагональ
        for (int index = 0; index < dimension; index++){
            //главная диагональ
            kol += (gameField[index][index]==playerSymbol) ? 1 : 0;
            //побочная диагональ
            kol1 += (gameField[index][dimension - index - 1] == playerSymbol) ?  1 : 0;
        }
        return (kol == dimension || kol1 == dimension);
    }

    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }
}
