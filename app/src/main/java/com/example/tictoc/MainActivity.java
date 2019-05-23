package com.example.tictoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.tictoc.Model.Player;
import com.example.tictoc.Utilities.StringUtility;
import com.example.tictoc.View.GameBeginDialog;
import com.example.tictoc.View.GameEndDialog;
import com.example.tictoc.ViewModel.GameViewModel;
import com.example.tictoc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String GAME_BEGIN_DIALOG_TAG = "game_begin";
    private static final String GAME_END_DIALOG_TAG = "game_end";
    private static final String NO_WINNER = "No one";

    private GameViewModel gameViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        start();

    }

    public void start(){
        // Show Dialog first instead of layout
        GameBeginDialog beginDialog = GameBeginDialog.newInstance(this);
        beginDialog.setCancelable(false);
        beginDialog.show(getSupportFragmentManager() , GAME_BEGIN_DIALOG_TAG);
    }

    public void onPlayerSet(String player1 , String player2){
       // Bind gameActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        ActivityMainBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.init(player1,player2);
        activityGameBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();

    }

    private void setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this , this::onGameWinnerChanged);
    }

    private void onGameWinnerChanged(Player player) {

        String winner = player == null || StringUtility.isNullOrEmpty(player.getName()) ? NO_WINNER : player.getName();
        GameEndDialog dialog = GameEndDialog.newInstance(this, winner);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
    }
}
