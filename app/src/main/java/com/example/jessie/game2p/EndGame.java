package com.example.jessie.game2p;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class EndGame extends AppCompatActivity {

    Button mainmenuButton;
    Button playagainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game);

        mainmenuButton = (Button) findViewById(R.id.main_menu_button2);
        playagainButton = (Button) findViewById(R.id.play_again_button);

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
    }
}

