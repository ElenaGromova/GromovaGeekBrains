package lesson7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gButton){
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if ((board.isTurnable(row, cell))){
            updateByPlayersData(board);
            if (board.isFull()){
                board.getGame().showMessage("Ничья!");
                board.getGame().passTurnStart();
                board.emptyField();
            }
            else {
                updateByAiData(board);
            }
        }
        else {
            board.getGame().showMessage("Некорректный ход");
        }
    }

    //ход человека
    private void updateByPlayersData(GameBoard board){
        //обновить матрицу игры
        board.updateGameField(row, cell);

        //обновить сожержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()){
            button.getBoard().getGame().showMessage("Вы выиграли!");
            board.getGame().passTurnStart();
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }
    }

    //ход компьютера
    private void updateByAiData(GameBoard board){
        int[] maxScoreField = board.isWinCompHuman();

        // обновить матрицу игры
        board.updateGameField(maxScoreField[0], maxScoreField[1]);

        //обновить содержимое кнопки
        int cellIndex = GameBoard.dimension * maxScoreField[0] + maxScoreField[1];
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        //проверяем победу
        if (board.checkWin()){
            button.getBoard().getGame().showMessage("Компьтер выиграл!");
            board.getGame().passTurnStart();
            board.emptyField();
        }
        else {
            board.getGame().passTurn();
        }
    }
}
