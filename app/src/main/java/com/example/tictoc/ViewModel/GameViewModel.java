package com.example.tictoc.ViewModel;

import com.example.tictoc.Model.Cell;
import com.example.tictoc.Model.Game;
import com.example.tictoc.Model.Player;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import static com.example.tictoc.Utilities.StringUtility.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game game ;

    public void init(String player1 , String player2){
        game = new Game(player1 , player2);
        cells = new ObservableArrayMap<>();
    }


    public void onClickedCellAt(int row, int column) {
        if (game.cells[row][column] == null) {
            game.cells[row][column] = new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row, column), game.currentPlayer.getValue());
            if (game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner(){
        return game.winner;
    }
}
