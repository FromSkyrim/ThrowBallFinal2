package com.example.a17255.a8project;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by 17255 on 2017/10/9.
 */

public class WinDialogFragment extends DialogFragment {
    Context context;
    int score;

    public WinDialogFragment(Context context, int score) {
        this.context = context;
        this.score = score;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("You Win! Your score is " + score )
                .setPositiveButton("Great!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, HighScore.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }




}
