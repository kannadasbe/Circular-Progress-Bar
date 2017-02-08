package com.crazygeeksblog.circularprogress;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.github.lzyzsd.circleprogress.DonutProgress;

public class MainActivity extends AppCompatActivity {

    private DonutProgress donutProgress;
    private CountDownTimer mCountDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setProgress(100);
        donutProgress.setPrefixText("0:00 /  \n");
        startTimer(1);
    }
    private void startTimer(final int minutes) {
        final int barMax = 100;
        final long countDownMills = 60 * minutes * 1000;
        final long countDownIntervalMills = 500;
        mCountDownTimer = new CountDownTimer(countDownMills, countDownIntervalMills) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                int barVal = (barMax) - ((int) ((float) (leftTimeInMilliseconds * 1.0) / (float) (countDownMills * 1.0) * barMax));
                donutProgress.setProgress(barMax - barVal);
                donutProgress.setPrefixText(String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60) + " / \n");
            }

            @Override
            public void onFinish() {
               if (donutProgress.getPrefixText().contains("00:00")) {
                    donutProgress.setPrefixText("STOP /  \n");
                } else {
                    donutProgress.setPrefixText("2:00 /  \n");
                }
            }
        }.start();
    }

}
