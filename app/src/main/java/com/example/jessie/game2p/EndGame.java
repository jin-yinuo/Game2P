package com.example.jessie.game2p;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {

    Button mainmenuButton;
    Button playagainButton;
    TextView scoreText;
    TextView highScoreText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);

        mainmenuButton = (Button) findViewById(R.id.main_menu_button2);
        playagainButton = (Button) findViewById(R.id.play_again_button);
        scoreText = (TextView) findViewById(R.id.scoreText);
        highScoreText = (TextView) findViewById(R.id.highScoreText);
        final GameScore gameScore = new GameScore();

        mainmenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(EndGame.this, Main.class);
                EndGame.this.startActivity(i);
            }
        });

        playagainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(EndGame.this, SinglePlay.class);
                EndGame.this.startActivity(i);
            }
        });

        scoreText.setText("Score: " + gameScore.score);

        SharedPreferences prefs = this.getSharedPreferences("scorePreferences", Context.MODE_PRIVATE);
        int currentHighScore = prefs.getInt("highScore", 0);
        if(gameScore.score > currentHighScore) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highScore", gameScore.score);
            editor.commit();
            currentHighScore = prefs.getInt("highScore", 0);
        }
        highScoreText.setText("High Score: " + currentHighScore);
    }
}

