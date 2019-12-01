package lesson7;

import javax.swing.*;

public class Game {
    private GameBoard board; // ссылка на поле
    public GamePlayer[] gamePlayers = new GamePlayer[2]; //массив игроков
    private int playersTurn = 0; //индекс текущего игрока

    public Game(){
        this.board = new GameBoard(this);
    }

    public void initGame(){
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(true, '0');
    }

    //метод передачи хода
    void passTurn(){
        playersTurn = (playersTurn == 0) ? 1 : 0;
    }

    //возвращаю человеку текущий ход после окончания предыдущей игры
    void passTurnStart(){
        playersTurn = (playersTurn == 1) ? 0 : 0;
    }

    //получение текущего игрока
    GamePlayer getCurrentPlayer(){return gamePlayers[playersTurn];}

    //метод попап сообщения
    void showMessage(String messageText){
        JOptionPane.showMessageDialog(board, messageText);
    }
}
