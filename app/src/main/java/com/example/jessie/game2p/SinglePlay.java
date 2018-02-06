package com.example.jessie.game2p;
import java.util.Random;
import java.util.Arrays;
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

import static android.R.color.holo_blue_dark;
import static android.R.color.holo_red_dark;

public class SinglePlay extends AppCompatActivity {
    ImageButton pauseButton;
    ImageView pauseScreenShadow;
    ImageView pauseScreenBackground;
    Button resumeButton;
    Button mainmenuButton;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_play);

        shuffleSquares();

        Button bt = (Button) findViewById(R.id.button9);
        bt.setSelected(false);

        Button shuffleButton = (Button) findViewById(R.id.shuffle_button);
        shuffleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffleSquares();
            }
        });

        pauseButton = (ImageButton) findViewById(R.id.pause_button);
        pauseScreenShadow = (ImageView) findViewById(R.id.pause_screen_shadow);
        pauseScreenBackground = (ImageView) findViewById(R.id.pause_screen_background);
        resumeButton = (Button) findViewById(R.id.resume_button);
        mainmenuButton = (Button) findViewById(R.id.main_menu_button);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);
        b10 = (Button) findViewById(R.id.button10);
        b11 = (Button) findViewById(R.id.button11);
        b12 = (Button) findViewById(R.id.button12);
        b13 = (Button) findViewById(R.id.button13);
        b14 = (Button) findViewById(R.id.button14);
        b15 = (Button) findViewById(R.id.button15);
        b16 = (Button) findViewById(R.id.button16);



        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pauseScreenShadow.setVisibility(View.VISIBLE);
                pauseScreenBackground.setVisibility(View.VISIBLE);
                resumeButton.setVisibility(View.VISIBLE);
                mainmenuButton.setVisibility(View.VISIBLE);
                pauseButton.setEnabled(false);
                b1.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                b5.setEnabled(false);
                b6.setEnabled(false);
                b7.setEnabled(false);
                b8.setEnabled(false);
                b9.setEnabled(false);
                b10.setEnabled(false);
                b11.setEnabled(false);
                b12.setEnabled(false);
                b13.setEnabled(false);
                b14.setEnabled(false);
                b15.setEnabled(false);
                b16.setEnabled(false);
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pauseScreenShadow.setVisibility(View.GONE);
                pauseScreenBackground.setVisibility(View.GONE);
                resumeButton.setVisibility(View.GONE);
                mainmenuButton.setVisibility(View.GONE);
                pauseButton.setEnabled(true);
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);
                b4.setEnabled(true);
                b5.setEnabled(true);
                b6.setEnabled(true);
                b7.setEnabled(true);
                b8.setEnabled(true);
                b9.setEnabled(true);
                b10.setEnabled(true);
                b11.setEnabled(true);
                b12.setEnabled(true);
                b13.setEnabled(true);
                b14.setEnabled(true);
                b15.setEnabled(true);
                b16.setEnabled(true);
            }
        });
    }


    public void toggleSquare(ImageView square) {
        if (square.isSelected()) {
            square.setBackgroundColor(getResources().getColor(holo_blue_dark));
        }
        else {
            square.setBackgroundColor(getResources().getColor(holo_red_dark));
        }
    }

    public void shuffleSquares() {
        Random obj = new Random();
        int coloured = obj.nextInt(5) +5;
        int[] randomArr = new int[coloured];
        for (int i = 0; i < coloured; ++i) {
            int r = obj.nextInt(16);
            if (!Arrays.asList(randomArr).contains(r)) {
                randomArr[i] = r;
            }

        }
        for (int i = 0; i < coloured; ++i) {
            String squareID = "imageView" + (i+1);
            int id = getResources().getIdentifier(squareID, "id", getPackageName());
            toggleSquare((ImageView) findViewById(id));
        }


    }

}
