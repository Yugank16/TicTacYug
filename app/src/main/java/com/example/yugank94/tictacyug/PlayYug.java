package com.example.yugank94.tictacyug;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayYug extends AppCompatActivity implements View.OnClickListener {

    MyButton buttons[][];
    LinearLayout rows[];
    LinearLayout mainLayout,board;
    boolean player1turn;
    boolean gameover;
    int n=3;
    TextView textview1,textview2;
    String play1, play2;
    public final static int NO_PLAYER =0;
    public final static int PLAYER_1 =1;
    public final static int PLAYER_2 =2;
    public final static int PLAYER_1_WIN =1;
    public final static int PLAYER_2_WIN =2;
    public final static int DRAW =3;
    public final static int INCOMPLETE =4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_play_ug);
        mainLayout= (LinearLayout) findViewById(R.id.activity_play_ug);

         textview1= (TextView) findViewById(R.id.name1);

        textview2= (TextView) findViewById(R.id.name2);

        OnStart();
        setupboard();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menubar,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.newgame:
            {
                resetboard();
                break;
            }

            case R.id.size3:
            {
                if(n==3)
                resetboard();
                else {
                    n=3;
                    setupboard();
                }
                break;
            }

            case R.id.size4:
            {
                if(n==4)
                    resetboard();
                else {
                    n=4;
                    setupboard();
                }
                break;
            }

            case R.id.size5:
            {
                if(n==5)
                    resetboard();
                else {
                    n=5;
                    setupboard();
                }
                break;
            }
        }
        return true;
    }

    public void resetboard()
    {
        player1turn= true;
        gameover= false;

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
            {
                buttons[i][j].setText("");
                buttons[i][j].setPadding(5,5,5,5);
                buttons[i][j].setPlayer(NO_PLAYER);
                buttons[i][j].setBackgroundResource(R.drawable.texture);
            }
    }





    private void setupboard() {
        int i,j;
        player1turn= true;          //player1 turn

        gameover= false;        // game start. i.e not over

        mainLayout.removeView(board);

        board= new LinearLayout(this);          //make main board
        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,3);
        params.setMargins(30,30,30,30);
        board.setLayoutParams(params);
        board.setBackgroundResource(R.drawable.text1);
        board.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(board);

        rows= new LinearLayout[n];          //create buttons and rows.
        buttons= new MyButton[n][n];



        for(i=0; i<n; i++)
        {
            rows[i]= new LinearLayout(this);
            LinearLayout.LayoutParams parameters= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            rows[i].setLayoutParams(parameters);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            board.addView(rows[i]);
        }

        for(i=0;i<n; i++)
        {
            for(j=0;j<n;j++)
            {
                buttons[i][j]= new MyButton(this);
                LinearLayout.LayoutParams parameters= new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.WRAP_CONTENT,1);
                parameters.setMargins(2,2,2,2);
                buttons[i][j].setLayoutParams(parameters);
                buttons[i][j].setText("");
                buttons[i][j].setPadding(5,5,5,5);
                buttons[i][j].setBackgroundResource(R.drawable.texture);
//                buttons[i][j].setBackgroundColor(Color.TRANSPARENT);
                buttons[i][j].setPlayer(NO_PLAYER);
                buttons[i][j].setOnClickListener(this);
                rows[i].addView(buttons[i][j]);
            }
        }

    }

    @Override
    public void onClick(View v) {


        MyButton b = (MyButton) v;

        if (gameover) {
            return;
        } else if (b.getPlayer() != NO_PLAYER) {
            Toast.makeText(this, "Inavlid Move !!", Toast.LENGTH_LONG).show();
            return;
        } else if (player1turn) {
            b.setPlayer(PLAYER_1);
            b.setBackgroundResource(R.drawable.cross);
        } else {
            b.setPlayer(PLAYER_2);
            b.setBackgroundResource(R.drawable.circle);
        }

        int status = gameStatus();
        if(status == PLAYER_1_WIN || status == PLAYER_2_WIN){
            Toast.makeText(this, "Player "+status+" wins !!", Toast.LENGTH_SHORT).show();
            gameover = true;
            return;
        }

        if(status == DRAW){
            Toast.makeText(this, "Draw !!", Toast.LENGTH_SHORT).show();
            gameover = true;
            return;
        }
        player1turn = !player1turn;
    }


        private int gameStatus() {


        // Rows
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (buttons[i][j].getPlayer() == NO_PLAYER || buttons[i][0].getPlayer() != buttons[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {

                for(int k=0;k<n;k++)
                {
                    buttons[i][k].setBackgroundResource(R.drawable.star);
                }

                if (buttons[i][0].getPlayer() == PLAYER_1) {
                    return PLAYER_1_WIN;
                } else {
                    return PLAYER_2_WIN;
                }
            }

        }

// Columns
        for (int j = 0; j < n; j++) {
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                if (buttons[i][j].getPlayer() == NO_PLAYER || buttons[0][j].getPlayer() != buttons[i][j].getPlayer()) {
                    flag = false;
                    break;
                }
            }
            if (flag) {

                for(int k=0;k<n;k++)
                {
                    buttons[k][j].setBackgroundResource(R.drawable.star);
                }

                if (buttons[0][j].getPlayer() == PLAYER_1) {
                    return PLAYER_1_WIN;
                } else {
                    return PLAYER_2_WIN;
                }
            }

        }

        // Diagonal 1
        boolean flag = true;
        for(int i = 0; i < n; i++){
            if (buttons[i][i].getPlayer() == NO_PLAYER || buttons[0][0].getPlayer() != buttons[i][i].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {

            for(int k=0;k<n;k++)
            {
                buttons[k][k].setBackgroundResource(R.drawable.star);
            }

            if (buttons[0][0].getPlayer() == PLAYER_1) {
                return PLAYER_1_WIN;
            } else {
                return PLAYER_2_WIN;
            }
        }

        // Diagonal 2
        flag = true;
        for(int i = n - 1; i >= 0; i--){
            int col = n - 1 - i;
            if (buttons[i][col].getPlayer() == NO_PLAYER || buttons[n - 1][0].getPlayer() != buttons[i][col].getPlayer()) {
                flag = false;
                break;
            }
        }
        if (flag) {

            for(int k=n-1;k>=0;k--)
            {
                int col= n-1-k;
                buttons[k][col].setBackgroundResource(R.drawable.star);
            }

            if (buttons[n - 1][0].getPlayer() == PLAYER_1) {
                return PLAYER_1_WIN;
            } else {
                return PLAYER_2_WIN;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(buttons[i][j].getPlayer() == NO_PLAYER){
                    return INCOMPLETE;
                }
            }
        }
        return DRAW;
    }


    protected void OnStart()
    {


        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);

        final EditText txtbox1= (EditText) dialog.findViewById(R.id.player1);
        //play1= txtbox1.getText().toString();
        final EditText txtbox2= (EditText) dialog.findViewById(R.id.player2);
        //play2= txtbox2.getText().toString();
        Button b= (Button) dialog.findViewById(R.id.play);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview1.setText(txtbox1.getText().toString());
                textview2.setText(txtbox2.getText().toString());


                dialog.dismiss();
            }
        });

        dialog.show();


    }
}






