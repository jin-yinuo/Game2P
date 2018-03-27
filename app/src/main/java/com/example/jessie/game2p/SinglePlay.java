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
import android.widget.TextView;

import static android.R.color.holo_blue_dark;
import static android.R.color.holo_red_dark;
import static java.lang.Math.max;
import static java.util.Arrays.fill;

public class SinglePlay extends AppCompatActivity {
    RelativeLayout pauseShadow;
    ImageButton pauseButton;
    ImageView pauseScreenBackground;
    Button resumeButton;
    Button mainmenuButton;
    Button nextButton;
    TextView scoreText;

    final int numRow = 8;
    final int numCol = 4;

    ImageView i11, i12, i13, i14, i21, i22, i23, i24, i31, i32, i33, i34, i41, i42, i43, i44,
            i51, i52, i53, i54, i61, i62, i63, i64, i71, i72, i73, i74, i81, i82, i83, i84;
    Button[][] bArray;
    ImageView[][] sqArray;
    int[][] compArray;

    int totalScore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_play);

        pauseShadow = (RelativeLayout) findViewById(R.id.pause_screen_shadow);
        pauseButton = (ImageButton) findViewById(R.id.pause_button);
        pauseScreenBackground = (ImageView) findViewById(R.id.pause_screen_background);
        resumeButton = (Button) findViewById(R.id.resume_button);
        mainmenuButton = (Button) findViewById(R.id.main_menu_button);
        nextButton = (Button) findViewById(R.id.next_button);
        scoreText = (TextView) findViewById(R.id.score_text);

        i11 = (ImageView) findViewById(R.id.imageView11);
        i12 = (ImageView) findViewById(R.id.imageView12);
        i13 = (ImageView) findViewById(R.id.imageView13);
        i14 = (ImageView) findViewById(R.id.imageView14);
        i21 = (ImageView) findViewById(R.id.imageView21);
        i22 = (ImageView) findViewById(R.id.imageView22);
        i23 = (ImageView) findViewById(R.id.imageView23);
        i24 = (ImageView) findViewById(R.id.imageView24);
        i31 = (ImageView) findViewById(R.id.imageView31);
        i32 = (ImageView) findViewById(R.id.imageView32);
        i33 = (ImageView) findViewById(R.id.imageView33);
        i34 = (ImageView) findViewById(R.id.imageView34);
        i41 = (ImageView) findViewById(R.id.imageView41);
        i42 = (ImageView) findViewById(R.id.imageView42);
        i43 = (ImageView) findViewById(R.id.imageView43);
        i44 = (ImageView) findViewById(R.id.imageView44);
        i51 = (ImageView) findViewById(R.id.imageView51);
        i52 = (ImageView) findViewById(R.id.imageView52);
        i53 = (ImageView) findViewById(R.id.imageView53);
        i54 = (ImageView) findViewById(R.id.imageView54);
        i61 = (ImageView) findViewById(R.id.imageView61);
        i62 = (ImageView) findViewById(R.id.imageView62);
        i63 = (ImageView) findViewById(R.id.imageView63);
        i64 = (ImageView) findViewById(R.id.imageView64);
        i71 = (ImageView) findViewById(R.id.imageView71);
        i72 = (ImageView) findViewById(R.id.imageView72);
        i73 = (ImageView) findViewById(R.id.imageView73);
        i74 = (ImageView) findViewById(R.id.imageView74);
        i81 = (ImageView) findViewById(R.id.imageView81);
        i82 = (ImageView) findViewById(R.id.imageView82);
        i83 = (ImageView) findViewById(R.id.imageView83);
        i84 = (ImageView) findViewById(R.id.imageView84);
        sqArray = new ImageView[][]{{i11, i12, i13, i14}, {i21, i22, i23, i24}, {i31, i32, i33, i34}, {i41, i42, i43, i44},
                {i51, i52, i53, i54}, {i61, i62, i63, i64}, {i71, i72, i73, i74}, {i81, i82, i83, i84}};
        compArray = new int[numRow][numCol];

        final GameScore gameScore = new GameScore();
        gameScore.score = 0;

        shuffleSquares();

        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pauseShadow.setVisibility(View.VISIBLE);
                pauseScreenBackground.setVisibility(View.VISIBLE);
                resumeButton.setVisibility(View.VISIBLE);
                mainmenuButton.setVisibility(View.VISIBLE);
                pauseButton.setEnabled(false);
                nextButton.setEnabled(false);
                for (int i = 0; i < numRow; ++i) {
                    for (int j = 0; j < numCol; ++j) {
                        sqArray[i][j].setEnabled(false);
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
                nextButton.setEnabled(true);
                for (int i = 0; i < numRow; ++i) {
                    for (int j = 0; j < numCol; ++j) {
                        sqArray[i][j].setEnabled(true);
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

        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int tempScore = 0;
                for (int i = 0; i < numRow; i++){
                    for (int j = 0; j < numCol; j++){
                        if (compArray[i][j] == 1) {
                            if (sqArray[i][j].isSelected()){
                                tempScore += 2;
                            } else {
                                tempScore -= 1;
                            }
                        } else {
                            if (sqArray[i][j].isSelected()){
                                tempScore -= 1;
                            }
                        }
                    }
                }
                gameScore.score += max(0, tempScore);
                scoreText.setText("Score: " + gameScore.score);
                shuffleSquares();
            }
        });

        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                final int x = i;
                final int y = j;
                sqArray[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        toggleSquare(sqArray[x][y]);
                    }
                });
            }
        }

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
            square.setColorFilter(Color.BLUE);
            square.setSelected(false);
        } else {
            square.setColorFilter(Color.RED);
            square.setSelected(true);
        }
    }

    public void shuffleSquares() {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++){
                compArray[i][j] = 0;
            }
        }
        Random obj = new Random();
        int coloured = obj.nextInt(5) + 5;
        int[] randomArr = new int[coloured];
        for (int i = 0; i < coloured; ++i) {
            while (true) {
                int r = obj.nextInt((numRow*numCol)+1);
                if (!contains(randomArr, r)) {
                    randomArr[i] = r;
                    break;
                }
            }
        }
        for (int i = 0; i < coloured; ++i) {
            int row = (randomArr[i] - 1) / 4;
            int col = (randomArr[i] - 1) % 4;
            ImageView sq = sqArray[row][col];
            toggleSquare(sq);
        }

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++){
                if(sqArray[i][j].isSelected()) {
                    compArray[i][j] = 1;
                } else {
                    compArray[i][j] = 0;
                }
            }
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
