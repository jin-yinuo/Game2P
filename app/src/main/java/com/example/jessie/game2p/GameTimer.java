package com.example.jessie.game2p;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Jessie on 3/28/2018.
 */

public class GameTimer {
        final Handler handler = new Handler();
        Timer t;
        int timeLeft = 60;

        public GameTimer(SinglePlay s) {
           newTimer(s);
        };

        public void newTimer(SinglePlay s) {
            t = new Timer();
            final SinglePlay ss = s;
            TimerTask tick = new TimerTask() {
                final SinglePlay sss = ss;
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            timeLeft--;
                            if (timeLeft == 0) {
                                Intent i = new Intent(sss, EndGame.class);
                                sss.startActivity(i);
                                sss.finish();
                            };
                        }

                    });


                }
            };
            t.scheduleAtFixedRate(tick, 0, 1000);
        };

        public void pause() {
            t.cancel();
        };


}
