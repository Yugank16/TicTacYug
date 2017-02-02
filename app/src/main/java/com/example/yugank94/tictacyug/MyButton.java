package com.example.yugank94.tictacyug;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by yugank94 on 27/1/17.
 */

public class MyButton extends Button {

    int player;

    public MyButton(Context context) {
        super(context);


    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }
}
