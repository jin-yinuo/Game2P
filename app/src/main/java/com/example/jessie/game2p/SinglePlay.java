package com.example.jessie.game2p;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.w3c.dom.Element;

/**
 * Created by Jessie on 10/14/2017.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static android.R.color.holo_blue_dark;
import static android.R.color.holo_red_dark;

public class SinglePlay extends AppCompatActivity {
    RelativeLayout pauseShadow;
    ImageButton pauseButton;
    ImageView pauseScreenBackground;
    Button resumeButton;
    Button mainmenuButton;
    Button b11, b12, b13, b14, b21, b22, b23, b24, b31, b32, b33, b34, b41, b42, b43, b44;
    ImageView i11, i12, i13, i14, i21, i22, i23, i24, i31, i32, i33, i34, i41, i42, i43, i44;
    Button[][] bArray = {{b11, b12, b13, b14}, {b21, b22, b23, b24}, {b31, b32, b33, b34}, {b41, b42, b43, b44}};
    ImageView[][] sqArray = {{i11, i12, i13, i14}, {i21, i22, i23, i24}, {i31, i32, i33, i34}, {i41, i42, i43, i44}};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_play);

        shuffleSquares();

        pauseShadow = (RelativeLayout) findViewById(R.id.pause_screen_shadow);
        pauseButton = (ImageButton) findViewById(R.id.pause_button);
        pauseScreenBackground = (ImageView) findViewById(R.id.pause_screen_background);
        resumeButton = (Button) findViewById(R.id.resume_button);
        mainmenuButton = (Button) findViewById(R.id.main_menu_button);
        b11 = (Button) findViewById(R.id.button11);
        b12 = (Button) findViewById(R.id.button12);
        b13 = (Button) findViewById(R.id.button13);
        b14 = (Button) findViewById(R.id.button14);
        b21 = (Button) findViewById(R.id.button21);
        b22 = (Button) findViewById(R.id.button22);
        b23 = (Button) findViewById(R.id.button23);
        b24 = (Button) findViewById(R.id.button24);
        b31 = (Button) findViewById(R.id.button31);
        b32 = (Button) findViewById(R.id.button32);
        b33 = (Button) findViewById(R.id.button33);
        b34 = (Button) findViewById(R.id.button34);
        b41 = (Button) findViewById(R.id.button41);
        b42 = (Button) findViewById(R.id.button42);
        b43 = (Button) findViewById(R.id.button43);
        b44 = (Button) findViewById(R.id.button44);


        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pauseShadow.setVisibility(View.VISIBLE);
                pauseScreenBackground.setVisibility(View.VISIBLE);
                resumeButton.setVisibility(View.VISIBLE);
                mainmenuButton.setVisibility(View.VISIBLE);
                pauseButton.setEnabled(false);
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 4; ++j) {
//                        bArray[i][j].setEnabled(false);
                    }
                }
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pauseShadow.setVisibility(View.GONE);
                pauseScreenBackground.setVisibility(View.GONE);
                resumeButton.setVisibility(View.GONE);
                mainmenuButton.setVisibility(View.GONE);
                pauseButton.setEnabled(true);
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 4; ++j) {
//                        bArray[i][j].setEnabled(true);
                    }
                }
            }
        });

        mainmenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(SinglePlay.this, Main.class);
                SinglePlay.this.startActivity(i);
            }
        });

        Button shuffleButton = (Button) findViewById(R.id.shuffle_button);
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffleSquares();
            }
        });
    }


    public void toggleSquare(ImageView square) {
        if (square.isSelected()) {
            square.setBackgroundColor(Color.BLUE);
            square.setSelected(false);
        } else {
            square.setBackgroundColor(Color.RED);
            square.setSelected(true);
        }
    }

    public void shuffleSquares() {
        Random obj = new Random();
        int coloured = obj.nextInt(5) + 5;
        int[] randomArr = new int[coloured];
        for (int i = 0; i < coloured; ++i) {
            int r = obj.nextInt(16);
            if (!contains(randomArr, r)) {
                randomArr[i] = r;
                break;
            }
        }
        for (int i = 0; i < coloured; ++i) {
            int row = randomArr[i] / 4;
            int col = randomArr[i] % 4;
//            ImageView sq = sqArray[row][col];
//            toggleSquare(sq);
        }
    }


    public static boolean contains(final int[] array, final int v) {

        boolean result = false;

        for (int i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }

        return result;
    }

}
