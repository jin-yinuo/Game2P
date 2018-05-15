package com.example.jessie.game2p;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.animation.ValueAnimator;

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
    Button quitGameButton;
    Button mainmenuButton;
    Button nextButton;
    TextView scoreText;

    ImageView i11, i12, i13, i14, i21, i22, i23, i24, i31, i32, i33, i34, i41, i42, i43, i44,
            i51, i52, i53, i54, i61, i62, i63, i64, i71, i72, i73, i74, i81, i82, i83, i84;
    Button[][] bArray;
    ImageView[][] sqArray;
    int[][] compArray;
    GameTimer t = new GameTimer(this);

    int totalScore;
    int difficulty = 4;
    int round = 0;
    int difficultyInterval = 3;

    final int numRow = 8;
    final int numCol = 4;
    final int timeOut = 2000;
    final int animationTimeout = 1000;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_play);

        pauseShadow = (RelativeLayout) findViewById(R.id.pause_screen_shadow);
        pauseButton = (ImageButton) findViewById(R.id.pause_button);
        pauseScreenBackground = (ImageView) findViewById(R.id.pause_screen_background);
        resumeButton = (Button) findViewById(R.id.resume_button);
        quitGameButton = (Button) findViewById(R.id.quit_game_button);
        mainmenuButton = (Button) findViewById(R.id.main_menu_button);
        nextButton = (Button) findViewById(R.id.next_button);
        scoreText = (TextView) findViewById(R.id.score_text);

        sqArray = new ImageView[numRow][numCol];

        for(int i = 1; i <= numRow; i++){
            for(int j = 1; j <= numCol; j++) {
                String buttonID = "imageView" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                sqArray[i - 1][j - 1] = ((ImageView) findViewById(resID));
            }
        }

        compArray = new int[numRow][numCol];

        final GameScore gameScore = new GameScore();
        gameScore.score = 0;


        runGame();


        pauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t.pause();
                pauseShadow.setVisibility(View.VISIBLE);
                pauseScreenBackground.setVisibility(View.VISIBLE);
                resumeButton.setVisibility(View.VISIBLE);
                quitGameButton.setVisibility(View.VISIBLE);
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
                pauseScreenBackground.setVisibility(View.GONE);
                resumeButton.setVisibility(View.GONE);
                pauseShadow.setVisibility(View.GONE);
                quitGameButton.setVisibility(View.GONE);
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

        quitGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(SinglePlay.this, EndGame.class);
                SinglePlay.this.startActivity(i);
                SinglePlay.this.finish();
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
                updateScore(gameScore);
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

//        Button shuffleButton = (Button) findViewById(R.id.shuffle_button);
//        shuffleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shuffleSquares();
//                final Handler handler = new Handler();
//                final Timer timer2 = new Timer();
//                TimerTask testing = new TimerTask() {
//                    public void run() {
//                        handler.post(new Runnable() {
//                            public void run() {
//                                resetSquares();
//                                timer2.cancel();
//                            }
//
//                        });
//
//
//                    }
//                };
//                timer2.schedule(testing, timeOut);
//
//            }
//        });
    }

    public void runGame(){
        nextButton.setEnabled(false);

        if (round % difficultyInterval == 0) {
            difficulty++;
        };
        round++;

        resetSquares();
        shuffleSquares();
        for(int i = 0; i < numRow; i++) {
            for(int j = 0; j < numCol; j++){
                sqArray[i][j].setEnabled(false);
            }
        }
        final Handler handler = new Handler();
        final Timer timer2 = new Timer();
        TimerTask testing = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        resetSquares();
                        for(int i = 0; i < numRow; i++) {
                            for (int j = 0; j < numCol; j++) {
                                sqArray[i][j].setEnabled(true);
                            }
                        }
                        timer2.cancel();
                        nextButton.setEnabled(true);
                    }

                });


            }
        };
        timer2.schedule(testing, timeOut);
    }

    public void updateScore(GameScore gameScore){
        int tempScore = 0;
        for (int i = 0; i < numRow; i++){
            for (int j = 0; j < numCol; j++){
                if (compArray[i][j] == 1) {
                    if (sqArray[i][j].isSelected()){
                        tempScore += 2;
                    } else {
                        tempScore -= 1;
                        fadeAnswer(sqArray[i][j], true);
                    }
                } else {
                    if (sqArray[i][j].isSelected()){
                        tempScore -= 1;
                        fadeAnswer(sqArray[i][j], false);
                    }
                }
            }
        }
        gameScore.score += max(0, tempScore);
        scoreText.setText("Score: " + gameScore.score);

        final Handler handler = new Handler();
        final Timer timer2 = new Timer();
        TimerTask testing = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        runGame();
                    }
                });


            }
        };
        timer2.schedule(testing, animationTimeout + 1000);
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

    public void fadeAnswer(final ImageView sq, boolean missing) { // missing answer: fade from blue to red
                                              // extra answer: fade from red to blue
        final float[] from = new float[3],
                to =   new float[3];

        if (missing) {
            Color.colorToHSV(Color.BLUE, from);   // from white
            Color.colorToHSV(Color.RED, to);     // to red
        } else {
            Color.colorToHSV(Color.RED, from);   // from white
            Color.colorToHSV(Color.BLUE, to);     // to red
        }

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);   // animate from 0 to 1
        anim.setDuration(animationTimeout);                              // for 300 ms

        final float[] hsv  = new float[3];                  // transition color
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override public void onAnimationUpdate(ValueAnimator animation) {
                // Transition along each axis of HSV (hue, saturation, value)
                hsv[0] = from[0] + (to[0] - from[0])*animation.getAnimatedFraction();
                hsv[1] = from[1] + (to[1] - from[1])*animation.getAnimatedFraction();
                hsv[2] = from[2] + (to[2] - from[2])*animation.getAnimatedFraction();

                sq.setColorFilter(Color.HSVToColor(hsv));
            }
        });

        anim.start();
    }

    public void shuffleSquares() {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++){
                compArray[i][j] = 0;
            }
        }
        Random obj = new Random();
        int coloured = obj.nextInt(1) + difficulty;
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

    public void resetSquares() {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                sqArray[i][j].setColorFilter(Color.BLUE);
                sqArray[i][j].setSelected(false);
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

    protected void onPause() {
        super.onPause();
        pauseButton.performClick();
    }

    protected void onStop() {
        super.onStop();
    }

}
