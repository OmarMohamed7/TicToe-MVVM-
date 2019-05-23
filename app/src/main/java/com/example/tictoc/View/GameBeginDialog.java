package com.example.tictoc.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tictoc.MainActivity;
import com.example.tictoc.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class GameBeginDialog extends DialogFragment {

    // Dialog for begining game

    private TextInputLayout player1Layout;
    private TextInputLayout player2Layout;

    //Playes Names
    private TextInputEditText player1EditText;
    private TextInputEditText player2EditText;

    private String player1;
    private String player2;

    private View rootView;
    private MainActivity activity;

    public static GameBeginDialog newInstance(MainActivity activity){
        GameBeginDialog dialog = new GameBeginDialog();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        intitViews();
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setTitle("Hello")
                .setPositiveButton("Done", (dialog, which) -> {
                    if (isValidName(player1Layout , player1) && isValidName(player2Layout , player2)){
                        activity.onPlayerSet(player1 , player2);
                        dismiss();
                    }
                })
                .create();

        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);

        //alertDialog.setOnShowListener(dialog -> onDialoShow(alertDialog));

        return alertDialog;
    }

    private void intitViews() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.game_begin_dialog ,null , false);

        player1Layout = rootView.findViewById(R.id.layout_player1);
        player2Layout = rootView.findViewById(R.id.layout_player2);

        player1EditText = rootView.findViewById(R.id.et_player1);
        player2EditText = rootView.findViewById(R.id.et_player2);

        // add text watchers to save the users names
        addTextWatchers();
    }

    private boolean isValidName(TextInputLayout layout , String name){
        if(TextUtils.isEmpty(name)){
            layout.setErrorEnabled(true);
            layout.setError("Please enter your name");
            return false;
        }

        if(player1 != null && player2 != null && player1.equalsIgnoreCase(player2)){
            layout.setErrorEnabled(true);
            layout.setError("Same names !");
            return false;
        }

        layout.setErrorEnabled(false);
        layout.setError("");
        return true;
    }

    private void addTextWatchers(){

        player1EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                player1 = s.toString();

            }
        });

        player2EditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                player2 = s.toString();
            }
        });
    }

}
