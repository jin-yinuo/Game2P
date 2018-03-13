package com.example.jessie.game2p;

/**
 * Created by Jessie on 3/12/2018.
 */

public class Timer {
    Long startTime;
    Long endTime;
    Long pauseTime;
    Long totalPaused = 0L;

    public Timer() {
        Long startTime = System.currentTimeMillis();
    };

    public void pause() {
        Long pauseTime = System.currentTimeMillis();
    };

    public void resume() {
        Long resumeTime = System.currentTimeMillis();
        this.totalPaused += resumeTime - pauseTime;

    };

    public Long endTimer() {
        return endTime - startTime - this.totalPaused;
    };
}
