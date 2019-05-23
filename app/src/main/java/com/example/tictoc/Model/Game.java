package com.example.tictoc.Model;

import androidx.lifecycle.MutableLiveData;
import android.util.Log;

public class Game {

    private static final String TAG = Game.class.getSimpleName();
    private static final int BOARD_SIZE = 3;

    public Player player1;
    public Player player2;

    // current player
    public Player currentPlayer;

    public Cell[][] cells;

    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game(String player1Name , String player2Name){
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(player1Name , "X");
        player2 = new Player(player2Name , "O");
        currentPlayer = player1;
    }

    public void switchPlayer(){
        currentPlayer = (currentPlayer == player1 ? player2 : player1);
    }


    private boolean hasThreeSameHorizontalCells(){
        try {
            for(int i=0; i<BOARD_SIZE; i++){
                if(areEqual(cells[i][0] , cells[i][1] , cells[i][2])){
                    return true;
                }
            }
            return false;
        }catch (NullPointerException e){
            Log.e(TAG , e.getMessage());
            return false;
        }
    }

    private boolean hasThreeSameVerticalCells(){
        try {
            for(int i=0; i<BOARD_SIZE; i++){
                if(areEqual(cells[0][i] , cells[1][i] , cells[2][i])){
                    return true;
                }
            }
            return false;
        }catch (NullPointerException e){
            Log.e(TAG , e.getMessage());
            return false;
        }
    }

    private boolean hasThreeSameDiagonalCells(){
        try {

            return areEqual(cells[0][0] , cells[1][1] , cells[2][2])
                    || areEqual(cells[0][2] , cells[1][1] , cells[2][0]);

        }catch (NullPointerException e){
            Log.e(TAG , e.getMessage());
            return false;
        }
    }

    private boolean areEqual(Cell... cells){
        if(cells == null || cells.length == 0)
            return false;

        for(Cell cell : cells) {
            if (cell == null || cell.player.getValue() == null)
                return false;
        }
        Cell comparisonBase = cells[0];
        for(int i=1; i<cells.length; i++){
            if(!comparisonBase.player.getValue().equals(cells[i].player.getValue()))
                return false;


        }

        return true;
    }

    public boolean hasGameEnded(){
        if(hasThreeSameDiagonalCells() || hasThreeSameHorizontalCells() || hasThreeSameVerticalCells()){
            winner.setValue(currentPlayer);
            return true;
        }

        if (isBoardFull()){
            winner.setValue(null);
            return true;
        }
        return false;
    }

    // when is board full then no one is winner
    public boolean isBoardFull(){
        for(Cell[] row : cells)
            for(Cell cell : row)
                if(cell == null || cell.isEmpty())
                    return false;

        return true;
    }

    public void reset(){
        player1 = null;
        player2 = null;
        currentPlayer = null;
        cells = null;
    }
}
