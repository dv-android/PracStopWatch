package gradle.udacity.com.pracstopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int seconds;
    private boolean running , wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
        Log.d("onCreate","onCreate called");
    }

    protected void onStop(){
       super.onStop();
       if(running == true) {
           running = false;
           wasRunning = true;
       }
    }

    protected void onRestart(){
        super.onRestart();
        if(wasRunning==true){
            running = true;
        }
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
        wasRunning = false;
    }

    public void onClickReset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {

                         @Override
                         public void run() {
                             int hours =  seconds/3600;
                             int minutes = (seconds%3600)/60;
                             int secs = seconds%60;
                             String time = String.format("%d:%02d:%02d",
                                     hours,minutes,secs);
                             timeView.setText(time);
                             if(running){
                                 seconds++;
                             }
                             handler.postDelayed(this,1000);
                         }
                     }

        );

    }

    public void onSaveInstaceState(Bundle savedInstanceState){
        Log.d("Activity","onSaveInstance called");
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
}
